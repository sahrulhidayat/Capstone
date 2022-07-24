package com.sahrulhidayat.home.ui

import androidx.test.core.app.ActivityScenario
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.sahrulhidayat.capstone.ui.MainActivity
import com.sahrulhidayat.home.R
import org.junit.Before
import org.junit.Test

class HomeFragmentTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadHomeFragment() {
        assertDisplayed(R.id.homeFragment)
        scrollListToPosition(R.id.rvHome, 10)
    }
}