plugins {
  java
  alias(plugins.plugins.spring.boot)
  alias(plugins.plugins.spring.dependency.management)
  alias(plugins.plugins.graalvm.buildtools)
}

group = "com.kxxnzstdsw"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(spring.spring.boot.starter.data.redis.reactive)
  implementation(spring.spring.boot.starter.webflux)
  implementation(utils.hutool.all)
  developmentOnly(spring.spring.boot.devtools)
  annotationProcessor(spring.spring.boot.configuration.processor)
  testImplementation(spring.spring.boot.starter.test)
  testImplementation(spring.reactor.test)
  testRuntimeOnly(spring.junit.platform.launcher)
}

tasks.withType<Test> {
  useJUnitPlatform()
}
