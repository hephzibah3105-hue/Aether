package com.aether.automation

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aether.scanner.FileScanner
import com.aether.lifecycle.LifecycleEngine
import com.aether.lifecycle.ValueProfile
import com.aether.lifecycle.LifecycleState
import com.aether.automation.ColdStorageManager
import com.aether.lifecycle.DecisionExplanation
class CleanupWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        val scanner = FileScanner(applicationContext)
        val engine = LifecycleEngine()
        val coldStorage = ColdStorageManager(applicationContext)

        val profile = ValueProfile(
            keepFamilyPhotos = true,
            screenshotRetentionDays = 3,
            autoDeletePdfs = false
        )

        val explanation = engine.evaluateWithExplanation(
            fileName = "screenshot_123.png",
            lastUsedDays = 5
        )

        when (explanation.decision) {
            LifecycleState.CANDIDATE,
            LifecycleState.EXPIRED -> {
                coldStorage.archiveFile(explanation.fileName)
            }
            else -> {
                println("Aether kept the file untouched")
            }
        }

// 🧾 Explainability log
        println(
            "Aether decision → File: ${explanation.fileName}, " +
                    "Action: ${explanation.decision}, " +
                    "Reason: ${explanation.reason}"
        )

        return Result.success()
    }
}