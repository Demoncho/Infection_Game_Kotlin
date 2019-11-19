package alfabeta

import javafx.scene.transform.MatrixType
import java.lang.StringBuilder

class MatrixGame() {
    val matrix = arrayListOf(
        arrayListOf('#', '0', '0', '0', '0', '0', '*'),
        arrayListOf('0', '0', '0', '0', '0', '0', '0'),
        arrayListOf('0', '0', '0', '0', '0', '0', '0'),
        arrayListOf('0', '0', '0', '0', '0', '0', '0'),
        arrayListOf('0', '0', '0', '0', '0', '0', '0'),
        arrayListOf('0', '0', '0', '0', '0', '0', '0'),
        arrayListOf('*', '0', '0', '0', '0', '0', '#')
    )

    var matrix_string = StringBuilder()
    init{
        for (i in 0..6)
            for (j in 0..6)
                matrix_string.append(matrix[i][j].toString())
    }

    fun get_string(ar: ArrayList<ArrayList<Char>>){
        matrix_string.clear()
        for (i in 0..6)
            for (j in 0..6)
                matrix_string.append(ar[i][j].toString())
    }

    fun copy(new_matr: ArrayList<ArrayList<Char>>){
        for (i in 0..6)
            for (j in 0..6)
                matrix[i][j] = new_matr[i][j]
    }

    fun copy_str(new_str: StringBuilder){
        matrix_string.append(new_str)
    }

    fun print_game() {
        for (i in 0..6) {
            for (j in 0..6) {
                if (j == 0)
                    print(i.toString() + "| " + matrix[i][j] + " ")
                else if (j == 6)
                    println(matrix[i][j])
                else print(matrix[i][j] + " ")
            }
        }
        println("   - - - - - - -")
        println("   " + "0 " + "1 " + "2 " + "3 " + "4 " + "5 " + "6")
        println()
    }

     fun make_move(from: Pair<Int, Int>, to: Pair<Int, Int>, player: Char): Boolean {
         if (!posible_move(player)){
             println("Закончились ходы у " + player)
             return false
         }
        if (from.first < 0 || from.first > 6 || from.second < 0 || from.second > 6 || to.first < 0 || to.first > 6 || to.second < 0 || to.second > 6) {
            println("Позиция за границами")
            return false
        } else if (matrix[from.first][from.second] != player) {
            println("Неправильно выбрана фигура")
            return false
        } else if (matrix[to.first][to.second] != '0') {
            println("Занятая позиция")
            return false
        } else if (Math.abs(from.first - to.first) > 2 || Math.abs(from.second - to.second) > 2) {
            println("Слишком далеко")
            return false
        } else {
            matrix[to.first][to.second] = player
            if (Math.abs(from.first - to.first) > 1 || Math.abs(from.second - to.second) > 1)
                matrix[from.first][from.second] = '0'

            for (i in -1..1)
                if (to.first+i > -1 && to.first+i < 7)
                    for (j in -1..1)
                        if (to.second+j > -1 && to.second+j < 7){
                            if (matrix[to.first+ i][to.second + j] != player && matrix[to.first + i][to.second + j] != '0')
                                matrix[to.first + i][to.second + j] = player
                        }

            /* if (to.second - 1 > -1 && matrix[to.first][to.second - 1] != player && matrix[to.first][to.second - 1] != '0')
                matrix[to.first][to.second - 1] = player
            if (to.second + 1 < 6 && matrix[to.first][to.second + 1] != player && matrix[to.first][to.second + 1] != '0')
                matrix[to.first][to.second + 1] = player
            if (to.first - 1 > -1 && matrix[to.first - 1][to.second] != player && matrix[to.first - 1][to.second] != '0')
                matrix[to.first - 1][to.second] = player
            if (to.first + 1 < 6 && matrix[to.first + 1][to.second] != player && matrix[to.first + 1][to.second] != '0')
                matrix[to.first + 1][to.second] = player
            if (to.first + 1 < 6 && to.second + 1 < 6 && matrix[to.first + 1][to.second + 1] != player && matrix[to.first + 1][to.second + 1] != '0')
                matrix[to.first + 1][to.second + 1] = player
            if (to.first + 1 < 6 && to.second - 1 > -1 && matrix[to.first + 1][to.second - 1] != player && matrix[to.first + 1][to.second - 1] != '0')
                matrix[to.first + 1][to.second - 1] = player
            if (to.first - 1 > -1 && to.second + 1 < 6 && matrix[to.first - 1][to.second + 1] != player && matrix[to.first - 1][to.second + 1] != '0')
                matrix[to.first - 1][to.second + 1] = player
            if (to.first - 1 > -1 && to.second - 1 > -1 && matrix[to.first - 1][to.second - 1] != player && matrix[to.first - 1][to.second - 1] != '0')
                matrix[to.first - 1][to.second - 1] = player*/

            return true
        }
    }

