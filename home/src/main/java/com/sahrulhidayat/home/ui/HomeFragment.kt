package com.sahrulhidayat.home.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sahrulhidayat.capstone.ui.MainActivity
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.ui.GameAdapter
import com.sahrulhidayat.core.utils.SortUtils
import com.sahrulhidayat.core.utils.gone
import com.sahrulhidayat.core.utils.visible
import com.sahrulhidayat.details.ui.DetailsActivity
import com.sahrulhidayat.home.R
import com.sahrulhidayat.home.databinding.FragmentHomeBinding
import com.sahrulhidayat.home.di.homeModule
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import reactivecircus.flowbinding.android.view.clicks

class HomeFragment : Fragment() {

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentHomeBinding

    private val viewModel by viewModel<HomeViewModel>()
    private val gameAdapter: GameAdapter by lazy { GameAdapter() }

    private val loadFeatures by lazy { loadKoinModules(homeModule) }
    private fun injectFeatures() = loadFeatures

    private var snackbar: Snackbar? = null

    override fun onStart() {
        super.onStart()
        binding?.rvHome?.adapter = gameAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectFeatures()

        (activity as MainActivity).supportActionBar?.title = getString(R.string.title_home)

        binding?.rvHome?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        getGameList(SortUtils.NEWEST)
        setFabButton()

        gameAdapter.onClickItem = { data ->
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_ID, data.id)
            startActivity(intent)
        }
    }

    private fun setFabButton() {
        binding?.apply {
            btnNewest.clicks()
                .onEach { getGameList(SortUtils.NEWEST) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
            btnTopRated.clicks()
                .onEach { getGameList(SortUtils.RATING) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
            btnRandom.clicks()
                .onEach { getGameList(SortUtils.RANDOM) }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun getGameList(sort: String) {
        viewModel.getGameList(sort).observe(viewLifecycleOwner) { games ->
            when (games) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    gameAdapter.setData(games.data)
                    binding?.rvHome?.scrollToPosition(0)
                }
                is Resource.Error -> {
                    showLoading(false)
                    snackbar = Snackbar.make(requireView(), R.string.error_loading, Snackbar.LENGTH_LONG)
                }
                else -> {}
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) binding?.progressBar?.visible()
        else binding?.progressBar?.gone()
    }

    override fun onStop() {
        super.onStop()
        snackbar?.dismiss()
        binding?.rvHome?.adapter = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentHomeBinding = null
    }
}