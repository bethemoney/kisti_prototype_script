#!/bin/bash

. ./setting.sh
#if [ "$#" -ne 3 ] ; then
#	echo "usage:$0 [text data input Path] [result outputPath] [NNExtractor.jar Path]"
#	exit
#fi

NUMBER_OF_DIR=$(find $NEWS_PATH  -maxdepth 1 -type d | wc -l);
let NUMBER_OF_DIR++
echo $NUMBER_OF_DIR
mkdir -p $NEWS_TMP_PATH
i=0;
for ((i=2; i< ($NUMBER_OF_DIR); i++ ))do
	
	inputPath=$(echo $NEWS_PATH | sed 's/.$//')
#	echo $inputPath
	outputPath=$NEWS_TMP_PATH
	jarPath=$JARS_PATH

	NAME_OF_DIR=$(find $inputPath  -maxdepth 1 -type d | sed -n ${i}p)
	echo ${NAME_OF_DIR};
#	exit
	inputPath=$NAME_OF_DIR
#	echo $inputPath
	fileName=`basename $inputPath`
	#echo $fileName
	#exit
	java -jar $jarPath/NNExtractor.jar -i $inputPath -o $jarPath

	export MAHOUT_LOCAL="TRUE"

	mahout seqdirectory -i $jarPath/txtdata -o $jarPath/seqfile

	export MAHOUT_LOCAL=""

	hadoop fs -mkdir /newsTopic

	echo "Uploading seqfile to hdfs"

	hadoop fs -copyFromLocal $jarPath/seqfile /newsTopic/seqfile

	rm -r $jarPath/txtdata

	rm -r $jarPath/seqfile

	echo "Starting vactorization"

	mahout seq2sparse -i /newsTopic/seqfile/part-m-00000 -o /newsTopic/sparse --maxDFPercent 90

	echo "Starting to make matrix"

	mahout rowid -i /newsTopic/sparse/tf-vectors -o /newsTopic/tf-vectors-cvb

	echo "Starting LDA topic analysis"

	mahout cvb -i /newsTopic/tf-vectors-cvb/matrix -o /newsTopic/cvb -nt 100000 -k 20 -dict /sparse/dictionary.file-0 -dt /newsTopic/cvb-doc-topics -x 5

	echo "Making result in local"

	mahout vectordump -i /newsTopic/cvb -o $outputPath/${fileName}.txt -vs 20 -d /newsTopic/sparse/dictionary.file-0 -dt sequencefile -sort true

	hadoop fs -rm -r -skipTrash /newsTopic

	echo "Finished news topic analysis"


done
mkdir -p $NEWS_REFINED_PATH
java -jar ./jars/NewsDataFomatter.jar $NEWS_TMP_PATH $NEWS_REFINED_PATH
exit

