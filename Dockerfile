FROM eclipse-temurin:17

WORKDIR /opt
 #The working directory of the container, when running file this directry will be created in the container
ENV PORT=8081
EXPOSE 8080


COPY target/*.jar /opt/app.jar
#copy the local build file into the conatiner /app directry

ENTRYPOINT ["java", "-jar", "app.jar"]
#Start the jar file



