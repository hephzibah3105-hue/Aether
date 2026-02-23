package com.aether.lifecycle

data class DecisionExplanation(
    val fileName: String,
    val decision: LifecycleState,
    val reason: String
)