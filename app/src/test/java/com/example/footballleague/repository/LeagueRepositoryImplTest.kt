package com.example.footballleague.repository

import com.example.footballleague.model.League
import com.example.footballleague.model.Leagues
import com.example.footballleague.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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

        val league = League(
            "4328",
            "English Premier League",
            "The Premier League (often referred to as the English Premier League (EPL) outside England), is the top level of the English football league system. Contested by 20 clubs, it operates on a system of promotion and relegation with the English Football League (EFL).\n" +
                    "            The Premier League is a corporation in which the member clubs act as shareholders. Seasons run from August to May with each team playing 38 matches (playing each other home and away). Most games are played on Saturday and Sunday afternoons. The Premier League has featured 47 English and two Welsh clubs since its inception, making it a cross-border league.\n" +
                    "            The competition was formed as the FA Premier League on 20 February 1992 following the decision of clubs in the Football League First Division to break away from the Football League, founded in 1888, and take advantage of a lucrative television rights deal. The deal was worth £1 billion a year domestically as of 2013–14, with BSkyB and BT Group securing the domestic rights to broadcast 116 and 38 games respectively. The league generates €2.2 billion per year in domestic and international television rights. In 2014–15, teams were apportioned revenues of £1.6 billion, rising sharply to £2.4 billion in 2016–17.\n" +
                    "            The Premier League is the most-watched sports league in the world, broadcast in 212 territories to 643 million homes and a potential TV audience of 4.7 billion people. In the 2014–15 season, the average Premier League match attendance exceeded 36,000, second highest of any professional football league behind the Bundesliga\\'s 43,500. Most stadium occupancies are near capacity. The Premier League ranks second in the UEFA coefficients of leagues based on performances in European competitions over the past five seasons, as of 2018.\n" +
                    "            Forty-nine clubs have competed since the inception of the Premier League in 1992. Six of them have won the title: Manchester United (13), Chelsea (5), Arsenal (3), Manchester City (3), Blackburn Rovers (1), and Leicester City (1). Following the 2003–04 season, Arsenal acquired the nickname \"The Invincibles\" as they became, and still remain, the only club to complete a Premier League campaign without losing a single game. The record of most points in a season is 100 by Manchester City in 2017–18.",
            "",
            "",
            0
        )

        val list = ArrayList<League>()
        list.add(league)

        val leagues = Leagues(list)

        val expected = listOf<Result<Leagues>>(
            Result.Error(Throwable()),
            Result.Success(leagues)
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                `when`(leagueRepository.getDetailLeague("4328")).thenReturn(expected[0])
                leagueRepository.getDetailLeague("4328")
                verify(leagueRepository.getDetailLeague("4328"))
                Assert.assertEquals(leagueRepository.getDetailLeague("4328"), expected[0])
            }
        }
    }
}