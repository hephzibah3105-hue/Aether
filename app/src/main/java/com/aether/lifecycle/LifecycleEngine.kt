package com.aether.lifecycle
import com.aether.lifecycle.LifecycleState
class LifecycleEngine {

    fun evaluateWithExplanation(
        fileName: String,
        lastUsedDays: Int
    ): DecisionExplanation {

        return when {
            fileName.contains("ticket", ignoreCase = true) && lastUsedDays > 1 ->
                DecisionExplanation(
                    fileName,
                    LifecycleState.EXPIRED,
                    "Temporal document expired after event date"
                )

            fileName.contains("screenshot", ignoreCase = true) && lastUsedDays > 3 ->
                DecisionExplanation(
                    fileName,
                    LifecycleState.CANDIDATE,
                    "Screenshot exceeded user retention preference"
                )

            else ->
                DecisionExplanation(
                    fileName,
                    LifecycleState.ACTIVE,
                    "File aligns with user value profile"
                )
        }
    }
}