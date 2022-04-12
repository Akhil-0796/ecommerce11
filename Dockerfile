FROM openjdk:8
EXPOSE 8100
ARG JAR_FILE
ADD target/${JAR_FILE} ecommerce.jar
ENTRYPOINT ["java","-jar","ecommerce.jar"]