    fun check_move(from: Pair<Int, Int>, to: Pair<Int, Int>, player: Char): Boolean{
        if (to.first < 0 || to.first > 6 || to.second < 0 || to.second > 6)
            return false
        if (matrix[to.first][to.second] != '0')
            return false
        return true
    }

    fun check_move_str(from: Pair<Int, Int>, to: Pair<Int, Int>, player: Char ):Boolean{
        if (to.first < 0 || to.first > 6 || to.second < 0 || to.second > 6)
            return false
        if (matrix_string[to.first*7 + to.second] != '0')
            return false
        return true
    }

    fun make_move_without_check(from: Pair<Int, Int>, to: Pair<Int, Int>, player: Char) {
        matrix[to.first][to.second] = player
        if (Math.abs(from.first - to.first) > 1 || Math.abs(from.second - to.second) > 1)
            matrix[from.first][from.second] = '0'

        for (i in -1..1)
            if (to.first+i > -1 && to.first+i < 7)
                for (j in -1..1)
                    if (to.second+j > -1 && to.second+j < 7){
                        if (matrix[to.first+ i][to.second + j] != player && matrix[to.first + i][to.second + j] != '0')
                            matrix[to.first + i][to.second + j] = player
                    }
    }

    fun make_move_without_check_str(from: Pair<Int, Int>, to: Pair<Int, Int>, player: Char){
        val id =  to.first*7 + to.second
        matrix_string[id] = player
        if (Math.abs(from.first - to.first) > 1 || Math.abs(from.second - to.second) > 1)
            matrix_string[from.first*7 + from.second] = '0'
        for (i in -1..1)
            if (to.first+i > -1 && to.first+i < 7)
                for (j in -1..1)
                    if (to.second+j > -1 && to.second+j < 7){
                        if (matrix_string[to.first*7 + i*7 + to.second + j] != player && matrix_string[to.first*7 + i*7 + to.second + j] != '0')
                            matrix_string[to.first*7 + i*7 + to.second + j] = player
                    }
    }

    fun make_move_without_check_str_and_matr(from: Pair<Int, Int>, to: Pair<Int, Int>, player: Char){
        val id =  to.first*7 + to.second
        matrix_string[id] = player
        if (Math.abs(from.first - to.first) > 1 || Math.abs(from.second - to.second) > 1)
            matrix_string[from.first*7 + from.second] = '0'
        for (i in -1..1)
            if (to.first+i > -1 && to.first+i < 7)
                for (j in -1..1)
                    if (to.second+j > -1 && to.second+j < 7){
                        if (matrix_string[to.first + i*7 + to.second + j] != player && matrix_string[to.first + i*7 + to.second + j] != '0')
                            matrix_string[to.first + i*7 + to.second + j] = player
                    }
        for (i in 0..6)
            for (j in 0..6)
                matrix[i][j] = matrix_string[i*7 + j]
    }


     fun posible_move(player: Char): Boolean{
         for (i in 0..6) {
             for (j in 0..6) {
                if (matrix[i][j] == '0') {
                    for (k in -2..2)
                        for (l in -2..2)
                            if (i+k > -1 && i+k < 7 && j+l > -1 && j+l < 7 && matrix[i+k][j+l] == player)
                                return true
                }
             }
         }
         return false
    }
}

