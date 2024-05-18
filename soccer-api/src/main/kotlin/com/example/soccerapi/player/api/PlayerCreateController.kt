package com.example.soccerapi.player.api

import com.example.soccerapi.batch.JenkinsTrigger
import com.example.soccerdomain.common.FileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class PlayerCreateController(
    val fileService: FileService,
    val jenkinsTrigger: JenkinsTrigger
) {

    @PostMapping("/player/create/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        val uploadFileName = fileService.uploadFile(file.originalFilename!!, file.inputStream, file.size)

        jenkinsTrigger.runBatch("player_create", mapOf(Pair("input", uploadFileName)))

        return ResponseEntity.ok().build()
    }
}
