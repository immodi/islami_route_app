package com.client.routeapplication.helpers

import android.content.Context
import android.util.Log
import java.io.IOException

fun getLocalFileContent(context: Context, fileName: String, chunkSize: Int = 1024): String {
    val stringBuilder = StringBuilder()
    try {
        val inputStream = context.assets.open(fileName)
        val buffer = ByteArray(chunkSize)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            val chunk = String(buffer, 0, bytesRead)
            stringBuilder.append(chunk)
        }
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return stringBuilder.toString().replace(Regex("\\s+"), " ").trim()
}

fun readTextFileLinesFromAssets(
    context: Context,
    fileName: String,
    separator: String = "#"
): List<List<String>> {
    val list = arrayListOf<List<String>>()

    try {
        val inputStream = context.assets.open(fileName)
        val bufferedReader = inputStream.bufferedReader()

        val current = arrayListOf<String>()
        bufferedReader.useLines { lines ->
            lines.forEach { line ->
                if (line.trim() == separator) {
                    list.add(ArrayList(current))
                    current.clear()
                } else {
                    current.add(line)
                }
            }
        }

    } catch (e: IOException) {
        e.printStackTrace()
    }

    return list
}