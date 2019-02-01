package a.rgpd.rgpd.utils

import android.content.Context
import java.io.File
import java.util.*

class UIDManager {
    companion object {
        fun retrieveUID(context: Context) : String {
            val directory = context.filesDir
            val file = File(directory, "UID.txt")

            if (!file.exists()) {
                file.createNewFile()
                file.writeText(UUID.randomUUID().toString().toUpperCase())
            }
            return file.readText()
        }
    }
}