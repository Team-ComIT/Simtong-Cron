plugins {
    id(Plugins.SPRING_BOOT) version PluginVersions.SPRING_BOOT_VERSION
    id(Plugins.DEPENDENCY_MANAGEMENT) version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin(Plugins.SPRING) version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin(Plugins.JPA) version PluginVersions.JPA_VERSION
}

dependencies {

    // impl project
    implementation(project(":simtong-application"))

    // kotlin
    implementation(Dependencies.JACKSON)

    // database
    implementation(Dependencies.SPRING_DATA_JPA)
    runtimeOnly(Dependencies.MYSQL_CONNECTOR)

    // querydsl
    implementation(Dependencies.QUERYDSL)
    kapt(Dependencies.QUERYDSL_PROCESSOR)

    // mapstruct
    implementation(Dependencies.MAPSTRUCT)
    kapt(Dependencies.MAPSTRUCT_PROCESSOR)

    // openfeign
    implementation(Dependencies.OPEN_FEIGN)

    // configuration
    annotationProcessor(Dependencies.CONFIGURATION_PROCESSOR)
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
        arg("mapstruct.unmappedTargetPolicy", "ignore")
        arg("scheduling.defaultZone", "Asia/Seoul")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}