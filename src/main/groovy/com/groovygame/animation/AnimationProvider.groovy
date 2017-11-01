package com.groovygame.animation

import com.groovygame.ui.Sprite

import java.awt.image.BufferedImage

abstract class AnimationProvider {
    protected List<BufferedImage> animationFrames

    protected abstract void createAnimationFramesFromSprite(Sprite sprite)

    List<BufferedImage> getAnimationFrames() {
        animationFrames
    }
}
