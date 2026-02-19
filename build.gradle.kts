import org.gradle.jvm.toolchain.JvmVendorSpec
import org.gradle.jvm.toolchain.JavaLanguageVersion

plugins {
    kotlin("jvm") version "2.1.20"
}

group = "org.setu.placemark"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation ("io.github.microutils:kotlin-logging:2.1.23")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
        vendor.set(JvmVendorSpec.ADOPTIUM) // <- or ADOPT, ORACLE, etc.
    }
}

tasks.register<JavaExec>("runMainKt") {
    group = "application"
    description = "Run the main function of MainKt"
    mainClass.set("org.setu.placemark.console.main.MainKt")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}
