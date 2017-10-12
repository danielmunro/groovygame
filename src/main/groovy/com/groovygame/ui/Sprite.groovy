package com.groovygame.ui

import com.groovygame.Constants
import com.groovygame.Coords

import java.awt.image.BufferedImage

class Sprite {
    private BufferedImage image

    BufferedImage getImageAtCoords(Coords coords) {
        return image.getSubimage(
                coords.getX() * Constants.TILE_SIZE,
                coords.getY() * Constants.TILE_SIZE,
                Constants.TILE_SIZE,
                Constants.TILE_SIZE
        )
    }

    BufferedImage getImageAtCoords(Coords coords, int width, int height) {
        return image.getSubimage(
                coords.getX(),
                coords.getY(),
                width,
                height
        )
    }
}