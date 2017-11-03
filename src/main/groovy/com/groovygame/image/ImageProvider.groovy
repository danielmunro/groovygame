package com.groovygame.image

import com.groovygame.ui.Sprite
import com.groovygame.util.Coords

import java.awt.image.BufferedImage

class ImageProvider {
    Sprite sprite

    BufferedImage getProjectileImage() {
        sprite.getImageAtCoords(new Coords(238, 1284), 4, 1)
    }
}
