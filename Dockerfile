FROM openjdk:17-oracle

VOLUME /paragon-main

EXPOSE 8080

ARG JAR_FILE=target/paragon-main-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} paragon-main.jar

ENTRYPOINT ["java","-jar","/paragon-main.jar"]