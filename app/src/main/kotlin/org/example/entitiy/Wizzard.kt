

class Wizzard(players : List<String>, currentPlayer: Int){
    val players = players
    var drawStack = mutableListOf(Card)
    var round = 0
    var trump? : Color = null
}