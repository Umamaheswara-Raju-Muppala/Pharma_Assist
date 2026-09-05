FROM maven:3.9-eclipse-temurin-17 as app-jar

WORKDIR /Pharmacy

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests=true

FROM eclipse-temurin:17-jre

WORKDIR /Pharmacy

COPY --from=app-jar /Pharmacy/target/PharmaAssist-0.0.1-SNAPSHOT.jar pharmacy.jar

ENTRYPOINT ["java","-jar","pharmacy.jar"]

EXPOSE 8080


 
