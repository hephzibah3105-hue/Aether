package com.aether.lifecycle

data class ValueProfile(
    val keepFamilyPhotos: Boolean = true,
    val screenshotRetentionDays: Int = 7,
    val autoDeletePdfs: Boolean = false
)