val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

jar.enabled = false
bootJar.enabled = true
bootJar.archiveBaseName.set("simple-app-basic")
