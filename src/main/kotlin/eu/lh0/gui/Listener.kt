package eu.lh0.gui

import eu.lh0.game.Ball
import eu.lh0.game.Vector
import eu.lh0.utils.WHITE_BALL_START_LOCATION
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

object Listener : KeyListener, MouseListener {
    override fun keyTyped(e: KeyEvent) {}
    override fun keyPressed(e: KeyEvent) {
        for (ball in Ball.balls) {
            ball.movement = Vector(0.0, 0.0)
        }
        Ball.balls = Ball.initialPosition()
    }

    override fun keyReleased(e: KeyEvent) {}

    override fun mouseClicked(e: MouseEvent) {}
    override fun mousePressed(e: MouseEvent) {
        Ball.balls
    //resetWhiteBallMovement(Vector(e.x, e.y))
    }

    override fun mouseReleased(e: MouseEvent) {}
    override fun mouseEntered(e: MouseEvent) {}
    override fun mouseExited(e: MouseEvent) {}

    private fun resetWhiteBallMovement(clickedPosition: Vector) {
        val ball = Ball.balls[0]
        if (!ball.isVisible) {
            ball.isVisible = true
            ball.image.width = 41
            ball.image.height = 41
            ball.relativePosition = WHITE_BALL_START_LOCATION
        }

        var movementVector = clickedPosition - ball.relativePosition
        val preferredLength = 18
        movementVector = movementVector / movementVector.length * preferredLength
        ball.movement = movementVector
    }
}