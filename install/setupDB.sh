EXPECTED_ARGS=1

Q1='CREATE DATABASE kisti;'
Q0='USE kisti;'
Q2='CREATE TABLE `kisti_healthcare` (`id` varchar(20) DEFAULT NULL,`pubyear` int(11) DEFAULT NULL, `terms` text,`doctype` varchar(20) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;'
Q3='CREATE TABLE `kisti_nano` (`id` varchar(20) DEFAULT NULL,`pubyear` int(11) DEFAULT NULL,`terms` text,`doctype` varchar(20) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;'
Q4='CREATE TABLE `kisti_semiconductor` (`id` varchar(20) DEFAULT NULL,`pubyear` int(11) DEFAULT NULL,`terms` text,`doctype` varchar(20) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;;'
if [ $# -ne $EXPECTED_ARGS ]
	then
	  echo "Usage: $0 password"
	  exit $E_BADARGS
fi

Q5="grant all privileges on *.* to root@'%' identified by '$1';flush privileges;"
sudo systemctl start mysqld
mysql -u root -p$1 -e "${Q1}${Q0}${Q2}${Q3}${Q4}"
mysql -u root -p$1 -e "${Q5}"

mkdir -p /etc/mysql/conf.d/
sudo cp ../my.cnf /etc/
sudo systemctl restart mysqld
