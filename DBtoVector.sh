EXPECTED_ARGS=4

if [ $# -ne $EXPECTED_ARGS ]
	then
    
	echo "Usage: $0 DB_IP DB_NAME DB_ID DB_PASSWORD"
	exit $E_BADARGS
fi


. ./setting.sh
mkdir -p $DBOUT_PATH
mkdir -p $VECTORS_PATH
java -jar jars/KISTI_DBtoVector.jar 6 $DBOUT_PATH/ $1 $2 $3 $4

for filename in $DBOUT_PATH/*; do
	file=`basename $filename`
	echo "${VECTORS_PATH}/${file}"
	./word2vec -train ${filename} -output $VECTORS_PATH/${file}.txt -size 200 -window 5 -sample 1e-4 -negative 5 -hs 0 -binary 0 -cbow 1 -iter 3
done

