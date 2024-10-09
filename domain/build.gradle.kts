plugins {
    alias(libs.plugins.jetbrainsKotlinJVM)
    `java-library`
}


dependencies {
    testImplementation(libs.junit)
    implementation(libs.gson)
    //javax inject
    implementation(libs.javax.inject)
}
