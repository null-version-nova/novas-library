buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.igormaznitsa:jcp:7.1.1")
    }
}

plugins {
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.4-SNAPSHOT" apply false
    // Adds the Kotlin Gradle plugin
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    // OPTIONAL Kotlin Serialization plugin
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
    id("com.replaymod.preprocess") version "48e02ad"
    id("idea")
    id("com.modrinth.minotaur") version "2.+" apply false
}

val versionRelationships = mapOf(
    "1.20.1" to "root",
    "1.18.2" to "1.20.1"
)