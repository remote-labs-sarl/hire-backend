#We get the base image that we will use. in this case maven image
FROM maven:3.8.4-openjdk-17 as maven-builder
WORKDIR /hire
COPY . .

#We run maven package and skip tests
RUN mvn clean install -DskipTests

#We get our runtime jdk
FROM openjdk:17

COPY --from=maven-builder hire/target/*.jar app.jar

#Set DB environment variables for our app to connect to the database also running on Docker
ENV POSTGRES_USER=hire_user \
    POSTGRES_PASSWORD=hire_password \
    POSTGRES_HOST=hire_db \
    POSTGRES_PORT=5432 \
    POSTGRES_DB=hire_db

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar", "--spring.profiles.active=dev"]