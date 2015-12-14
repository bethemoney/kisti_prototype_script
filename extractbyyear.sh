#!/bin/sh

echo "Setting 파일을 검사합니다.....
. ./setting.sh
./word2vec -train $DBOUT_PATH/ -output $VECTORS_PATH/vec.txt -size 200 -window 5 -sample 1e-4 -negative 5 -hs 0 -binary 0 -cbow 1 -iter 3
./word2vec -train $DBOUT_PATH/ -output $VECTORS_PATH/vec.txt -size 200 -window 5 -sample 1e-4 -negative 5 -hs 0 -binary 0 -cbow 1 -iter 3
./word2vec -train $DBOUT_PATH/ -output $VECTORS_PATH/vec.txt -size 200 -window 5 -sample 1e-4 -negative 5 -hs 0 -binary 0 -cbow 1 -iter 3
./word2vec -train $DBOUT_PATH/ -output $VECTORS_PATH/vec.txt -size 200 -window 5 -sample 1e-4 -negative 5 -hs 0 -binary 0 -cbow 1 -iter 3



