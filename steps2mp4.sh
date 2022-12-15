#!bash
picnum=$(printf "%03d" $1)
ffmpeg -r 13 -i pic_${picnum}_step_%04d.png -c:v libx264 -crf 25  -pix_fmt yuv420p  -y pic_${picnum}.mp4

