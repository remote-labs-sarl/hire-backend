FROM maven:3.8.4-openjdk-17 as maven-builder
WORKDIR /hire
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db-postgresql-fra1-86535-do-user-13682969-0.b.db.ondigitalocean.com:25060/defaultdb
ENV SPRING_DATASOURCE_USERNAME=doadmin
ENV SPRING_DATASOURCE_PASSWORD=AVNS_dgpwVRVKUZS9lsmwPgd

COPY --from=maven-builder /hire/target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]