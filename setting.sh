#setting.sh

######## 수정 가능 ########
# 프로토타입 설치 및 실행 루트 경로
PROTOTYPE_PATH=~/kisti_prototype/

######## 수정 가능 ########
# nodejs 서버 웹페이지 저장 경로 
NODEJS_PATH=$PROTOTYPE_PATH/nodeserver/
# nodejs 설치 폴더 경로
NODE_PATH=$PROTOTYPE_PATH/node/
# word2vec 결과파일 저장 경로
VECTORS_PATH=$PROTOTYPE_PATH/vector/
# 뉴스 데이터 명사 추출된 경로
NEWS_PATH=$PROTOTYPE_PATH/news/
# 뉴스 데이터 중간 임시파일 저장 경로
NEWS_TMP_PATH=$PROTOTYPE_PATH/newstmp/
# 뉴스 데이터 수집 경로
NEWS_RAW_PATH=$PROTOTYPE_PATH/news_row/data/
# JAR 파일 위치 경로
JARS_PATH=$PROTOTYPE_PATH/jars/
# DB에서 추출한 임시 경로
DBOUT_PATH=$PROTOTYPE_PATH/dbout/
# NDSL 문서 추출 경로
COLLECT_PATH=$PROTOTYPE_PATH/collects/

######## 수정 금지 ########
# JSON으로 변경된 뉴스데이터 수집경로
NEWS_REFINED_PATH=$NODEJS_PATH/routes/refinednews/
# nodejs route파일 경로
NODEJS_ROUTE_PATH=$NODEJS_PATH/routes/

