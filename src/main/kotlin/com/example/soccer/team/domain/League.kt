package com.example.soccer.team.domain

import lombok.Getter

@Getter
enum class League(name: String) {

    LALIGA("라리가"),
    BUNDESLIGA("분데스리가"),
    PREMIER_LEAGUE("프리미어 리그"),
    SERIE("세리에"),
    LEAGUE_1("리그앙");
}
