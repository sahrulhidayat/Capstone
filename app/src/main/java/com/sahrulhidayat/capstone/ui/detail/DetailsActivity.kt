package com.sahrulhidayat.capstone.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sahrulhidayat.capstone.databinding.ActivityDetailsBinding
import com.sahrulhidayat.core.domain.model.GameModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private var _activityDetailsBinding: ActivityDetailsBinding? = null
    private val binding get() = _activityDetailsBinding

    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(_activityDetailsBinding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val game = intent.getParcelableExtra<GameModel>(EXTRA_GAME)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailsBinding = null
    }
}