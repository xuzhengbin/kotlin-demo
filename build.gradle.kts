import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group="kotlin-project"
version="1.0-SNAPSHOT"

val springBootVersion="2.1.3.RELEASE"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.3.21"
    kotlin("plugin.spring") version "1.3.21"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id( "org.springframework.boot") version "2.1.3.RELEASE" apply false
}

//apply(plugin = "io.spring.dependency-management")

// 仓库地址
repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.3.21")

    }
}

// 添加依赖
dependencies {
    compile("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-jdbc:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-jdbc:$springBootVersion")
    compile("org.springframework.boot:spring-boot-gradle-plugin:2.1.2.RELEASE")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("com.alibaba.otter:canal.common:1.1.2")
    compile("com.alibaba.otter:canal.client:1.1.2")
    compile("com.google.guava:guava:27.0.1-jre")
    compile("mysql:mysql-connector-java:6.0.6")
    compile("com.alibaba:druid:1.0.29")
    compile("com.alibaba:druid-spring-boot-starter:1.1.14")
    compile("junit:junit:4.12")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}