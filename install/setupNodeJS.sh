#!/bin/sh

argc=$#
argv1=$1

LoadSetting()
{
	echo "Setting 파일을 검사합니다......"
	. ../setting.sh
	echo "환경변수 설정중......"
	
}
SetupNodeJS()
{
	tar -zxf nodeServer.tar.gz
	echo "var DATA_PATH=\"$VECTORS_PATH/\";" > env.js
	echo "var JAR_PATH=\"$JARS_PATH/\";" >> env.js
	cat environment >> env.js
	mv env.js nodeServer/routes/env.js
	#여기까지 노드서버 변수설정
	wget http://nodejs.org/dist/v4.1.1/node-v4.1.1.tar.gz
	tar -zxf node-v4.1.1.tar.gz
	mkdir -p $NODE_PATH
	mv node-v4.1.1 $NODE_PATH
	$NODE_PATH/node-v4.1.1/configure
#	sudo apt-get install build-essential -qq -y
	sudo yum groupinstall 'Development Tools'
	sudo make -f $NODE_PATH/node-v4.1.1/Makefile -C $NODE_PATH/node-v4.1.1/
	sudo make install -f $NODE_PATH/node-v4.1.1/Makefile -C $NODE_PATH/node-v4.1.1
}
CopyFiles()
{
	#echo $VECTORS_PATH
	#echo $JARS_PATH
	mkdir -p $JARS_PATH
	mkdir -p $NODEJS_PATH
	echo `pwd`
	cp -r ./jars/* $JARS_PATH/
	cp -r ./nodeServer/* $NODEJS_PATH/
}
fa_argcv()
{
        if [ 0 -eq $argc ]
        then
                echo ""
                echo "argc:$argc 인자가 충분하지 않습니다."
                echo ""
				echo "install 설치"
				echo "uninstall 삭제"
				echo ""
        fi
        if [ 1 -eq $argc ]; then
                if [ "install" = $argv1 ]; then
					echo "$argv0 설치를 시작합니다"
			                LoadSetting
					SetupNodeJS
					CopyFiles
                elif [ "uninstall" = $argv1 ]; then
					echo "삭제를 시작합니다......"
					cd $NODE_PATH/node-v4.1.1/
					sudo make uninstall
					cd ~
					rm -r $NODE_PATH
					echo "삭제가 완료 되었습니다"
				fi
        fi
}
fa_argcv

exit 0


