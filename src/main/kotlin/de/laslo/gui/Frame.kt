package de.laslo.gui

import de.laslo.RES_DIRECTORY
import de.laslo.game.Vector
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.WindowConstants
import kotlin.system.exitProcess

object Frame : JFrame("Billiard") {
    init {
        size = Dimension(1920, 1080)
        setLocationRelativeTo(null)
        isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        requestFocus()
        addMouseListener(Listener)
        addKeyListener(Listener)
        add(Panel)
        isVisible = true

        lateinit var background: BufferedImage
        try {
            background = ImageIO.read(File("$RES_DIRECTORY/table.png"))
        } catch (e: IOException) {
            e.printStackTrace()
            println("Images could not be loaded!")
            exitProcess(-1)
        }

        Image(background, 1, Vector(0.0, 0.0), true, 1920, 1080)
    }
}