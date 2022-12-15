#!bash
prefix=$1
picnum=$(printf "%03d" $2)
ffmpeg -r 13 -i pic_${picnum}_step_%04d.png -c:v libx264 -crf 25  -pix_fmt yuv420p -vf tpad=stop_mode=clone:stop_duration=5 -y ${prefix}_pic_${picnum}.mp4

