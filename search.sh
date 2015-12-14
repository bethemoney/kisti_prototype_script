#!/bin/sh

. ./setting.sh

# echo $DIRECTORY
mkdir -p $NEWS_RAW_PATH
java -jar ./jars/search.jar -d $NEWS_RAW_PATH >> ./search_result.log
