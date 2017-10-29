package com.groovygame.animation

import com.groovygame.ui.Sprite
import com.groovygame.util.Coords

class BlueExplosionAnimation extends Animation {

    @Override
    void createAnimationFramesFromSprite(Sprite sprite) {
        animationFrames = [
                sprite.getImageAtCoords(Coords.at(19, 10)),
                sprite.getImageAtCoords(Coords.at(18, 10)),
                sprite.getImageAtCoords(Coords.at(17, 10))
        ]
    }
}
