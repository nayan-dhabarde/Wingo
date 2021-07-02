package com.nayandhabarde.wingo.paging

import com.nayandhabarde.wingo.model.Tournament
import org.junit.Before

class TournamentPagingSourceTest {

    private val tournamentFactory = TournamentFactory()
    private val mockList = listOf(
        tournamentFactory.create(6253),
        tournamentFactory.create(6252),
        tournamentFactory.create(6266),
    )

    

}