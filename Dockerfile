#We get the base image that we will use. in this case maven image
FROM maven:3.8.4-openjdk-17 as maven-builder
RUN mkdir /hire
WORKDIR /hire
COPY . .

#We run maven package and skip tests
RUN mvn clean install -DskipTests

FROM openjdk:17 as flyway

FROM adoptopenjdk:11-jre-hotspot

ENV FLYWAY_URL=jdbc:postgresql://hire_db:5432/hire_db
ENV FLYWAY_USER=hire_user
ENV FLYWAY_PASSWORD=hire_password
ENV FLYWAY_LOCATIONS=classpath:db/migration

RUN wget -qO- https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/7.15.0/flyway-commandline-7.15.0-linux-x64.tar.gz | tar xvz -C /opt && \
    ln -s /opt/flyway-7.15.0/flyway /usr/local/bin/

# Copy Flyway migration scripts
COPY src/main/resources/db/migration /flyway/sql/

# Run Flyway migrations
CMD ["flyway", "migrate"]

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
ENTRYPOINT ["java","-jar","app.jar"]