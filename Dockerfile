FROM gradle:jdk21 AS build

WORKDIR /app

COPY . .

ENV GRADLE_USER_HOME=~/.gradle

COPY ./init.gradle.kts ~/.gradle/init.gradle.kts 

RUN gradle :jar

FROM eclipse-temurin:21-jre-alpine AS runner

WORKDIR /app

COPY --from=build /app/build/libs/HyperLinkStretch-0.0.1-SNAPSHOT.jar /app/HyperLinkStretch.jar
COPY --from=build /app/src/main/resources/application.properties /app/application.properties

EXPOSE 8080

CMD ["java", "-jar", "HyperLinkStretch.jar"]
