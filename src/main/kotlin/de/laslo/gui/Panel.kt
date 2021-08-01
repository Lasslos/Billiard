package de.laslo.gui

import de.laslo.util.SortedList
import java.awt.Graphics
import javax.swing.JPanel

object Panel : JPanel() {
    val images = SortedList<Image>()

    override fun paint(g: Graphics) {
        super.paint(g)

        images.forEach {
            if (it.isVisible) g.drawImage(
                it.image,
                it.position.x.toInt(),
                it.position.y.toInt(),
                it.width,
                it.height,
                null
            )
        }
    }
}