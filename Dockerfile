FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
