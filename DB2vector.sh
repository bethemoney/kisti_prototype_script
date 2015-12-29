#!/bin/sh
EXPECTED_ARGS=4

if [ $# -ne $EXPECTED_ARGS ]
	then
	echo "Usage: $0 DB_IP DB_NAME DB_ID DB_PASSWORD"
	exit $E_BADARGS
fi

# 환경설정파일 로드
. ./setting.sh

# Jar를 통한 DB로부터 데이터 추출
mkdir -p $DBOUT_PATH
mkdir -p $VECTORS_PATH
java -jar $JARS_PATH/DBtoVector.jar 6 $DBOUT_PATH/ $1 $2 $3 $4

# DB로부터 추출된 파일 마다 word2vec 수행
for filename in $DBOUT_PATH/*; do
	file=`basename $filename`
	echo "${VECTORS_PATH}/${file}"
	./word2vec -train ${filename} -output $VECTORS_PATH/${file}.txt -size 200 -window 5 -sample 1e-4 -negative 5 -hs 0 -binary 0 -cbow 1 -iter 3
done

