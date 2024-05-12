import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {

    implementation(project(":soccer-domain"))
    implementation("org.springframework.boot:spring-boot-starter-batch")
    testImplementation("org.springframework.batch:spring-batch-test")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
