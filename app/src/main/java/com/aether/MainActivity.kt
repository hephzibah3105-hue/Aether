package com.aether
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aether.ui.theme.AetherTheme
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.aether.automation.CleanupWorker
import androidx.work.Constraints
import androidx.work.NetworkType
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()
        WorkManager.getInstance(this)
            .cancelAllWorkByTag("AETHER_CLEANUP")

        val workRequest = OneTimeWorkRequestBuilder<CleanupWorker>()
            .addTag("AETHER_CLEANUP")
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
        setContent {
            AetherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AetherTheme {
        Greeting("Android")
    }
}