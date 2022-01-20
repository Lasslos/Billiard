package eu.lh0.game

import eu.lh0.utils.MyImage
import eu.lh0.utils.PLAYING_FIELD_ORIGIN
import eu.lh0.utils.RES_DIRECTORY
import eu.lh0.utils.WHITE_BALL_START_LOCATION
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.random.Random
import kotlin.system.exitProcess

class Ball private constructor(
    index: Int,
    var realPosition: Vector,
    ) {
    var relativePosition: Vector
        set(value) {
            realPosition = value + PLAYING_FIELD_ORIGIN
        }
        get() = realPosition - PLAYING_FIELD_ORIGIN

    val image: MyImage
    var isVisible: Boolean = true
    var movement: Vector = Vector(0, 0)

    init {
        try {
            val bufferedImage = ImageIO.read(File("$RES_DIRECTORY/balls/$index.png"))
            image = MyImage(
                bufferedImage,
                bufferedImage.width,
                bufferedImage.height
            )
        } catch (e: IOException) {
            e.printStackTrace()
            println("Images could not be loaded!")
            exitProcess(-1)
        }
    }

    companion object {
        var balls: Array<Ball>

        init {
            balls = initialPosition()
        }

        fun initialPosition(): Array<Ball> {
            val indices = ArrayList<Int>()

            for (i in 0..15) indices.add(i)

            val ballsInitializer: Array<Ball?> = Array(16) {
                val position: Vector = when (it) {
                    0 -> WHITE_BALL_START_LOCATION
                    1 -> getInitialPosition(1, 1)
                    8 -> getInitialPosition(2, 3)
                    else -> return@Array null
                }
                indices.remove(it)
                return@Array Ball(it, position)
            }

            val rowWithFullBall = arrayOf(1, 5).random()
            val rowWithHalfBall = if (rowWithFullBall == 1) 5 else 1
            putRandomBall(ballsInitializer, indices, getInitialPosition(rowWithFullBall, 5))
            putRandomBall(ballsInitializer, indices, getInitialPosition(rowWithHalfBall, 5))


            for (column in 1..5) {
                row@for (row in 1..column) {
                    val position = Vector(row, column)
                    if (position == Vector(1, 1)
                        || position == Vector(2, 3)
                        || position == Vector(1, 5)
                        || position == Vector(5, 5)
                    ) continue@row
                    putRandomBall(ballsInitializer, indices, getInitialPosition(row, column))
                }
            }
            return Array(ballsInitializer.size) {
                ballsInitializer[it]!!
            }
        }

        private fun putRandomBall(ballsInitializer: Array<Ball?>, indices: ArrayList<Int>, position: Vector, randomOverride: Int? = null) {
            val index = indices[randomOverride ?: Random.nextInt(indices.size)]
            ballsInitializer[index] = Ball(index, position)
            indices.remove(index)
        }

        private fun getInitialPosition(row: Int, column: Int) = Vector(
            (1410.0 + (column - 1).toDouble() * 35.507),
            (540.0 + (column.toDouble() - 2.0 * row.toDouble() + 1) * 20.5),
        )
    }
}