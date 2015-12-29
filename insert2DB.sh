#!/bin/sh

EXPECTED_ARGS=4

if [ $# -ne $EXPECTED_ARGS ]
	then   
		echo "Usage: $0 <DB_IP> <DB_NAME> <DB_ID> <DB_PASSWORD>"
		exit $E_BADARGS
fi
# 환경설정파일 로드
. ./setting.sh

# Jar파일 통한 DB INSERT
mkdir -p $DBOUT_PATH
java -jar $JARS_PATH/DB_INSERT_0.7.jar $COLLECT_PATH $1/$2 $3 $4
