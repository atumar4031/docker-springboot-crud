FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/docker-springboot-crud.jar docker-springboot-crud.jar
ENTRYPOINT ["java", "-jar", "/docker-springboot-crud.jar"]