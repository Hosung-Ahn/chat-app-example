FROM openjdk:17

COPY build/libs/*.jar /app.jar

EXPOSE 8088

ENTRYPOINT ["java","-jar","/app.jar"]
