subprojects {
  version = "1.0.0"
  group = "org.rwtodd"

  repositories {
    mavenCentral()
  }
 
  tasks.withType<JavaCompile>().configureEach {
    options.release = 21
  }

  tasks.withType<Test>().configureEach {
        // useJUnitPlatform()
        testLogging {
                events("skipped", "failed")
        }
    }
}
