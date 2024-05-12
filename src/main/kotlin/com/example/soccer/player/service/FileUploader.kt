package com.example.soccer.player.service

import org.springframework.web.multipart.MultipartFile

interface FileUploader {

    fun upload(file: MultipartFile): String
}
