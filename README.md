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
#### => Basic Functions for any Apps

1. ##### Exception Handler (App Level) - prevent app from crashing and show user some message. (use in application class)
```text
GlobalExceptionHandler.setupExceptionHandler(object : ExceptionListener {
   override fun uncaughtException(thread: Thread, throwable: Throwable) {
      Log.e("Application Level: ", "uncaughtException: " + throwable.localizedMessage)
      throwable.printStackTrace()
   }
})
```

1. ##### Logger - logs message (wrapper above android native log class) - Note: isDebuggable == true (default)
```text
Logger.e(tag, message) // error logs
Logger.d(tag, message) // debug logs
Logger.i(tag, message) // info logs
Logger.w(tag, message) // warning logs
```

1. ##### Single Click Listener - prevent user from doing multiple clicks on a button
```text
settingsButton.setSingleOnClickListener {
    showSettingsScreen() // click event here
}
```

1. ##### Session Manager - shared preferences class for local storage
```text
//no comments
```



#### 5) background_task

#### 6) fragment

#### 7) recycler_view
#### => Recycler View customs classes and utils

1. #### GenericAdapter - used for custom recycler - adapter
```text
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.visibility = View.VISIBLE

        val demoArr = ArrayList<String>()

        for (i in 1..100) {
            demoArr.add("Date ${i}")
        }

        Log.e("TAG", "demoRecyclerViewLongList: $demoArr")

        val layoutManager = LinearLayoutManager(this) // or GridLayoutManager(this, spanCount)
        rv.layoutManager = layoutManager

        adapter = object : GenericAdapter<String>(R.layout.text_item){
            override fun onBindViewHold(holder: BaseViewHolder<String>, position: Int) {
                val binding = holder.binding as TextItemBinding
                binding.textId.text = getItem(position)
                Log.e("TAG", "onBindViewHold: $position - ${getItem(position)}")
            }

        }

        rv.adapter = adapter

        adapter.updateList(demoArr)
```

1. #### GenericAdapterPagination - used for custom pagination globally
```text
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.visibility = View.VISIBLE

        val demoArr = ArrayList<String>()

        for (i in 1..1000) {
            demoArr.add("Date ${i}")
        }

        val layoutManager = LinearLayoutManager(this) // or GridLayoutManager(this, spanCount)
        rv.layoutManager = layoutManager

        val pageSize = 50 // Number of items per page
        adapter = object : GenericAdapterPagination<String>(
            R.layout.text_item, {
                // Load more data
                adapter.addMoreList(demoArr, pageSize, it)
            }) {

            override fun onBindViewHold(holder: BaseViewHolder<String>, position: Int) {
                val binding = holder.binding as TextItemBinding
                binding.textId.text = getItem(position)
                Log.e("TAG", "onBindViewHold: $position - ${getItem(position)}")
            }
        }

        rv.adapter = adapter

        adapter.setRecyclerViewScrollListener(rv)

        // Load initial data (first page)
        adapter.updateInitialData(demoArr, pageSize)
```

#### 8) google_play
#### 9) dropdown





## License
This project is licensed under the [MIT License](LICENSE).