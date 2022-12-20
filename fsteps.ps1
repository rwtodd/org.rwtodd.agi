Param(
  [Parameter(Mandatory=$true)]
  [string]$gamedir,
  [Parameter(Mandatory=$true)]
  [string]$prefix,
  [switch]$noViews = $false,
  [Int32]$picNo
)
# make sure the build is fresh, then look up how to run the program...
& ${PSScriptRoot}\gradlew.bat -p $PSScriptRoot assemble
$agiextract = & ${PSScriptRoot}\gradlew.bat -p $PSScriptRoot aE:rcl | select-string java

[Int32[]]$numList

if($picNo) {
  $numList = @($picNo)
} else {
  # generate all the room pics...
  rm *.png
  Invoke-Expression "& $agiextract -d $gamedir --pics=`"*`""
  rm *_pri.png
  
  # now get a list of all the rooms with images...
  $numList = gci *.png | %{ [Int32]($_.BaseName -replace '.*_0*','') }
}

# run through them generating mp4 files, as long as it isn't already there
$pviews = "--picviews"
if ($noViews) {
  $pviews = ""
}
foreach($pic in $numList) {
  $picnum = "{0:D3}" -f $pic
  $ofile = "${prefix}_pic_${picnum}.mp4"
  if (Test-Path -Path $ofile) {
     write-host "file $ofile is already there"
  } else {
    Invoke-Expression "& $agiextract -d $gamedir $pviews --picsteps --pics=$pic"
    & ffmpeg -r 13 -i "pic_${picnum}_step_%04d.png" -c:v libx264 -crf 25 -pix_fmt yuv420p -vf tpad=stop_mode=clone:stop_duration=5  -y "${prefix}_pic_${picnum}.mp4"
    del *_step_*
    del *_pri.png
  }
}

