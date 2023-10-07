plugins {
    id("java")
}

group = "org.flmelody"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.flmelody:reflections:0.10.2")
    implementation("com.squareup:javapoet:1.13.0")
    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
    compileOnly("com.google.auto.service:auto-service:1.1.1")
    annotationProcessor("com.google.auto.service:auto-service:1.1.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    val compilerArgs = options.compilerArgs
    compilerArgs.add("-XprintProcessorInfo")
    compilerArgs.add("-XprintRounds")
}