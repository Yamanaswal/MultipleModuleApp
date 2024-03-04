pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

rootProject.name = "MultipleModuleApp"
include(":app")
include(":network_module")
include(":common_utils")
include(":runtime_permissions")
include(":pdf_viewer")
include(":file_system")
include(":fragment")
include(":recycler_view")
include(":background_task")
include(":google_play")
