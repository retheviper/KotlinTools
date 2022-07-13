plugins {
    kotlin("jvm") version "1.5.31"
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
    testImplementation("io.kotest:kotest-runner-junit5:5.3.1")
    implementation(kotlin("stdlib-jdk8"))
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