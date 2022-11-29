pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "PerqaraAssessment"
include (":app")
include(":data")
include(":domain")
include(":presentation:welcome")
include(":presentation:splashscreen")
include(":presentation:home")
include(":presentation:favorite")
include(":presentation:detail")
include(":common")
include(":navigation")
