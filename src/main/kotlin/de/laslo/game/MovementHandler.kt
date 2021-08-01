package de.laslo.game

import de.laslo.gui.Frame
import de.laslo.holes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object MovementHandler {
    init {
        GlobalScope.launch {
            while (true) {
                checkForBallCollision()
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
                if (ball1.position.getDistance(ball2.position) < 41)
                    handleBallCollision(ball1, ball2)
            }
        }
    }

    private fun handleBallCollision(ball1: Ball, ball2: Ball) {

    }

    private fun handleBallWallCollision() {
        for (ball in Ball.balls) {
            val x = ball.position.x
            val y = ball.position.y

            //if (y - BALL_HEIGHT_WIDTH / 2 < UPPER_WALL_RELATIVE && (/*TODO*/))
        }
    }

    private fun checkForBallInGap() {
        balls@ for (ball in Ball.balls) {
            holes@ for (vector in holes) {
                if (ball.position.getDistance(vector) < 50) {
                    handleBallInGap(ball)
                    continue@balls
                }
            }
        }
    }

    private fun handleBallInGap(ball: Ball) {
        if (!ball.isInGap) {
            ball.isInGap = true
            GlobalScope.launch {
                var i = 41
                while (i >= 0) {
                    ball.position = ball.position - 1
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
            ball.position = ball.position + ball.movement
        }
    }

    private fun handleFriction() {
        for (ball in Ball.balls) {
            if (ball.movement.length == 0.0) return
            if (ball.movement.length < 0.00730708661416) {
                ball.movement = Vector(0.0, 0.0)
                return
            }
            val preferredLength = ball.movement.length - 0.00730708661416
            ball.movement = ball.movement / ball.movement.length * preferredLength
        }
    }
}