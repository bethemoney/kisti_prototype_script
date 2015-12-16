#!/bin/sh

# NodeJS 설치 함수
SetupNodeJS()
{
	# 웹페이지 파일 압축 해제
	tar -zxf nodeServer.tar.gz
	# vector파일 및 jar파일 위치 넘겨주기
	echo "var DATA_PATH=\"$VECTORS_PATH/\";" > env.js
	echo "var JAR_PATH=\"$JARS_PATH/\";" >> env.js
	cat environment >> env.js
	mv env.js nodeServer/routes/env.js
	
	# NodeJS 설치 파일 다운로드 
	wget http://nodejs.org/dist/v4.1.1/node-v4.1.1.tar.gz
	# NodeJS 압축 해제
	tar -zxf node-v4.1.1.tar.gz
	mkdir -p $NODE_PATH
	mv node-v4.1.1 $NODE_PATH
	$NODE_PATH/node-v4.1.1/configure
	# GCC 등 개발 툴 다운로드
	sudo yum groupinstall 'Development Tools'
	# NodeJS 컴파일
	sudo make -f $NODE_PATH/node-v4.1.1/Makefile -C $NODE_PATH/node-v4.1.1/
	sudo make install -f $NODE_PATH/node-v4.1.1/Makefile -C $NODE_PATH/node-v4.1.1
}
#Jar파일 및 노드 클라이언트 복사 
CopyFiles()
{
	mkdir -p $JARS_PATH
	mkdir -p $NODEJS_PATH
	cp -r ./jars/* $JARS_PATH/
	cp -r ./nodeServer/* $NODEJS_PATH/
}
fa_argcv()
{
	. ../setting.sh
	SetupNodeJS
	CopyFiles
}
fa_argcv
exit 0
