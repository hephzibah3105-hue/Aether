package com.aether.ai

class ImageScorer {

    fun score(blur: Boolean, faceCount: Int): Int {
        var score = 0
        if (!blur) score += 50
        score += faceCount * 10
        return score
    }
}