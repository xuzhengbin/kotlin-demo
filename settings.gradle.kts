pluginManagement {
    repositories {
        maven { url = uri("http://dl.bintray.com/kotlin/kotlin-eap") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
        jcenter()
        gradlePluginPortal()
    }
}

rootProject.name = "kotlin-demo"

