package com.groovygame.image

import com.groovygame.ui.Sprite
import com.groovygame.util.Coords

import java.awt.image.BufferedImage

class ImageProvider {
    Sprite sprite

    BufferedImage getProjectileImage() {
        sprite.getImageAtCoords(new Coords(238, 1284), 4, 1)
    }

    BufferedImage getHeroImage() {
        sprite.getImageAtCoords(new Coords(0, 3))
    }

    BufferedImage getRockTile() {
        sprite.getImageAtCoords(new Coords(0, 14))
    }

    BufferedImage getGrassTile() {
        sprite.getImageAtCoords(new Coords(0, 15))
    }
}
