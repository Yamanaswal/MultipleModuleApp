package com.yaman.file_system

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class FileSystem(private val context: Context) {

    //To read/write files in your app's private internal storage,
    // you can use the getFilesDir() and getCacheDir() methods provided by the Context class.
    fun readFile() {
        val filesDir: File = File(context.filesDir, "example.txt")
        val cacheDir: File = File(context.cacheDir, "example.txt")
        Log.e("TAG", "readFile1: $filesDir")
        Log.e("TAG", "readFile2: $cacheDir")
    }

    // To read/write files in external storage,
    // you'll need the WRITE_EXTERNAL_STORAGE permission (in older Android versions)
    // or use the MediaStore for modern Android versions.
    fun readFileFromSdCard(
        fileToRead: String,
        folderPath: String = "",
        type: FileSystemType = FileSystemType.TEXT,
        fileSystemListener: FileSystemListener
    ) {

        lateinit var inputStream: FileInputStream

        try {
            val sdCard: File = Environment.getExternalStorageDirectory() // Get the root directory of the SD card
            val folder = File(sdCard, folderPath) // Specify the folder on the SD card

            // Check if the specified folder exists
            if (folder.exists() && folder.isDirectory) {

                // The folder exists, so you can access files within it
                val file = File(folder, fileToRead) // Specify the file you want to read in the folder

                // The file exists, you can read it
                if (file.exists()) {
                    // Now you can read data from the file using the inputStream
                    inputStream = FileInputStream(file)

                    Log.d(TAG, "readFileFromSdCard: Directory: $sdCard , File: $file")

                    handleFileWriteBasedOnType(type, inputStream, fileSystemListener)
                }
                //file not exists
                else {
                    Log.e(TAG, "readFileFromSdCard: The specified file doesn't exist in the folder")
                }
            }
            //folder does not exist
            else {
                Log.e(TAG, "readFileFromSdCard: The specified folder doesn't exist on the SD card")
            }

        } catch (e: Exception) {
            handleErrors(e,fileSystemListener)
        } finally {
            // Remember to close the stream when you're done
            try {
                inputStream.close() // Close the input stream
            } catch (e: IOException) {
                // Handle any exceptions while closing the stream
            }
        }

    }

    fun readFileFromInternalStorage(
        fileToRead: String,
        folderPath: String = "",
        type: FileSystemType = FileSystemType.TEXT,
        fromDir: DirectoryType = DirectoryType.LOCAL,
        fileSystemListener: FileSystemListener
    ) {

        lateinit var inputStream: FileInputStream

        try {
            val folder = File(if(fromDir == DirectoryType.LOCAL) context.filesDir else context.cacheDir, folderPath) // Specify the folder

            // Check if the specified folder exists
            if (folder.exists() && folder.isDirectory) {

                // The folder exists, so you can access files within it
                val file = File(folder, fileToRead) // Specify the file you want to read in the folder

                // The file exists, you can read it
                if (file.exists()) {
                    // Now you can read data from the file using the inputStream
                    inputStream = FileInputStream(file)

                    Log.d(TAG, "readFileFromSdCard: File: $file")

                    handleFileWriteBasedOnType(type, inputStream, fileSystemListener)
                }
                //file not exists
                else {
                    Log.e(TAG, "readFileFromSdCard: The specified file doesn't exist in the folder")
                }
            }
            //folder does not exist
            else {
                Log.e(TAG, "readFileFromSdCard: The specified folder doesn't exist on the SD card")
            }

        } catch (e: Exception) {
            handleErrors(e,fileSystemListener)
        } finally {
            // Remember to close the stream when you're done
            try {
                inputStream.close() // Close the input stream
            } catch (e: IOException) {
                // Handle any exceptions while closing the stream
            }
        }

    }

    private fun handleFileWriteBasedOnType(
        type: FileSystemType,
        inputStream: FileInputStream,
        fileSystemListener: FileSystemListener
    ) {
        //For 1 Kb
        val buffer = ByteArray(1024) // Create a buffer to read data in chunks

        var bytesRead: Int // Variable to track the number of bytes read


        when (type) {
            FileSystemType.TEXT -> {
                // Create a temporary file to store the content
                val tempFile = File.createTempFile("temp_text_file", ".txt")

                // Create an output stream for the temporary file
                val outputStream = FileOutputStream(tempFile)


                // Read data in chunks (e.g., 1KB at a time) from the input stream
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    // Process the data here, e.g., write it to another file, display it, etc.
                    // Write the data to the temporary file
                    outputStream.write(buffer, 0, bytesRead)
                    // In this example, we'll just print the data to the console.
                    System.out.write(buffer, 0, bytesRead) // Print the data to the console
                }

                // Close the output stream
                outputStream.close()

                //Return File absolutePath
                fileSystemListener.getAsTextFile("file://${tempFile.absolutePath}")

                // In Android, the File.createTempFile() method is a convenient way to
                // generate temporary files for short-term use.
                // It helps you avoid naming conflicts and simplifies cleanup,
                // as the system takes care of deleting the temporary files.
                // When you're finished with a temporary file created using this method,
                // it's generally a good practice to let the system manage its removal.
                // Delete the temporary file when you're done with it
                /*tempFile.delete()*/

            }

            FileSystemType.HTML -> {
                // Create a temporary file to store the content
                val tempFile = File.createTempFile("temp_text_file", ".html")

                // Create an output stream for the temporary file
                val outputStream = FileOutputStream(tempFile)

                // Read data in chunks (e.g., 1KB at a time) from the input stream
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    // Process the data here, e.g., write it to another file, display it, etc.
                    // Write the data to the temporary file
                    outputStream.write(buffer, 0, bytesRead)
                    // In this example, we'll just print the data to the console.
                    System.out.write(buffer, 0, bytesRead) // Print the data to the console
                }

                // Close the output stream
                outputStream.close()

                //Return File absolutePath
                fileSystemListener.getAsHtmlFile("file://${tempFile.absolutePath}")
            }


            FileSystemType.STRING -> {
                val stringBuilder = StringBuilder()

                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    val chunk = String(buffer, 0, bytesRead)
                    stringBuilder.append(chunk)
                }

                fileSystemListener.getAsString(stringBuilder.toString())
            }


        }
    }

    private fun handleErrors(e: Exception, fileSystemListener: FileSystemListener) {
        val err = e.localizedMessage ?: ""
        Log.e(TAG, "handleErrors: $err")
        fileSystemListener.onError(e)
        /*if (e is IOException) {
            // Handle the IOException (e.g., log the error, notify the user)
        } else if (e is FileNotFoundException) {
            // The file was not found, handle it appropriately (e.g., create the file, notify the user)
        } else if (e is SecurityException) {
            // Handle the permission issue (e.g., request permissions, notify the user)
        }*/
    }


    companion object {
        private const val TAG = "FileSystem: "
    }

}