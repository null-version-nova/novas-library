pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net/")
        maven("https://jitpack.io")
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.replaymod.preprocess" -> {
                    useModule("com.github.replaymod:preprocessor:${requested.version}")
                }
            }
        }
    }
}

include("root")
project(":root").apply {
    buildFileName = "../build.gradle"
}
include("root:common")
include("root:fabric")
include("root:forge")

rootProject.name = "novaslibrary"
rootProject.buildFileName = "root.gradle.kts"

val versions = listOf(
    "1.18.2",
    "1.20.1"
)

versions.forEach { version ->
    include(version)
    project(":$version").apply {
        buildFileName = "../build.gradle"
    }
    include(":$version:common")
    project(":$version:common").apply {
        buildFileName = "../../root/common/build.gradle"
    }
    include(":$version:fabric")
    project(":$version:fabric").apply {
        buildFileName = "../../root/fabric/build.gradle"
    }
    include(":$version:forge")
    project(":$version:forge").apply {
        buildFileName = "../../root/forge/build.gradle"
    }
}