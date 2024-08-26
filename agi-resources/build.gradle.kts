plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    `jvm-test-suite`
}

base {
    archivesName = "org.rwtodd.agires"
}

testing {
    suites { 
        val test by getting(JvmTestSuite::class) { 
            useJUnitJupiter() 
        }
    }
}

