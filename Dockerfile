FROM eclipse-temurin:17-jre-alpine
EXPOSE 8083
COPY target/poststeadauditservice-1.0.0.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
