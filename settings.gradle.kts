pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net/")
        gradlePluginPortal()
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
rootProject.buildFileName = "root.gradle"

val versions = listOf(
    "1.18.2",
    "1.20.1"
)

versions.forEach {
    include(it)
    project(":$it").apply {
        buildFileName = "../build.gradle"
    }
    include("$it:common")
    project(":$it:common").apply {
        buildFileName = "../../root/common/build.gradle"
    }
    include("$it:fabric")
    project(":$it:fabric").apply {
        buildFileName = "../../root/fabric/build.gradle"
    }
    include("$it:forge")
    project(":$it:forge").apply {
        buildFileName = "../../root/forge/build.gradle"
    }
}