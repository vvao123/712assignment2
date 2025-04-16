package com.example.a712assignment2

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {
    private val customPermission = "com.example.a712assignment2.MSE412"
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show()
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            _712assignment2Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
        if (ContextCompat.checkSelfPermission(this, customPermission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(customPermission)
        }
        setContentView(R.layout.activity_main)
        val buttonExplicit: Button = findViewById(R.id.buttonExplicit)
        buttonExplicit.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val buttonImplicit: Button = findViewById(R.id.buttonImplicit)
        buttonImplicit.setOnClickListener {
            val implicitIntent = Intent("com.example.ACTION_DISPLAY")
            startActivity(implicitIntent)
        }
        val viewImageActivityBtn: Button = findViewById(R.id.viewImageActivityBtn)
        viewImageActivityBtn.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    _712assignment2Theme {
//        Greeting("Android")
//    }
//}