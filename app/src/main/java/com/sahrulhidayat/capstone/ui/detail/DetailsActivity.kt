package com.sahrulhidayat.capstone.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sahrulhidayat.capstone.R
import com.sahrulhidayat.capstone.databinding.ActivityDetailsBinding
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.utils.gone
import com.sahrulhidayat.core.utils.loadImage
import com.sahrulhidayat.core.utils.showSnackbar
import com.sahrulhidayat.core.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private var _activityDetailsBinding: ActivityDetailsBinding? = null
    private val binding get() = _activityDetailsBinding

    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        val game = intent.getParcelableExtra<GameModel>(EXTRA_GAME)

        if (game != null) {
            binding?.apply {
                txtName.text = game.name
                applicationContext.loadImage(game.background, imgPoster)
                txtRating.text = game.rating.toString()
            }

            viewModel.getGameDetails(game.id).observe(this) { gameDetails ->
                when (gameDetails) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        setDetailsData(gameDetails.data)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        binding?.root?.showSnackbar(getString(R.string.error_loading))
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setDetailsData(gameDetails: GameModel?) {
        if (gameDetails != null) {
            binding?.apply {
                content.txtDescription.text = gameDetails.description
                content.txtRelease.text = gameDetails.released
                content.txtGenre.text = gameDetails.genres
                content.txtTags.text = gameDetails.tags

                var favoriteStatus = gameDetails.isFavorite
                setFavoriteIndicator(favoriteStatus)
                fabFavorite.setOnClickListener {
                    favoriteStatus = !favoriteStatus
                    viewModel.setFavoriteGame(gameDetails, favoriteStatus)
                    setFavoriteIndicator(favoriteStatus)
                }
            }
        }
    }

    private fun setFavoriteIndicator(isFavorite: Boolean) {
        if (isFavorite) {
            binding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) binding?.progressBar?.visible()
        else binding?.progressBar?.gone()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailsBinding = null
    }

    companion object {
        const val EXTRA_GAME = "extra_game"
    }
}