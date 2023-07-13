FROM openjdk:17
WORKDIR /app
COPY myproject-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/app/myproject-0.0.1-SNAPSHOT.jar"]
