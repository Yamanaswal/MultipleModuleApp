package com.yaman.file_system

interface FileSystemListener {
    fun getAsHtmlFile(path: String){}
    fun getAsTextFile(path: String){}
    fun getAsString(path: String){}
    fun onError(e: Exception)
}