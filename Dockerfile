FROM openjdk:21-slim

WORKDIR /app

COPY target/tech_challenge_1-0.0.1-SNAPSHOT.jar .

RUN ls

CMD ["java", "-jar", "tech_challenge_1-0.0.1-SNAPSHOT.jar"]