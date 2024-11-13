import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    id("org.flywaydb.flyway") version "9.22.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Exposed ORM - Updated to 0.43.0
    implementation("org.jetbrains.exposed:exposed-core:0.43.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.43.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.43.0")

    // PostgreSQL JDBC Driver
    implementation("org.postgresql:postgresql:42.5.4")

    // HikariCP for Connection Pooling
    implementation("com.zaxxer:HikariCP:5.0.1")

    // Flyway Core
    implementation("org.flywaydb:flyway-core:9.22.0")

    // Logging (Optional but Recommended)
    implementation("org.slf4j:slf4j-simple:2.0.7")

    // Testing
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("app.AppKt")
}

//Flyway configuration
flyway {
    url = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/datarpg"
    user = System.getenv("DB_USER") ?: "datarpguser"
    password = System.getenv("DB_PASSWORD") ?: "password"
    locations = arrayOf("filesystem:src/main/resources/db/migration")
}