FROM openjdk:17-alpine
WORKDIR /usr/src/app
COPY . /usr/src/app
EXPOSE 8080
CMD ["java", "-jar", "target/Rawsy-1.0-SNAPSHOT.jar"]