FROM openjdk:8-jre-alpine
ADD target/scala-2.13/rectangles-assembly-1.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]