plugins {
    kotlin("jvm") version "1.5.30"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    // Test
    testImplementation("io.kotest:kotest-runner-junit5:4.6.1")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    test {
        useJUnitPlatform()
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.retheviper"
            artifactId = "kotlintools"
            version = "0.1"

            from(components["java"])
        }
    }
}