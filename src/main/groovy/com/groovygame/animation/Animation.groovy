package com.groovygame.animation

import com.groovygame.util.Coords

import java.awt.Image
import java.awt.image.BufferedImage

class Animation {
    private Coords coords
    private ArrayList<BufferedImage> animation
    private int frame = 0

    Animation(Coords coords, List<BufferedImage> animation) {
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
