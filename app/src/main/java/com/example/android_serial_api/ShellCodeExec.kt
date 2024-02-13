package com.example.android_serial_api

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class ShellCodeExec constructor(Command: String) {
    init {
        try {
            val process = Runtime.getRuntime().exec(Command)
            val reader = BufferedReader(
                InputStreamReader(process.inputStream)
            )
            var read: Int
            val buffer = CharArray(4096)
            val output = StringBuffer()
            while (reader.read(buffer).also { read = it } > 0) {
                output.append(buffer, 0, read)
            }
            reader.close()
            process.waitFor()
            output.toString()
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

}