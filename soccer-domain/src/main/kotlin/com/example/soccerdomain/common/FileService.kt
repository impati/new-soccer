package com.example.soccerdomain.common

import java.io.InputStream

interface FileService {


    fun uploadFile(originFileName: String, inputStream: InputStream, contentLength: Long): String

    fun downloadFile(path: String): ByteArray
}
