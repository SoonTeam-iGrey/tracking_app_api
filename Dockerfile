FROM openjdk:17-alpine3.12

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw clean
RUN ./mvnw dependency:resolve

COPY src ./src
RUN ./mvnw package -DskipTests

CMD ["./mvnw", "spring-boot:run"]