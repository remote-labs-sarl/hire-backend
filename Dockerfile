FROM maven:3.8.4-openjdk-17 as maven-builder
WORKDIR /hire
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17

COPY --from=maven-builder /hire/target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]
CMD ["--spring.profiles.active=dev"]