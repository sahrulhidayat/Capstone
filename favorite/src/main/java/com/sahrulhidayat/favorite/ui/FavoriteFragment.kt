package com.sahrulhidayat.favorite.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.core.splitcompat.SplitCompat
import com.sahrulhidayat.capstone.ui.MainActivity
import com.sahrulhidayat.capstone.ui.detail.DetailsActivity
import com.sahrulhidayat.core.ui.GameAdapter
import com.sahrulhidayat.favorite.R
import com.sahrulhidayat.favorite.databinding.FragmentFavoriteBinding
import com.sahrulhidayat.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _fragmentFavoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _fragmentFavoriteBinding

    private val viewModel by viewModel<FavoriteViewModel>()
    private val gameAdapter: GameAdapter by lazy { GameAdapter() }

    private val loadFeatures by lazy { loadKoinModules(favoriteModule) }
    private fun injectFeatures() = loadFeatures

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectFeatures()

        (activity as MainActivity).supportActionBar?.title = getString(R.string.title_favorite)

        binding?.rvFavorite?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        viewModel.getAllFavoriteGame().observe(viewLifecycleOwner) { games ->
            gameAdapter.setData(games)
        }

        gameAdapter.onClickItem = { data ->
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_ID, data.id)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteBinding = null
    }
}