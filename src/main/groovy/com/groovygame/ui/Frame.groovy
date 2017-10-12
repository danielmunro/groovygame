package com.groovygame.ui

import javax.swing.JFrame
import javax.swing.WindowConstants
import java.awt.Color

class Frame extends JFrame {
    Frame() {
        setSize(1024, 768)
        setBackground(Color.BLACK)
        setTitle("Frame")
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
        setLocationRelativeTo(null)
        setFocusable(true)
        requestFocusInWindow()
    }
}