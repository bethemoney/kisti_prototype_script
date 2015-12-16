#!/bin/sh

# 환경설정파일 로드
. ./setting.sh

# Jar파일을 통해 웹으로부터 특정 분야 수집
mkdir -p $COLLECT_PATH
java -jar ./jars/Collects_0.7.jar health $COLLECT_PATH
java -jar ./jars/Collects_0.7.jar semi $COLLECT_PATH
java -jar ./jars/Collects_0.7.jar nano $COLLECT_PATH
