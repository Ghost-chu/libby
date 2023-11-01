repositories {
    maven("https://repo.spongepowered.org/maven/")
}

dependencies {
    api(project(":libby-core"))

    compileOnly("org.spongepowered:spongeapi:8.1.0") // Higher version requires Java 17
}