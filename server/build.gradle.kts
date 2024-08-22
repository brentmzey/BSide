plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "love.bside"
version = "1.0.0"
application {
    mainClass.set("love.bside.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
//            implementation(libs.exposed.core)
//            implementation(libs.exposed.dao)
//            implementation(libs.exposed.jdbc)
//            implementation(libs.exposed.java.time)
    implementation(libs.kgraphql.ktor)
    implementation(libs.kgraphql)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.ktor.server.core)
    implementation(libs.ktor.ktor.server.netty)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.status.pages)
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.uuid)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}
