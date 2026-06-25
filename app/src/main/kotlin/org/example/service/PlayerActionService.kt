package service

import entity.*

class PlayerActionService(private val rootService : RootService) : AbstractRefreshingService(){

    fun discardCard(index){
        val game = requireNotNull(rootService.currentGame)
        game.players[currentPlayer].hand[index].remove()
    }

    fun enterTricks(){}
}