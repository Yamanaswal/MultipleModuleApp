# MultiModule Library (Setup)

## Dependency (Module:Project)
### For Groovy
maven { url 'https://jitpack.io' }
### For Kotlin Gradle - build.gradle.kts
maven("https://jitpack.io")

## Dependency (Module:App) - Jitpack
### Include All Modules

### For Groovy 
implementation 'com.github.Yamanaswal:MultipleModuleApp:1.0.4'
### For Kotlin Gradle - build.gradle.kts
implementation("com.github.Yamanaswal:MultipleModuleApp:1.0.4")

### Include No Modules

### For Groovy
```text
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
```

### For Kotlin Gradle - build.gradle.kts
```text
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
```


## Module - (Usage For Library)

#### 1) pdf_viewer
#### => Pdf Viewer Library - Select pdf from phone (media files) and show pdf view on mobile device
```text
startActivity(Intent(this, PdfViewerActivity::class.java))
```

#### 2) runtime_permissions 
#### => Runtime Permissions Library - Open permission dialog
```text
CoroutineScope(Dispatchers.IO).launch {
            PermissionManager.requestPermissions(this@MainActivity, 123,
             Manifest.permission.CAMERA,
             Manifest.permission.BLUETOOTH)
        }
```

#### 3) network_module
#### => Networking Using Retrofit Library - Single Library for all networking tasks
```text
val service = NetworkCore.createNetworkClient(this, NetworkConfiguration(
baseUrl = "www.baseurl.com/",
okHttpClient = OkHttpClientGenerator().createCore(),
//            convertors = arrayListOf(GsonConverterFactory.create(),ScalarsConverterFactory.create())
), service = ApiInterface::class.java)
```

#### 4) common_utils
#### 5) background_task
#### 6) fragment
#### 7) recycler_view
#### 8) google_play
#### 9) dropdown



### Network Module

### Common Utils
```text
GlobalExceptionHandler.setupExceptionHandler(object : ExceptionListener {
   override fun uncaughtException(thread: Thread, throwable: Throwable) {
      Log.e("Application Level: ", "uncaughtException: " + throwable.localizedMessage)
      throwable.printStackTrace()
   }
})
```









## License
This project is licensed under the [MIT License](LICENSE).