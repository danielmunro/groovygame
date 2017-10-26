package com.groovygame.util

import java.awt.Image
import java.awt.image.BufferedImage

class Explosion {
    private Coords coords
    private ArrayList<BufferedImage> animation
    private int frame = 0

    Explosion(Coords coords, ArrayList<BufferedImage> animation) {
        this.coords = coords
        this.animation = animation
    }

    Image getAnimationFrameImage() {
        animation[frame]
    }

    Coords getCoords() {
        coords
    }

    void proceedAnimationFrame() {
        frame++
    }

    boolean hasCompleted() {
        return frame >= animation.size()
    }
}
