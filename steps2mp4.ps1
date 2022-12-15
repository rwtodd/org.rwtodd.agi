$prefix = $args[0]
$picnum = "{0:D3}" -f $args[1]
& ffmpeg -r 13 -i "pic_${picnum}_step_%04d.png" -c:v libx264 -crf 25 -pix_fmt yuv420p -vf tpad=stop_mode=clone:stop_duration=5  -y "${prefix}_pic_${picnum}.mp4"

