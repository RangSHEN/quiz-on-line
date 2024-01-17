FROM eclipse-temurin:17

LABEL maintainer="alexrang007@gmail.com"

WORKDIR /app

COPY target/quiz-online-0.0.1-SNAPSHOT.jar /app/quiz-online.jar

ENTRYPOINT ["java", "-jar", "quiz-online.jar"]