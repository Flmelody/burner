plugins {
    id("java-library")
    id("maven-publish")
}

group = "org.flmelody"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor(project(":burner-generator"))
    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
    publications {
        create<MavenPublication>("mavenJar") {
            groupId = "org.flmelody"
            artifactId = "burner"
            version = "1.0-SNAPSHOT"

            from(components["java"])

            pom {
                name.set("burner")
                description.set("A simple dependency injector for Java.")
                url.set("https://github.com/Flmelody/burner")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("esotericman")
                        name.set("esotericman")
                        email.set("solitude.wind@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/Flmelody/burner.git")
                    developerConnection.set("scm:git:ssh://github.com/Flmelody/burner.git")
                    url.set("https://github.com/Flmelody/burner.git")
                }
            }
        }
    }
}


tasks.test {
    useJUnitPlatform()
}