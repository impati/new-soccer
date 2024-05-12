package com.example.soccer.player.api

import com.example.soccer.player.service.ClassPathFileUploader
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class PlayerUploadController(
    val jobLauncher: JobLauncher,
    val job: Job,
    val classPathFileUploader: ClassPathFileUploader
) {

    @PostMapping("/player/upload")
    fun handleFileUpload(@RequestParam("file") file: MultipartFile): String {
        val path = classPathFileUploader.upload(file)

        println("absolutePath : $path")

        val jobParameters = JobParametersBuilder()
            .addString("input", path)
            .toJobParameters()

        jobLauncher.run(job, jobParameters)
        return "File successfully processed!"
    }
}
