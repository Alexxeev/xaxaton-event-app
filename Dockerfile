FROM openjdk:21
WORKDIR /app
COPY target/xaxaton-event-app-*.jar /xaxaton-event-app.jar
CMD ["java", "-jar", "/xaxaton-event-app.jar"]