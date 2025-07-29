# Maven

FROM openjdk:17-jdk

# Jar 파일의 위치
ARG JAR_FILE=target/*.jar

# app.jar는 경우에 따라 이름 변경
COPY ${JAR_FILE} app.jar

# 8080 port 사용
EXPOSE 8080

# docker run 시 실행할 필수 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]
