import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlin
    id("org.springframework.boot") version Versions.springBoot
    id("io.spring.dependency-management") version Versions.springDependencyManagement
}

allprojects {
    group = "simple.app"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("io.spring.dependency-management")
        plugin("kotlin-spring")
        plugin("org.springframework.boot")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
    }

    sourceSets {
        create("integrationTest") {
            withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
                kotlin.srcDir("src/test-integration/kotlin")
            }
            resources.srcDir("src/test-integration/resources")

            compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath
        }
        create("serviceTest") {
            withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
                kotlin.srcDir("src/test-service/kotlin")
            }
            resources.srcDir("src/test-service/resources")

            compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath
        }
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
                allWarningsAsErrors = true
            }
        }
        withType<JavaCompile> {
            sourceCompatibility = JavaVersion.VERSION_17.majorVersion
            targetCompatibility = JavaVersion.VERSION_17.majorVersion
        }
        withType<Test> {
            useJUnitPlatform()
        }
        val integrationTest = task<Test>("integrationTest") {
            group = "verification"

            testClassesDirs = sourceSets["integrationTest"].output.classesDirs
            classpath = sourceSets["integrationTest"].runtimeClasspath
            shouldRunAfter("test")
        }
        val serviceTest = task<Test>("serviceTest") {
            group = "verification"

            testClassesDirs = sourceSets["serviceTest"].output.classesDirs
            classpath = sourceSets["serviceTest"].runtimeClasspath
        }
        check { dependsOn(integrationTest) }
    }
}