class AlfaBeta(val depth: Int, val isMax: Boolean, val position: MatrixGame, val alpha: Int, val beta: Int) {

    fun heuristic(position: MatrixGame): Int {
        var number_ai = 0
        //var number_player = 0
        for (i in 0..6) {
            number_ai += position.matrix[i].count { x -> x == '#' }
          //  number_player += position.matrix[i].count { x -> x == '*' }
        }
        return number_ai
    }
    fun heuristic_str(position: MatrixGame): Int {
        var number_ai = 0
        //var number_player = 0
        number_ai += position.matrix_string.count { x -> x == '#' }
        //number_player += position.matrix_string.count { x -> x == '*' }
        return number_ai
    }

        fun make_ai_move() {
        val our_positions = arrayListOf<Pair<Int, Int>>()
        for (i in 0..6)
            for (j in 0..6)
                if (position.matrix[i][j] == '#')
                    our_positions.add(i to j)
        var temp_matrix = MatrixGame()
        val posible_moves = arrayListOf<Triple<Pair<Int,Int>, Pair<Int,Int>, Int>>()
        for (k in 0..our_positions.size - 1) {
           // temp_matrix.copy(position.matrix)
            for (i in -2..2)
                for (j in -2..2)
                    if (position.check_move(our_positions[k].first to our_positions[k].second,our_positions[k].first + i to our_positions[k].second + j, '#' )){
                        temp_matrix.copy(position.matrix)
                        temp_matrix.make_move_without_check(our_positions[k].first to our_positions[k].second,our_positions[k].first + i to our_positions[k].second + j, '#')
                        posible_moves.add(Triple(our_positions[k],Pair(our_positions[k].first + i,our_positions[k].second + j), chose_move(0, false, temp_matrix , -100, 100)))
                    }
        }
        var from = 0 to 0
        var to = 0 to 0
        var max = -100
        for (i in 0..posible_moves.size-1)
            if (posible_moves[i].third > max){
                max = posible_moves[i].third
                from = posible_moves[i].first
                to = posible_moves[i].second
            }
        println(from.toString()+ "->"+ to.toString())
        position.make_move_without_check(from,to,'#')

    }

    fun make_ai_move_str() {
        position.matrix_string.clear()
        for (i in 0..6)
            for (j in 0..6)
                position.matrix_string.append(position.matrix[i][j].toString())
        val our_positions = arrayListOf<Int>()
        for (i in 0..48)
                if (position.matrix_string[i] == '#'){
                    our_positions.add(i)
                }
        var temp_matrix = MatrixGame()
        val posible_moves = arrayListOf<Triple<Int, Int , Int>>()
        for (k in 0..our_positions.size - 1) {
            // temp_matrix.copy(position.matrix)
            for (i in -2..2)
                for (j in -2..2)
                    if (position.check_move_str(our_positions[k] / 7 to our_positions[k] % 7,our_positions[k] / 7 + i to our_positions[k] % 7 + j, '#' )){
                        temp_matrix.copy_str(position.matrix_string)
                        temp_matrix.make_move_without_check_str(our_positions[k] / 7 to our_positions[k] % 7,our_positions[k] / 7 + i to our_positions[k] % 7 + j, '#' )
                        posible_moves.add(Triple(our_positions[k], our_positions[k] + i*7 + j, chose_move_str(0, false, temp_matrix , -100, 100)))
                    }
        }
        var from = 0 to 0
        var to = 0 to 0
        var max = -100
        for (i in 0..posible_moves.size-1)
            if (posible_moves[i].third > max){
                max = posible_moves[i].third
                from = posible_moves[i].first / 7 to posible_moves[i].first % 7
                to = posible_moves[i].second / 7 to posible_moves[i].second % 7
            }
        println(from.toString()+ "->"+ to.toString())
        position.make_move_without_check_str_and_matr(from,to,'#')

    }

