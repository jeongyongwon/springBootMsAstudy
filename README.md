# Kafka 활용을 위한 코드

-----

> `wurstmeister/kafka` 은 opt 밑에 kafka 폴더가 존재한다.

## Kafka Topic 생성

```bash
./bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092 --partitions 1
```



## Kafka Topic 목록 확인

```BASH
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
```



## Kafka Topic 정보 확인

```bash
./bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
```



## Message 생산

```bash
./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic quickstart-events
```



## Message 소비

```bash
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic quickstart-events --from-beginning
```







----

# Docker - MYSQL - H2 console



## DockerFILE 작성

```dockerfile
FROM mariadb:10.3

ENV MYSQL_ALLOW_EMPTY_PASSWORD true
ENV MYSQL_DATABASE mydb

EXPOSE 3306
```



## MariaDB image 및 container 생성

```bash
docker build -t cozy_mysql -f ./Dockerfile .
docker run -d -p 3306:3306 --name cozy_mysql cozy_mysql .
```



## MariaDB 접속

```bash
docker exec -it cozy_mysql bash
mysql -u root
```



## Database 생성

```mysql
create database mydb;
use mydb;
show tables;
```



## H2 Console 사용을 위한 계정 생성 및 권한 부여

```mysql
CREATE USER 'asdfg0237'@'172.17.0.1' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON *.* TO 'asdfg0237'@'172.17.0.1' WITH GRANT OPTION;
flush privileges;
exit
```

