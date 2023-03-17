import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
    id("org.graalvm.buildtools.native") version "0.9.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal {
        content {
            includeGroup("com.expediagroup")
        }
    }
}

dependencies {
    implementation("com.expediagroup:graphql-kotlin-ktor-server:7.0.0-SNAPSHOT")
    implementation("ch.qos.logback:logback-classic:1.3.5")
    implementation("io.ktor:ktor-server-cio-jvm:2.2.4")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("com.example.ApplicationKt")
}

graalvmNative {
    toolchainDetection.set(false)
    binaries {
        named("main") {
            fallback.set(false)
            verbose.set(true)

            buildArgs.add("--initialize-at-build-time=io.ktor,kotlin,ch.qos.logback,org.slf4j.LoggerFactory")
            buildArgs.add("--trace-object-instantiation=java.util.jar.JarFile")

            buildArgs.add("-H:+InstallExitHandlers")
            buildArgs.add("-H:+ReportUnsupportedElementsAtRuntime")
            buildArgs.add("-H:+ReportExceptionStackTraces")

            imageName.set("graal-server")
//            mainClass.set("org.test.Main")
        }
        metadataRepository {
            enabled.set(true)
        }
    }
}
