plugins {
    kotlin(Plugins.ALLOPEN) version PluginVersions.ALLOPEN_VERSION
}

dependencies {

    // spring transaction
    implementation(Dependencies.SPRING_TRANSACTION)

}

allOpen {
    annotation("team.comit.simtong.global.annotation.Job")
    annotation("team.comit.simtong.global.annotation.ReadOnlyJob")
}