FROM openjdk:11

WORKDIR /usr/local/app
COPY resources/target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 3000