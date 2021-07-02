package com.nayandhabarde.wingo.paging

import com.nayandhabarde.wingo.model.Tournament

class TournamentFactory {
    fun create(id: Long): Tournament {
        return Tournament(beginAt = "2021-06-22T11:56:58Z",
        endAT = "2021-06-26T11:56:58Z",
        id = id)
    }

}