import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.jpa") version "1.9.22"
}

dependencies {

    implementation("software.amazon.awssdk:s3")
    implementation("software.amazon.awssdk:bom:2.17.100")
    implementation(platform("software.amazon.awssdk:bom:2.17.100"))

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("com.mysql:mysql-connector-j")
}


tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
