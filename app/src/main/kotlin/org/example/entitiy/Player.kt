package entity

class Player(var name : String) {
    val points = mutableListOf(Int)
    val hand = mutableListOf(Card)
    val trick = 0
    val roundwins = 0
}