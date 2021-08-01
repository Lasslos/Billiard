package de.laslo.game

import de.laslo.*
import de.laslo.gui.Image
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO
import kotlin.system.exitProcess

class Ball private constructor(index: Int) {
    val image: Image
    var isInGap: Boolean = false
    var isVisible: Boolean
        get() = image.isVisible
        set(value) {
            image.isVisible = value
        }
    var position = Vector(0.0, 0.0) //Vector of middle of ball and relative to playing field origin
        set(value) {
            field = value
            image.position = field + Vector(RIGHT_WALL - BALL_HEIGHT_WIDTH / 2, UPPER_WALL - BALL_HEIGHT_WIDTH / 2)
        }
    var movement = Vector(0.0, 0.0)

    init {
        val image: BufferedImage

        try {
            image = ImageIO.read(File("$RES_DIRECTORY/balls/$index.png"))
        } catch (e: IOException) {
            e.printStackTrace()
            println("Images could not be loaded!")
            exitProcess(-1)
        }
        this.image = Image(image, 2)
    }

    companion object {
        val balls: Array<Ball> = Array(16) {
            Ball(it)
        }

        init {
            setBallsToInitialPosition()
        }

        fun setBallsToInitialPosition() {
            val ballIndices = ArrayList<Int>()
            for (i in 1..15) ballIndices.add(i)

            balls[0].position = WHITE_BALL_START_LOCATION
            placeBallTo(1, 1, balls[1]) //one is always on pos 1,1
            ballIndices.remove(1)
            placeBallTo(3, 2, balls[8]) //eight is always on ps 3,2
            ballIndices.remove(8)

            val random = Random()
            var index: Int
            val rowWithFullBall: Int
            val rowWithHalfBall: Int
            //Balls in edges of fifth column need to be different to each other, that means these spots aren't allowed to contain either two half or two full balls.
            if (random.nextBoolean()) {
                rowWithFullBall = 1
                rowWithHalfBall = 5
            } else {
                rowWithFullBall = 5
                rowWithHalfBall = 1
            }
            //Select one ball for each edge:
            index = random.nextInt(6) //2, 3, 4, 5, 6, 7
            placeBallTo(5, rowWithFullBall, balls[ballIndices[index]])
            ballIndices.removeAt(index)
            index = random.nextInt(7) + 5 //9, 10, 11, 12, 13, 14, 15
            placeBallTo(5, rowWithHalfBall, balls[ballIndices[index]])
            ballIndices.removeAt(index)

            //Going through all spots and pick a random ball for each
            for (i in 1..5) {
                for (j in 1..i) {
                    if (!(i == 1 && j == 1 || i == 3 && j == 2 || i == 5 && (j == 1 || j == 5))) { //Some spots are already occupied
                        index = random.nextInt(ballIndices.size)
                        placeBallTo(i, j, balls[ballIndices[index]])
                        ballIndices.removeAt(index)
                    }
                }
            }
        }

        private fun placeBallTo(column: Int, row: Int, ball: Ball) {
            val x = (1410 + (column - 1).toDouble() * 35.507)
            val y = (540 + (column.toDouble() - 2 * row.toDouble() + 1) * 20.5)
            ball.position = Vector(x, y)
        }
    }
}