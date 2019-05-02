/**
 * Default configurations for java modules
 */
plugins {
    `java-library`
    kotlin("android")
}

apply {
    from("$rootDir/buildsystem/project_shared.gradle.kts")
    from("$rootDir/buildsystem/jacoco_java.gradle")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
