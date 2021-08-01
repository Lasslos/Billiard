package de.laslo.gui

import de.laslo.game.Vector
import java.awt.image.BufferedImage

class Image(
    val image: BufferedImage,
    val layer: Int,
    var position: Vector = Vector(0.0, 0.0),
    var isVisible: Boolean = true,
    var width: Int = image.width,
    var height: Int = image.height
) : Comparable<Image> {

    init {
        Panel.images.add(this)
        Panel.repaint()
    }

    override fun compareTo(other: Image): Int = this.layer - other.layer
}