package eu.lh0.gui

import eu.lh0.game.Ball
import eu.lh0.utils.FRAME_DIMENSION
import eu.lh0.utils.PLAYING_FIELD_ORIGIN
import eu.lh0.utils.RES_DIRECTORY
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JPanel

object Panel: JPanel() {

    private val background: BufferedImage = ImageIO.read(File("$RES_DIRECTORY/table.png"))

    init {
        preferredSize = FRAME_DIMENSION
        addKeyListener(Listener)
        addMouseListener(Listener)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(background, 0, 0,null)
        balls@for (ball in Ball.balls) {
            if (!ball.isVisible) {
                continue@balls
            }

            g.drawImage(
                ball.image.image,
                (ball.realPosition.x).toInt(),
                (ball.realPosition.y).toInt(),
                ball.image.width,
                ball.image.height,
                null,
            )
        }
    }
}