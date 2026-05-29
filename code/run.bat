@echo off

if not exist db mkdir db

call mvn package -DskipTests

java "-Ddb.url=jdbc:sqlite:db/dev.db" -jar target/proye-is-1.0-SNAPSHOT.jar