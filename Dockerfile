FROM gradle:jdk21 AS base

WORKDIR /app

COPY ./gradle .
COPY ./build.gradle.kts .
COPY ./gradlew .
COPY ./gradlew.bat .
COPY ./settings.gradle.kts .
COPY ./init.gradle.kts ~/.gradle/init.gradle.kts

ENV GRADLE_USER_HOME=~/.gradle

RUN gradle --refresh-dependencies

FROM base AS build

WORKDIR /app

COPY . .

RUN gradle build

FROM eclipse-temurin:21-jre-alpine AS runner

WORKDIR /app

COPY --from=build /app/build/libs/HyperLinkStretch-0.0.1-SNAPSHOT.jar ./HyperLinkStretch.jar
COPY --from=build /app/src/main/resources/application.properties ./application.properties

EXPOSE 8080

CMD ["java", "-jar", "HyperLinkStretch.jar"]
