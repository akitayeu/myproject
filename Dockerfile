FROM openjdk:17
WORKDIR /app
COPY myproject.jar .
ENTRYPOINT ["java","-jar","app/myproject.jar"]