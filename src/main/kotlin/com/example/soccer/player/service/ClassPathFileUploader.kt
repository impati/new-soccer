package com.example.soccer.player.service

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Component
class ClassPathFileUploader : FileUploader {

    private val targetLocation: Path = Paths.get("src/main/resources/uploads")

    init {
        Files.createDirectories(targetLocation)
    }

    override fun upload(file: MultipartFile): String {
        val targetPath = targetLocation.resolve(file.originalFilename!!)
        file.inputStream.use { inputStream ->
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING)
        }
        return targetPath.toString()
    }
}
