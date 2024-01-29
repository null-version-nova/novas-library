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

preprocess {
    val mc12001 = createNode("1.20.1", 12001, "mojmap")
    val mc11802 = createNode("1.18.2", 11802, "mojmap")

    mc12001.link(mc11802,file("versions/1.20.1/mapping.txt"))
//    mc11802.link(mc12001,file("versions/1.18.2/mappings.txt"))
}