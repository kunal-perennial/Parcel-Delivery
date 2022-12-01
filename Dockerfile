FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} parcel-delivery.jar
ENTRYPOINT ["java","-jar","/parcel-delivery.jar"]



