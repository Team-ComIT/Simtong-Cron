object  Dependencies {

    // kotlin
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_JDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    // java servlet
    const val JAVA_SERVLET = "javax.servlet:javax.servlet-api:${DependencyVersions.SERVLET}"

    // querydsl
    const val QUERYDSL = "com.querydsl:querydsl-jpa:${DependencyVersions.QUERYDSL}"
    const val QUERYDSL_PROCESSOR = "com.querydsl:querydsl-apt:${DependencyVersions.QUERYDSL}:jpa"

    // database
    const val SPRING_DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val MYSQL_CONNECTOR = "mysql:mysql-connector-java"

    // transaction
    const val SPRING_TRANSACTION = "org.springframework:spring-tx:${DependencyVersions.SPRING_TRANSACTION}"

    // mapstruct
    const val MAPSTRUCT = "org.mapstruct:mapstruct:${DependencyVersions.MAPSTRUCT_VERSION}"
    const val MAPSTRUCT_PROCESSOR = "org.mapstruct:mapstruct-processor:${DependencyVersions.MAPSTRUCT_VERSION}"

    // openfeign
    const val OPEN_FEIGN = "org.springframework.cloud:spring-cloud-starter-openfeign:${DependencyVersions.OPEN_FEIGN_VERSION}"

    // test
    const val SPRING_TEST = "org.springframework.boot:spring-boot-starter-test:${PluginVersions.SPRING_BOOT_VERSION}"

    // configuration
    const val CONFIGURATION_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"
}