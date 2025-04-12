package com.example.a712assignment2



import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {

    private val PACKAGE_NAME = "com.example.a712assignment2"
    private val LAUNCH_TIMEOUT = 5000L
    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // back to mainscreen
        device.pressHome()

        // launch app
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
            ?: throw RuntimeException("App not installed or package name is incorrect.")

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        // wait
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT)
    }

    @Test
    fun testStartExplicitActivityAndCheckChallengeText() {
        // click button
        val button = device.wait(
            Until.findObject(By.res(PACKAGE_NAME, "buttonExplicit")),
            2000
        )
        assertNotNull("Start Activity button not found", button)
        button?.click()


        device.waitForIdle()

        // check text challenges
        val challengeText = device.wait(
            Until.findObject(By.textContains("Device Fragmentation")),
            3000
        )
        assertNotNull("Challenge text not found in second activity", challengeText)
    }
}
