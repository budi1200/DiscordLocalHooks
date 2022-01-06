plugins {
    idea
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "si.budimir"
version = "1.4.0"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    maven { url = uri("https://jitpack.io") }
    maven { url= uri("https://repo.codemc.org/repository/maven-public/")}
}

dependencies {
    compileOnly((kotlin("stdlib")))
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    compileOnly("com.squareup.okhttp3:okhttp:4.9.3")
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")

    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.processResources {
    expand("version" to project.version)
}

tasks.shadowJar {
    // This makes it shadow only stuff with "implementation"
    project.configurations.implementation.get().isCanBeResolved = true
    configurations = mutableListOf(project.configurations.implementation.get()) as List<FileCollection>?

    minimize {}
}

tasks.register("copyToServer"){
    dependsOn("shadowJar")

    doLast {
        copy {
            from("build/libs/DiscordLocalHooks-" + project.version + "-all.jar")
            into("../00-server/plugins")
        }
    }
}