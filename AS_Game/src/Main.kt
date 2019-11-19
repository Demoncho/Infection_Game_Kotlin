import alfabeta.*
import javafx.scene.transform.MatrixType
import java.lang.StringBuilder

fun main(args: Array<String>){

    val aaa = MatrixGame()
    val game = AlfaBeta(0, false, aaa, -100, 100)
    game.position.print_game()
    while(game.position.posible_move('*')){
        print("Введите координаты фигуры: ")
        var from = readLine()!!.toString()
        var from_x = from[0].toString().toInt()
        var from_y = from[2].toString().toInt()
        //println (from_x.toString() + " "+ from_y.toString())
        print("Введите координаты перемещения : ")
        var to = readLine()!!
        var to_x = to[0].toString().toInt()
        var to_y = to[2].toString().toInt()
        //print ("Введите номер строки выбранной фигуры: ")
        //var from_x = readLine()!!.toInt()
        //print ("Введите номер столбца выбранной фигуры: ")
        //var from_y = readLine()!!.toInt()
        //print ("Введите номер строки новой позиции: ")
        //var to_x = readLine()!!.toInt()
        //print ("Введите номер столбца новой позиции: ")
        //var to_y = readLine()!!.toInt()
        while(!game.position.make_move(from_x to from_y, to_x to to_y, '*')){
            print ("Введите номер строки выбранной фигуры: ")
            from_x = readLine()!!.toInt()
            print ("Введите номер столбца выбранной фигуры: ")
            from_y = readLine()!!.toInt()
            print ("Введите номер строки новой позиции: ")
            to_x = readLine()!!.toInt()
            print ("Введите номер столбца новой позиции: ")
            to_y = readLine()!!.toInt()
        }
        game.position.print_game()
        if (game.position.posible_move('#'))
            game.make_ai_move()
        game.position.print_game()
    }

    if (game.heuristic(game.position) > 0)
        println("Вы проиграли")
    else if (game.heuristic(game.position) < 0)
        println ("Вы победили")
    else println("Ничья")
}