package com.example.soccerdomain.common

import com.example.soccerdomain.config.S3Config
import org.springframework.stereotype.Component
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.InputStream

@Component
class S3FileService(
    val s3Config: S3Config,
    val s3Client: S3Client
) : FileService {
    override fun uploadFile(originFileName: String, inputStream: InputStream, contentLength: Long): String {
        val savepoint = "${s3Config.directory}/${originFileName}"
        val request = PutObjectRequest.builder()
            .bucket(s3Config.bucketName)
            .key(savepoint)
            .build()

        s3Client.putObject(request, RequestBody.fromInputStream(inputStream, contentLength))

        return "https://${s3Config.bucketName}.s3.${s3Config.region}.amazonaws.com/${savepoint}"
    }

    override fun downloadFile(path: String): ByteArray {
        val savepoint = "${s3Config.directory}/${path}"
        val request = GetObjectRequest.builder()
            .bucket(s3Config.bucketName)
            .key(savepoint)
            .build()

        return s3Client.getObjectAsBytes(request).asByteArray()
    }
}
