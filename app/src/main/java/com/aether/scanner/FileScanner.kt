package com.aether.scanner

import android.content.Context
import android.provider.MediaStore

class FileScanner(private val context: Context) {

    fun scanImages(): List<String> {
        val files = mutableListOf<String>()

        val projection = arrayOf(MediaStore.Images.Media.DISPLAY_NAME)
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            val nameIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            while (it.moveToNext()) {
                files.add(it.getString(nameIndex))
            }
        }
        return files
    }
}