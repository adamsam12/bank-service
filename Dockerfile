FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bank-account.jar
ENTRYPOINT ["java","-jar","/bank-account.jar"]