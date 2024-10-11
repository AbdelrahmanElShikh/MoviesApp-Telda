plugins {
    alias(libs.plugins.jetbrainsKotlinJVM)
    `java-library`
}


dependencies {
    testImplementation(libs.junit)
    implementation(libs.gson)
    //mockk
    testImplementation(libs.io.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    //javax inject
    implementation(libs.javax.inject)
}
