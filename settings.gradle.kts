pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ("https://devrepo.kakao.com/nexus/content/groups/public/")
        maven ("https://jitpack.io" )
    }
}

rootProject.name = "Vostom"
include(":app")

 