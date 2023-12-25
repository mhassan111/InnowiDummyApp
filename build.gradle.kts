plugins {
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.version.catalog.update)
}

buildscript {
    val spotlessVersion = "6.10.0"
    val kotlinVersion = "1.9.10"
    val hiltVersion = "2.48.1"

    repositories {
        mavenCentral()
    }

    dependencies {

    }
}


apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")