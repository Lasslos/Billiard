package eu.lh0.gui

import javax.swing.JFrame

object Frame: JFrame() {
    init {
        title = "Billiard"
        defaultCloseOperation = EXIT_ON_CLOSE

        add(Panel)
        pack()

        extendedState = MAXIMIZED_BOTH

        setLocationRelativeTo(null)
        isResizable = false
        isVisible = true
    }
}