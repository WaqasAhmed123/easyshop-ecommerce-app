// Top-level build file where you can add configuration options common to all sub-projects/modules.


plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.1" apply false
    id("com.google.protobuf") version "0.8.12" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false

}
buildscript{
    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.9.4")
    }
}