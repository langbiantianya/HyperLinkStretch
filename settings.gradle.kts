rootProject.name = "LinkHorizon"


dependencyResolutionManagement {
  versionCatalogs {
    create("spring") {
      version("spring-boot", "3.4.0")
      library(
        "spring-boot-starter-data-redis-reactive",
        "org.springframework.boot",
        "spring-boot-starter-data-redis-reactive"
      ).versionRef("spring-boot")
      library(
        "spring-boot-starter-webflux",
        "org.springframework.boot",
        "spring-boot-starter-webflux"
      ).versionRef("spring-boot")
      library("spring-boot-devtools", "org.springframework.boot", "spring-boot-devtools").versionRef("spring-boot")
      library(
        "spring-boot-configuration-processor",
        "org.springframework.boot",
        "spring-boot-configuration-processor"
      ).versionRef("spring-boot")
      library(
        "spring-boot-starter-test",
        "org.springframework.boot",
        "spring-boot-starter-test"
      ).versionRef("spring-boot")
      library("reactor-test", "io.projectreactor", "reactor-test").version("3.7.0")
      library("junit-platform-launcher", "org.junit.platform", "junit-platform-launcher").version("1.11.3")
    }
    create("utils") {
      library("hutool-all", "cn.hutool", "hutool-all").version("5.8.16")
    }
    create("plugins") {
      plugin("spring-boot", "org.springframework.boot").version("3.4.0")
      plugin("spring-dependency-management", "io.spring.dependency-management").version("1.1.6")
      plugin("graalvm.buildtools", "org.graalvm.buildtools.native").version("0.10.3")
    }
  }
}
