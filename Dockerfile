FROM openjdk:18
EXPOSE 8080
ADD target/spring-boot-ecommerce2.jar spring-boot-ecommerce2.jar
ENTRYPOINT ["java", "-jar", "spring-boot-ecommerce2.jar"]