import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.4")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.4")
    testImplementation("io.kotest:kotest-property:5.5.4")

    testImplementation("io.rest-assured:json-schema-validator:5.3.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")

    testImplementation("io.kotest:kotest-extensions-allure:4.4.3")
    testImplementation("io.qameta.allure:allure-rest-assured:2.20.0")
    testImplementation("io.qameta.allure:allure-attachments:2.20.1")

}

tasks.test {
    useJUnitPlatform()
}

allure {
    version.set("2.20.0")

    adapter {
        autoconfigure.set(true)
        autoconfigureListeners.set(true)
        aspectjWeaver.set(true)

        frameworks {
            junit5 {
                enabled.set(true)
                autoconfigureListeners.set(true)
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}