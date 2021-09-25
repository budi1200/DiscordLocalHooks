plugins {
    idea
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "si.budimir"
version = "1.2.0"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    maven { url = uri("https://jitpack.io") }
    maven { url= uri("https://repo.codemc.org/repository/maven-public/")}
}

dependencies {
    compileOnly((kotlin("stdlib")))
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0-RC")
    compileOnly("com.squareup.okhttp3:okhttp:4.9.1")
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")

    implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions{
        jvmTarget = JavaVersion.VERSION_16.toString()
    }
}

tasks.processResources {
    expand("version" to project.version)
}

tasks.shadowJar {
    // This makes it shadow only stuff with "implementation"
    project.configurations.implementation.get().isCanBeResolved = true
    configurations = mutableListOf(project.configurations.implementation.get())

    minimize {}
}

tasks.register("copyToServer"){
    dependsOn("shadowJar")

    doLast {
        copy {
            from("build/libs/DiscordLocalHooks-" + project.version + "-all.jar")
            into("C:\\Users\\budi1\\Desktop\\Custom Plugins\\00-server\\plugins")
        }
    }
}