package com.groovygame.animation

import com.groovygame.ui.Sprite

import java.awt.image.BufferedImage

abstract class Animation {
    protected int currentAnimationFrame
    protected List<BufferedImage> animationFrames

    abstract void createAnimationFramesFromSprite(Sprite sprite)
}
