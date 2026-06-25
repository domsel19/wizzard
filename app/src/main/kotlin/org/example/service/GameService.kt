package service

import entity.*
import kotlin.collections.toMutableList


class GameService(private val rootService:RootService) : AbstractRefreshingService(){
    fun startGame(players: List<String>){
        require(players.isNotEmpty()) {"players is empty"}
        require(players.size() in 2..6)
        require(players.all{it.isNotBlank()}){ "playername is blank "}
        require(players.distinct().size == players.size) {"there is a name twice"}
        val game = Wizzard(players = players, currentPlayer = 0)

        createDrawStack

        rootService.currentGame = game

        game.GameState = GameState.PLAYER_TURN
        onAllRefreshables { refreshAfterStartGame()}

    }

    private fun endGame(){
        val game = requireNotNull(rootService.currentGame)
        calculatePoints()
        game.GameState = GameState.GAME_ENDED
    }

    private fun nextPlayer(){
        rootService.currentGame.currentPlayer++ % players.size()
    }

    private fun createDrawStack() {
        val game = requireNotNull(rootService.currentGame)

        game.drawStack = Value.entries.flatMap { value ->
        Color.entries.map { color />
        Card(value, color)
        }
        }.toMutableList()
    }

    private fun serveCards() {
        val game = requireNotNull(rootService.currentGame)
        createDrawStack()
        game.drawStack.shuffle()
        for (player in players) {
            for (round in rounds){
                player.hand.add(drawStack.removeLast())
            }
        }
    }

    private fun initialRoundStart(){
        val game = requireNotNull(rootService.currentGame)
        game.round++
        serveCards()
        trump = drawStack.removeLast().Color
    }

    private fun calculatePoints(){
        val totalPointsAll = mutableListOf()
        for(player in players){
            val totalPoints = 0
            for(point in Player.points){
                totalPoints+= point
            }
            totalPointsAll[player]=totalPoints
        }
        onAllRefreshables(refreshAfterEndGame(totalPointsAll))
    }
}