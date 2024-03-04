# Usage For Library

## Dependency (Required)

### For Groovy
maven { url 'https://jitpack.io' }
### For Kotlin Gradle - build.gradle.kts
maven("https://jitpack.io")


## Include All Modules
### For Groovy 
implementation 'com.github.Yamanaswal:MultipleModuleApp:1.0.3'
### For Kotlin Gradle - build.gradle.kts
implementation("com.github.Yamanaswal:MultipleModuleApp:1.0.3")

## Include No Modules

### For Groovy
implementation("com.github.Yamanaswal:MultipleModuleApp:1.0.3") {
   exclude(module: "pdf_viewer")
   exclude(module: "runtime_permissions")
   exclude(module: "network_module")
   exclude(module: "file_system")
   exclude(module: "common_utils")
   exclude(module: "background_task")
   exclude(module: "fragments")
   exclude(module: "recycler_view")
}


### For Kotlin Gradle - build.gradle.kts
implementation("com.github.Yamanaswal:MultipleModuleApp:1.0.3") {
   exclude(module = "pdf_viewer")
   exclude(module = "runtime_permissions")
   exclude(module = "network_module")
   exclude(module = "file_system")
   exclude(module = "common_utils")
   exclude(module = "background_task")
   exclude(module = "fragment")
   exclude(module = "recycler_view")
}

## License
This project is licensed under the [MIT License](LICENSE).