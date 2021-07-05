package com.nayandhabarde.wingo.paging

import com.nayandhabarde.wingo.model.League

class LeagueFactory {
    fun create(id: Long): League {
        return League(id = id)
    }

}