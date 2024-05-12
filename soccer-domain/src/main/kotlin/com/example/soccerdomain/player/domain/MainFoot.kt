package com.example.soccerdomain.player.domain

import lombok.Getter

@Getter
enum class MainFoot(name: String) {

    LEFT("왼발"), RIGHT("오른발"), BOTH("양발")
}
