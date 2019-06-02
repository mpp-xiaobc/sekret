import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("jvm")
    kotlin("kapt")
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("compiler-embeddable"))

    compileOnly("com.google.auto.service:auto-service:1.0-rc4")
    kapt("com.google.auto.service:auto-service:1.0-rc4")
}

val sourcesJar by tasks.creating(Jar::class) {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create("maven", MavenPublication::class.java) {
            from(components["java"])
            artifact(sourcesJar)
            artifactId = "sekret-kotlin-plugin"
        }
    }
}

bintray {
    user = project.findProperty("bintrayUser") as? String ?: System.getenv("BINTRAY_USER")
    key = project.findProperty("bintrayApiKey") as? String ?: System.getenv("BINTRAY_API_KEY")

    setPublications("maven")

    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "sekret-kotlin-plugin"
        vcsUrl = "https://github.com/aafanasev/sekret.git"
        publicDownloadNumbers = true

        setLabels("kotlin", "data class", "toString")
        setLicenses("Apache-2.0")
    })
}