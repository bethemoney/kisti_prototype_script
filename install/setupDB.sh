EXPECTED_ARGS=1

# DB 생성 쿼리
Q1='CREATE DATABASE kisti;'
# DB 선택 쿼리
Q0='USE kisti;'
# TABLE 생성 쿼리
Q2='CREATE TABLE `kisti_healthcare` (`id` varchar(20) DEFAULT NULL,`pubyear` int(11) DEFAULT NULL, `terms` text,`doctype` varchar(20) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;'
Q3='CREATE TABLE `kisti_nano` (`id` varchar(20) DEFAULT NULL,`pubyear` int(11) DEFAULT NULL,`terms` text,`doctype` varchar(20) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;'
Q4='CREATE TABLE `kisti_semiconductor` (`id` varchar(20) DEFAULT NULL,`pubyear` int(11) DEFAULT NULL,`terms` text,`doctype` varchar(20) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;;'


if [ $# -ne $EXPECTED_ARGS ]
	then
	  echo "Usage: $0  password"
	  exit $E_BADARGS
fi

# 외부 DB 접속 쿼리
Q5="grant all privileges on *.* to 'root'@'%' identified by '$2';flush privileges;"
# mysql 실행 
sudo systemctl start mysqld
# 쿼리 실행
mysql -u root -p$1 -e "${Q1}${Q0}${Q2}${Q3}${Q4}"
mysql -u root -p$1 -e "${Q5}"
# UTF-8이 설정된 mysql 설정파일 변경
mkdir -p /etc/mysql/conf.d/
sudo cp ../my.cnf /etc/
# mysql 재시작
sudo systemctl restart mysqld
