FROM openjdk:17

WORKDIR /app

COPY target/Food-Ordering-Application-0.0.1-SNAPSHOT.jar /app/springboot-docker-demo.jar

ENTRYPOINT ["java", "-jar", "/app/springboot-docker-demo.jar"]





