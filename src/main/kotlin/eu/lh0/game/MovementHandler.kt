package eu.lh0.game

import eu.lh0.gui.Frame
import eu.lh0.utils.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(DelicateCoroutinesApi::class)
object MovementHandler {
    init {
        GlobalScope.launch {
            while (true) {
                checkForBallCollision()
                handleBallWallCollision()
                checkForBallInGap()
                moveBalls()
                delay(1)
            }
        }
        GlobalScope.launch {
            while (true) {
                Frame.repaint()
                handleFriction()
                delay(20)
            }
        }
    }

    private fun checkForBallCollision() {
        for (i in 0 until Ball.balls.size) {
            for (j in i until Ball.balls.size) {
                val ball1 = Ball.balls[i]
                val ball2 = Ball.balls[j]
                if (!ball1.isVisible || !ball2.isVisible || (ball1.movement.length == 0.0 && ball2.movement.length == 0.0))
                    continue
                if (ball1.relativePosition.getDistanceTo(ball2.relativePosition) < 41)
                    handleBallCollision(ball1, ball2)
            }
        }
    }

    private fun handleBallCollision(ball1: Ball, ball2: Ball) {
        val oldBall1Movement = ball1.movement
        val oldBall2Movement = ball2.movement

        ball1.movement
    }

    private fun handleBallWallCollision() {
        for (ball in Ball.balls) {
            val x = ball.relativePosition.x
            val y = ball.relativePosition.y

            val movement = ball.movement

            when {
                x < LEFT_WALL_RELATIVE -> ball.movement = movement.copyWith(x = movement.x.absoluteValue)
                x > RIGHT_WALL_RELATIVE -> ball.movement = movement.copyWith(x = -movement.x.absoluteValue)
            }
            when {
                y < UPPER_WALL_RELATIVE -> ball.movement = movement.copyWith(y = movement.y.absoluteValue)
                y > LOWER_WALL_RELATIVE -> ball.movement = movement.copyWith(y = - movement.y.absoluteValue)
            }
        }
    }

    private fun checkForBallInGap() {
        /*balls@ for (ball in Ball.balls) {
            holes@ for (vector in holes) {
                if (ball.position.getDistanceTo(vector) < 50) {
                    handleBallInGap(ball)
                    continue@balls
                }
            }
        }*/
    }

    private fun handleBallInGap(ball: Ball) {
        if (ball.isVisible) {
            ball.isVisible = false
            GlobalScope.launch {
                var i = 41
                while (i >= 0) {
                    ball.relativePosition = ball.relativePosition - 1
                    ball.image.width--
                    ball.image.height--
                    delay(10)
                    i -= 2
                }
                ball.isVisible = false
            }
        }
    }

    private fun moveBalls() {
        for (ball in Ball.balls) {
            ball.relativePosition = ball.relativePosition + ball.movement
        }
    }

    private fun handleFriction() {
        for (ball in Ball.balls) {
            if (ball.movement.length == 0.0) return
            if (ball.movement.length < 0.0730708661416) {
                //ball.movement = Vector(0.0, 0.0)
                return
            }
            val preferredLength = ball.movement.length - 0.0730708661416
            ball.movement = ball.movement / ball.movement.length * preferredLength
        }
    }
}