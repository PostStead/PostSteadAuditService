FROM eclipse-temurin:17-jre-alpine
EXPOSE 8083
COPY target/poststeadauditservice-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
