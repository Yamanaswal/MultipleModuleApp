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
include(":common_ui_tools")
include(":runtime_permissions")
include(":pdf_viewer")
include(":file_system")
include(":fragments")
include(":recycler_view")
