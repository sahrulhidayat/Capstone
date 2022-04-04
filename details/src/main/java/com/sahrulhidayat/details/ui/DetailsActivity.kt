package com.sahrulhidayat.details.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.utils.gone
import com.sahrulhidayat.core.utils.loadImage
import com.sahrulhidayat.core.utils.showSnackbar
import com.sahrulhidayat.core.utils.visible
import com.sahrulhidayat.details.R
import com.sahrulhidayat.details.databinding.ActivityDetailsBinding
import com.sahrulhidayat.details.di.detailsModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailsActivity : AppCompatActivity() {

    private var _activityDetailsBinding: ActivityDetailsBinding? = null
    private val binding get() = _activityDetailsBinding

    private val viewModel by viewModel<DetailsViewModel>()

    private val loadFeatures by lazy { loadKoinModules(detailsModule) }
    private fun injectFeatures() = loadFeatures

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        injectFeatures()

        supportActionBar?.title = null

        val gameId = intent.getIntExtra(EXTRA_ID, 0)

        viewModel.getGameDetails(gameId).observe(this) { gameDetails ->
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

    private fun setDetailsData(gameDetails: GameModel?) {
        if (gameDetails != null) {
            binding?.apply {
                applicationContext.loadImage(gameDetails.background, imgPoster)
                content.txtName.text = gameDetails.name
                content.txtRating.text = gameDetails.rating.toString()
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
        const val EXTRA_ID = "extra_id"
    }
}