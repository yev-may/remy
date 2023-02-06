# Remy
Application for simplifying spaced repetition process

To install database as docker container run
```bash
docker run --name mysql-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
```
To run existed database container run
```bash
docker start mysql-db
```
To create database run
```bash
CREATE DATABASE remy_db;
```