import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.40" apply false
}

allprojects {

    group = "dev.afanasev"
    version = "0.0.3"

    repositories {
        jcenter()
    }

}

subprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}