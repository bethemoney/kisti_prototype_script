#!/bin/sh



EXPECTED_ARGS=2

if [ $# -ne $EXPECTED_ARGS ]
	then
	   
		echo "Usage: $0 DB_ID DB_PASSWORD"
		exit $E_BADARGS
fi

. ./setting.sh

java -jar ./jars/DB_INSERT_0.7.jar $COLLECT_PATH localhost/kisti $1 $2
