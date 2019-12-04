package com.example.footballleague.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueRepositoryImplTest {

    @Mock
    private lateinit var leagueRepository: LeagueRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun getDetailLeague() {
        leagueRepository = Mockito.mock(leagueRepository::class.java)
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                leagueRepository.getDetailLeague("4328")
                Mockito.verify(leagueRepository.getDetailLeague("4328"))
            }
        }
    }
}