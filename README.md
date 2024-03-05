# Setup For Library

## Dependency (Required)

### For Groovy
maven { url 'https://jitpack.io' }
### For Kotlin Gradle - build.gradle.kts
maven("https://jitpack.io")


## Include All Modules
### For Groovy 
implementation 'com.github.Yamanaswal:MultipleModuleApp:1.0.4'
### For Kotlin Gradle - build.gradle.kts
implementation("com.github.Yamanaswal:MultipleModuleApp:1.0.4")

## Include No Modules

### For Groovy
implementation("com.github.Yamanaswal:MultipleModuleApp:1.0.4") {
   exclude(module: "pdf_viewer")
   exclude(module: "runtime_permissions")
   exclude(module: "network_module")
   exclude(module: "file_system")
   exclude(module: "common_utils")
   exclude(module: "background_task")
   exclude(module: "fragments")
   exclude(module: "recycler_view")
   exclude(module: "google_play")
   exclude(module: "dropdown")
}


### For Kotlin Gradle - build.gradle.kts
implementation("com.github.Yamanaswal:MultipleModuleApp:1.0.4") {
   exclude(module = "pdf_viewer")
   exclude(module = "runtime_permissions")
   exclude(module = "network_module")
   exclude(module = "file_system")
   exclude(module = "common_utils")
   exclude(module = "background_task")
   exclude(module = "fragment")
   exclude(module = "recycler_view")
   exclude(module = "google_play")
   exclude(module = "dropdown")
}


# Usage For Library
## network_module
val service = NetworkCore.createNetworkClient(this, NetworkConfiguration(
baseUrl = "",
okHttpClient = OkHttpClientGenerator().createCore(),
//            convertors = arrayListOf(GsonConverterFactory.create(),ScalarsConverterFactory.create())
), service = ApiInterface::class.java)




## License
This project is licensed under the [MIT License](LICENSE).