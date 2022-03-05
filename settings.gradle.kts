rootProject.name = "simple-app-rest"

listOf(
    "basic"
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/apps/$it")
}
