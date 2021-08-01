package de.laslo.gui

import de.laslo.WHITE_BALL_START_LOCATION
import de.laslo.game.Ball
import de.laslo.game.Vector
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
        Ball.setBallsToInitialPosition()
    }

    override fun keyReleased(e: KeyEvent) {}

    //Mouse isn't accurate because of the JFrame header, do the following if x || y is needed: x - 7; y - 31
    override fun mouseClicked(e: MouseEvent) {}
    override fun mousePressed(e: MouseEvent) {
        resetWhiteBallMovement(Vector(e.x - 7.0, e.y - 31.0))
    }

    override fun mouseReleased(e: MouseEvent) {}
    override fun mouseEntered(e: MouseEvent) {}
    override fun mouseExited(e: MouseEvent) {}

    private fun resetWhiteBallMovement(clickedPosition: Vector) {
        val ball = Ball.balls[0]
        if (!ball.isVisible) {
            ball.isVisible = true
            ball.position = WHITE_BALL_START_LOCATION
            ball.isInGap = false
        }

        var movementVector = clickedPosition - ball.position
        val preferredLength = 1.8
        movementVector = movementVector / movementVector.length * preferredLength
        ball.movement = movementVector
    }
}