    private fun chose_move( depth: Int,  isMax: Boolean,  position: MatrixGame,  alpha: Int,  beta: Int): Int{
        // Terminating condition. i.e
        // leaf node is reached
        if (depth == 2)
            return heuristic(position)

        if (isMax)
        {
            var best = -100
            val posible_moves = arrayListOf<MatrixGame>()
            for (k in 0..6)
                for (l in 0..6)
                    if (position.matrix[k][l] == '*'){
                        for (i in -2..2)
                            for (j in -2..2)
                                    if (position.check_move(k to l,k + i to l + j, '*' )) {
                                    val temp = MatrixGame()
                                    temp.copy(position.matrix)
                                    temp.make_move_without_check(k to l, k + i to l + j , '*')
                                    posible_moves.add(temp)
                                }

                    }

            // Recur for left and
            // right children
            for (i in 0..posible_moves.size - 1)
            {

                val temp = chose_move(depth + 1, false, posible_moves[i], alpha, beta)
                best = Math.max(best, temp)
                val alpha1 = Math.max(alpha, best)

                // Alpha Beta Pruning
                if (beta <= alpha1)
                    break
            }
            return best
        }
        else
        {
            var best = 100
            val posible_moves = arrayListOf<MatrixGame>()
            for (k in 0..6)
                for (l in 0..6)
                    if (position.matrix[k][l] == '#'){
                        for (i in -2..2)
                            for (j in -2..2)
                                if (position.check_move(k to l,k + i to l + j, '#' )) {
                                    val temp = MatrixGame()
                                    temp.copy(position.matrix)
                                    temp.make_move_without_check(k to l, k + i to l + j , '#')
                                    posible_moves.add(temp)
                                }

                    }

            // Recur for left and
            // right children
            for (i in 0..posible_moves.size - 1)
            {
                val temp = chose_move(depth + 1,  true, posible_moves[i], alpha, beta)
                best = Math.min(best, temp)
                val beta1 = Math.min(beta, best)

                // Alpha Beta Pruning
                if (beta1 <= alpha)
                    break
            }
            return best
        }
    }

    private fun chose_move_str(depth: Int,  isMax: Boolean,  position: MatrixGame,  alpha: Int,  beta: Int): Int{
        // Terminating condition. i.e
        // leaf node is reached
        if (depth == 2)
            return heuristic_str(position)

        if (isMax)
        {
            var best = -100
            val posible_moves = arrayListOf<MatrixGame>()
            for (k in 0..47)
                if (position.matrix_string[k] == '*'){
                    for (i in -2..2)
                        for (j in -2..2)
                            if (position.check_move_str(k /7 to k%7,k /7 + i to k%7 + j, '*' )) {
                                val temp = MatrixGame()
                                temp.copy_str(position.matrix_string)
                                temp.make_move_without_check_str(k /7 to k%7,k /7 + i to k%7 + j, '*' )
                                posible_moves.add(temp)
                            }

                }

            // Recur for left and
            // right children
            for (i in 0..posible_moves.size - 1)
            {

                val temp = chose_move_str(depth + 1, false, posible_moves[i], alpha, beta)
                best = Math.max(best, temp)
                val alpha1 = Math.max(alpha, best)

                // Alpha Beta Pruning
                if (beta <= alpha1)
                    break
            }
            return best
        }
        else
        {
            var best = 100
            val posible_moves = arrayListOf<MatrixGame>()
            for (k in 0..47)
                if (position.matrix_string[k] == '#'){
                    for (i in -2..2)
                        for (j in -2..2)
                            if (position.check_move_str(k /7 to k%7,k /7 + i to k%7 + j, '#' )) {
                                val temp = MatrixGame()
                                temp.copy_str(position.matrix_string)
                                temp.make_move_without_check_str(k /7 to k%7,k /7 + i to k%7 + j, '#' )
                                posible_moves.add(temp)
                            }

                }

            // Recur for left and
            // right children
            for (i in 0..posible_moves.size - 1)
            {
                val temp = chose_move_str(depth + 1,  true, posible_moves[i], alpha, beta)
                best = Math.min(best, temp)
                val beta1 = Math.min(beta, best)

                // Alpha Beta Pruning
                if (beta1 <= alpha)
                    break
            }
            return best
        }
    }
}