#!/bin/sh

##########################
. ./setting.sh

mkdir -p $COLLECT_PATH
java -jar ./jars/Collects_0.7.jar health $COLLECT_PATH
java -jar ./jars/Collects_0.7.jar semi $COLLECT_PATH
java -jar ./jars/Collects_0.7.jar nano $COLLECT_PATH
