#!/bin/sh
# 환경설정파일 로드
. ./setting.sh

# 뉴스 자동수집기 jar파일 실행
mkdir -p $NEWS_RAW_PATH
java -jar ./jars/search.jar -d $NEWS_RAW_PATH >> ./search_result.log
