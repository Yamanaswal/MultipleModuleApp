package com.yaman.file_system

interface FileSystemListener {
    fun getAsHtmlFile()

    fun getAsTextFile(text: String)
}