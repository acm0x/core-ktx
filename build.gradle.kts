// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(ProjectDependencies.androidGradlePlugin)
        classpath( ProjectDependencies.kotlinGradlePlugin)
        classpath( ProjectDependencies.mavenGradlePlugin)
        classpath( ProjectDependencies.bintrayPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}