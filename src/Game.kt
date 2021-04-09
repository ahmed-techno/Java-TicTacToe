class Game(var gridSize: Int) {
    //This is the Game class.  It hold the current state of the game
    //with the help of the Cell class.
    var finished = false
    var draw = false
    private val grid: Array<Cell?>

    //checks to see if a win condition has been met and
    //outputs the current game map to the console 
    fun output(): String {
        checkForTicTacToe()
        return drawMap()
    }

    //places an X or an O in a cell ont he game map
    fun setCell(index: Int): Boolean {
        return if (grid[index]!!.empty) {
            grid[index]!!.placeMark()
            true
        } else {
            false
        }
    }

    //checks to see if a win condition has been met
    private fun checkForTicTacToe(): Boolean {
        var gridFilled: Boolean
        var rowWin: Boolean
        var columnWin: Boolean
        var diagonalWin: Boolean
        val rows: Array<Array<Cell?>> = Array<Array<Cell?>>(gridSize) { arrayOfNulls<Cell>(gridSize) }
        val columns: Array<Array<Cell?>> = Array<Array<Cell?>>(gridSize) { arrayOfNulls<Cell>(gridSize) }
        val diagonals: Array<Array<Cell?>> = Array<Array<Cell?>>(2) { arrayOfNulls<Cell>(gridSize) } //there are only ever two diagonals which complete a tictactoe in a square

        //if every cell is filled, end the game
        gridFilled = true
        for (i in 0 until gridSize * gridSize) {
            if (grid[i]!!.empty) {
                gridFilled = false
            }
        }
        if (gridFilled) {
            finished = true
            draw = true
        }
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                rows[i][j] = grid[gridSize * i + j]
            }
        }
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                columns[i][j] = grid[i + gridSize * j]
            }
        }
        for (i in 0..1) {
            if (i == 0) {
                for (j in 0 until gridSize) {
                    diagonals[i][j] = grid[(gridSize + 1) * j]
                }
            } else {
                for (j in 0 until gridSize) {
                    diagonals[i][j] = grid[(gridSize - 1) * (j + 1)]
                }
            }
        }

        //if a row has all the same content and isnt empty
        //then the game is over
        for (row in rows) {

            //if the row elements are all the same and not empty
            //set finished to true
            rowWin = true
            for (i in 0 until row.size - 1) {
                if (row[i]!!.output() !== row[i + 1]!!.output()) {
                    rowWin = false
                }
                for (j in 0 until row.size - 1) {
                    if (row[i]!!.empty) {
                        rowWin = false
                    }
                }
            }
            if (rowWin) {
                finished = true
                draw = false
            }
        }

        //if a column has all the same content and isnt empty
        //then the game is over
        for (column in columns) {

            //if the column elements are all the same and not empty
            //set finished to true
            columnWin = true
            for (i in 0 until column.size - 1) {
                if (column[i]!!.output() !== column[i + 1]!!.output()) {
                    columnWin = false
                }
                for (j in 0 until column.size - 1) {
                    if (column[i]!!.empty) {
                        columnWin = false
                    }
                }
            }
            if (columnWin) {
                finished = true
                draw = false
            }
        }

        //if a diagonal has all the same content and isnt empty
        //then the game is over
        for (diagonal in diagonals) {

            //if the diagonal elements are all the same and not empty
            //set finished to true
            diagonalWin = true
            for (i in 0 until diagonal.size - 1) {
                if (diagonal[i]!!.output() !== diagonal[i + 1]!!.output()) {
                    diagonalWin = false
                }
                for (j in 0 until diagonal.size - 1) {
                    if (diagonal[i]!!.empty) {
                        diagonalWin = false
                    }
                }
            }
            if (diagonalWin) {
                finished = true
                draw = false
            }
        }
        return finished
    }

    //draws the current game state in perfect proportion
    //
    private fun drawMap(): String {
        var top = "\t\t  "
        var fill = "\t\t    "
        var divider = "\t\t ---"
        var meat = "\t\t"
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var map = "\n"
        for (i in 1 until gridSize) {
            top += i.toString() + "  "
            if (i < 9) {
                top += " "
            }
            fill += "|   "
            divider += "+---"
        }
        top += "$gridSize \n"
        fill += "\n"
        divider += "\n"
        map += top + fill
        for (row in 1..1) {
            for (column in 1..1) {
                meat += alphabet.substring(row - 1, row).toString() + " " + grid[3 * (row - 1) + (column - 1)]!!.output()
                for (i in 2 until gridSize + 1) {
                    meat += " | " + grid[3 * (row - 1) + (i - 1)]!!.output()
                }
            }
            meat += "\n"
        }
        map += meat + fill
        for (row in 2 until gridSize + 1) {
            map += divider
            map += fill
            for (column in 1..1) {
                meat = "\t\t" + alphabet.substring(row - 1, row).toString() + " " + grid[gridSize * (row - 1) + (column - 1)]!!.output()
                for (i in column + 1 until gridSize + 1) {
                    meat += " | " + grid[gridSize * (row - 1) + (i - 1)]!!.output()
                }
            }
            map += """
                $meat
                $fill
                """.trimIndent()
        }
        return map
    }

    //constructor.  takes integer and generates a new Game with given size
    init {
        grid = arrayOfNulls<Cell>(gridSize * gridSize)
        for (i in grid.indices) {
            grid[i] = Cell()
        }
    }
}