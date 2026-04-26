plugins {
    id("java-library")
    id("maven-publish")
    id("net.neoforged.moddev") version "2.0.30-beta"
}

version = "${property("minecraft_version")}-${property("mod_version")}"
if (System.getenv("BUILD_NUMBER") != null) {
    version = "$version.${System.getenv("BUILD_NUMBER")}"
}
val baseArchivesName = project.property("mod_id").toString()
base {
    archivesName.set(project.property("mod_id").toString())
}
group = "${property("mod_group_id")}"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
}

val localRuntime: Configuration by configurations.creating
configurations.runtimeClasspath {
    extendsFrom(localRuntime)
}

neoForge {
    version.set(project.property("neo_version").toString())

    parchment {
        mappingsVersion.set(project.property("parchment_mappings_version").toString())
        minecraftVersion.set(project.property("parchment_minecraft_version").toString())
    }

    setAccessTransformers(
        "src/main/resources/META-INF/accesstransformer.cfg",
        "src/main/resources/META-INF/recipebuilders.cfg",
        "src/main/resources/META-INF/blockproperties.cfg",
        "src/main/resources/META-INF/renderstates.cfg"
    )

    runs {
        register("client") {
            client()

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("data") {
            data()
            programArguments.addAll(
                "--mod", project.property("mod_id").toString(),
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            jvmArgument("-Dmixin.debug=true")
            jvmArgument("-Xmx4G")
            systemProperty("neoforge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        create("${property("mod_id")}") {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets {
    main {
        resources.srcDir("src/generated/resources")
    }
}

repositories {
    flatDir {
        dirs("lib")
    }
    mavenLocal()
    mavenCentral()
    maven {
        name = "Greenhouse"
        url = uri("https://maven.greenhouse.lgbt/snapshots")
    }
    maven {
        name = "Curios maven"
        url = uri("https://maven.theillusivec4.top/")
    }
    maven {
        name = "JEI maven"
        url = uri("https://dvs1.progwml6.com/files/maven")
    }
    maven {
        name = "tterrag maven"
        url = uri("https://maven.tterrag.com/")
    }
    maven {
        name = "BlameJared maven"
        url = uri("https://maven.blamejared.com/")
    }
    maven {
        name = "KosmX's maven"
        url = uri("https://maven.kosmx.dev/")
    }
    maven {
        name = "Curse Maven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
    maven {
        url = uri("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
        content {
            includeGroup("software.bernie.geckolib")
        }
    }
    maven {
        name = "ModMaven"
        url = uri("https://modmaven.dev")
    }
    maven {
        name = "jitpack"
        url = uri("https://jitpack.io")
        content {
            includeGroup("io.github")
        }
    }
    maven {
        name = "OctoStudios"
        url = uri("https://maven.octo-studios.com/releases")
    }

}

dependencies {
    // JEI Dependency
    compileOnlyApi(("mezz.jei:jei-${project.property("minecraft_version")}-neoforge-api:${project.property("jei_version")}"))
    runtimeOnly(("mezz.jei:jei-${project.property("minecraft_version")}-neoforge:${project.property("jei_version")}"))

    compileOnlyApi(("team.lodestar.lodestone:lodestone:${property("minecraft_version")}-${property("lodestone_version")}"))
    runtimeOnly(("team.lodestar.lodestone:lodestone:${property("minecraft_version")}-${property("lodestone_version")}"))

    ///compileOnly(("curse.maven:farmers-delight-398521:6597295"))
    ///localRuntime(("curse.maven:farmers-delight-398521:6597295"))
    compileOnly(("vectorwing.farmersdelight:FarmersDelight:1.21.1-1.3.0-rc2"))
    localRuntime(("vectorwing.farmersdelight:FarmersDelight:1.21.1-1.3.0-rc2"))

    localRuntime(("curse.maven:appleskin-248787:5586600"))
}

val generateModMetadata by tasks.registering(ProcessResources::class) {
    val replaceProperties = mapOf(
        "minecraft_version" to project.findProperty("minecraft_version") as String,
        "minecraft_version_range" to project.findProperty("minecraft_version_range") as String,
        "neo_version" to project.findProperty("neo_version") as String,
        "neo_version_range" to project.findProperty("neo_version_range") as String,
        "loader_version_range" to project.findProperty("loader_version_range") as String,
        "mod_id" to project.findProperty("mod_id") as String,
        "mod_name" to project.findProperty("mod_name") as String,
        "mod_license" to project.findProperty("mod_license") as String,
        "mod_version" to project.findProperty("mod_version") as String,
        "mod_authors" to project.findProperty("mod_authors") as String,
        "mod_description" to project.findProperty("mod_description") as String,
        "lodestone_version_range" to project.findProperty("lodestone_version_range") as String
    )
    inputs.properties(replaceProperties)
    expand(replaceProperties)

    filesMatching("**/*.java") {
        exclude()
    }

    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}
sourceSets["main"].resources.srcDir(generateModMetadata)
neoForge.ideSyncTask(generateModMetadata)

java {
//    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            artifactId = "${property("mod_id")}"
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("file://${System.getenv("local_maven")}")
        }
    }
}

idea {
    module {
        for (fileName in listOf("run", "out", "logs")) {
            excludeDirs.add(file(fileName))
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}