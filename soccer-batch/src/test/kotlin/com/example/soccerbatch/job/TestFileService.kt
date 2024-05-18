package com.example.soccerbatch.job

import com.example.soccerdomain.common.FileService
import org.springframework.context.annotation.Primary
import org.springframework.core.io.PathResource
import org.springframework.stereotype.Component
import java.io.InputStream


@Component
@Primary
class TestFileService : FileService {
    override fun uploadFile(originFileName: String, inputStream: InputStream, contentLength: Long): String {
        return ""
    }

    override fun downloadFile(path: String): ByteArray {
        return PathResource(path).contentAsByteArray
    }
}
