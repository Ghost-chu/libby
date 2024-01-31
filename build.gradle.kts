plugins {
    `java-library`
    `maven-publish`
    signing
}

allprojects {
    group = "com.ghostchu.lib.unofficial.com.alessiodp.libby"
    version = "2.0.0-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    dependencies {
        compileOnly("org.jetbrains:annotations:24.0.1")
        testCompileOnly("org.jetbrains:annotations:24.0.1")
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

        withJavadocJar()
        withSourcesJar()
    }

    tasks.test {
        useJUnitPlatform()
    }

    publishing {
        repositories {
            val mavenUrl: String? by project
            val mavenSnapshotUrl: String? by project

            (if(version.toString().endsWith("SNAPSHOT")) mavenSnapshotUrl else mavenUrl)?.let { url ->
                maven(url) {
                    val mavenUsername: String? by project
                    val mavenPassword: String? by project
                    if(mavenUsername != null && mavenPassword != null) {
                        credentials {
                            username = mavenUsername
                            password = mavenPassword
                        }
                    }
                }
            }
        }

        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])

                pom {
                    name.set("Libby")
                    description.set("A runtime dependency management library for plugins running in Java-based Minecraft server platforms. (unoffical fork)")
                    url.set("https://github.com/Ghost-chu/libby")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/license/mit/")
                        }
                    }

                    developers {
                        developer {
                            id = "Ghost-chu"
                            email = "ghostchu@qq.com"
                        }
                    }

                    scm {
                        connection = "scm:git:git://github.com/Ghost-chu/libby.git"
                        developerConnection = "scm:git:git@github.com:Ghost-chu/libby.git"
                        url = "https://github.com/Ghost-chu/libby"
                    }
                }
            }
        }
    }

    signing {
        setRequired {
            gradle.taskGraph.allTasks.any { it is PublishToMavenRepository }
        }
        useGpgCmd()
        sign(publishing.publications["mavenJava"])
    }
}