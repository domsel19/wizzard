package service

import entity.Wizzard

class RootService{
    val gameService = GameService(this)
    val playerActionService = PlyerActionService(this)

    var currentGame: Wizzard? = null


    fun addRefreshable(newRefreshable: Refreshable){
        gameService.addRefreshable(newRefreshable)
        playerActionService.addRefreshable(newRefreshable)
    }

    fun addRefreshables(vararg newRefreshables: Refreshable){
        newRefreshables.forEach{addRefreshable(it)}
    }
}