package com.example.footballleague.viewmodel

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueViewModelTest {

    @Mock
    private lateinit var leagueViewModel: LeagueViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadLeagueDetail() {
        leagueViewModel = Mockito.mock(LeagueViewModel::class.java)
        leagueViewModel.loadLeagueDetail("4328")
        Mockito.verify(leagueViewModel).loadLeagueDetail("4328")
    }
}