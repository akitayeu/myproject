FROM openjdk:17
EXPOSE 8888
COPY build/libs/myproject-0.0.1-SNAPSHOT.jar /opt/app/myproject.jar
ENTRYPOINT ["java","-jar","/opt/app/myproject.jar"]