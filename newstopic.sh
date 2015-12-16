#!/bin/bash

# 환경설정파일 로드
. ./setting.sh

# setting.sh에 설정된 NEWS_PATH가 데이터 입력 경로, NEWS_TMP_PATH가 데이터 출력 경로
# NEWS_PATH 하위에 있는 디렉토리를 검사하여 디렉토리별로 토픽분석 수행

NUMBER_OF_DIR=$(find $NEWS_PATH  -maxdepth 1 -type d | wc -l);
let NUMBER_OF_DIR++

# 결과 저장 폴더 생성
mkdir -p $NEWS_TMP_PATH
i=0;
for ((i=2; i< ($NUMBER_OF_DIR); i++ ))do
	
	# 입력 출력. JAR파일 경로 설정
	inputPath=$(echo $NEWS_PATH | sed 's/.$//')
	outputPath=$NEWS_TMP_PATH
	jarPath=$JARS_PATH
	NAME_OF_DIR=$(find $inputPath  -maxdepth 1 -type d | sed -n ${i}p)
	inputPath=$NAME_OF_DIR
	fileName=`basename $inputPath`

	# 단어 추출 프로그램 실행
	java -jar $jarPath/NNExtractor.jar -i $inputPath -o $jarPath

	# mahout에서 사용가능한 sequence file 생성
	# seq파일을 단순 변환하는 것 뿐이므로 속도를 줄이기 위해 LOCAL로 실행
	export MAHOUT_LOCAL="TRUE"
	mahout seqdirectory -i $jarPath/txtdata -o $jarPath/seqfile

	# Hadoop 기반 실행으로 변경
	export MAHOUT_LOCAL=""

	# 하둡저장소 생성
	hadoop fs -mkdir /newsTopic

	# seq파일을 하둡에 업로드
	echo "Uploading seqfile to hdfs"
	hadoop fs -copyFromLocal $jarPath/seqfile /newsTopic/seqfile

	# 임시파일 제거
	rm -r $jarPath/txtdata
	rm -r $jarPath/seqfile

	# 토픽 분석을 위한 단어 빈도 계산 
	echo "Starting vectorization"
	mahout seq2sparse -i /newsTopic/seqfile/part-m-00000 -o /newsTopic/sparse --maxDFPercent 90

	# LDA 수행을위한 단어 matrix로 변경
	echo "Starting to make matrix"
	mahout rowid -i /newsTopic/sparse/tf-vectors -o /newsTopic/tf-vectors-cvb

	# LDA 수행
	echo "Starting LDA topic analysis"
	mahout cvb -i /newsTopic/tf-vectors-cvb/matrix -o /newsTopic/cvb -nt 100000 -k 20 -dict /sparse/dictionary.file-0 -dt /newsTopic/cvb-doc-topics -x 5

	# 결과 파일이 vector 파일이므로 일반 파일로 변경
	echo "Making result in local"
	mahout vectordump -i /newsTopic/cvb -o $outputPath/${fileName}.txt -vs 20 -d /newsTopic/sparse/dictionary.file-0 -dt sequencefile -sort true
	
	# 토픽 분석 중 생긴 임시 파일 제거
	hadoop fs -rm -r -skipTrash /newsTopic
	echo "Finished news topic analysis"

done
# 전체 파일을 웹페이지에 사용가능한 JSON 포맷으로 변경
mkdir -p $NEWS_REFINED_PATH
java -jar ./jars/NewsDataFomatter.jar $NEWS_TMP_PATH $NEWS_REFINED_PATH
exit

