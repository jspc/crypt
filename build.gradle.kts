plugins {
    `build-scan`
    kotlin("jvm") version "1.2.71"
    maven
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.12")
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"

    publishAlways()
}