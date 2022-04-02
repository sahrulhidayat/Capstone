package com.sahrulhidayat.capstone.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahrulhidayat.capstone.R
import com.sahrulhidayat.capstone.databinding.FragmentHomeBinding
import com.sahrulhidayat.capstone.ui.detail.DetailsActivity
import com.sahrulhidayat.capstone.ui.detail.DetailsActivity.Companion.EXTRA_ID
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.ui.GameAdapter
import com.sahrulhidayat.core.utils.SortUtils
import com.sahrulhidayat.core.utils.gone
import com.sahrulhidayat.core.utils.showSnackbar
import com.sahrulhidayat.core.utils.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.view.clicks

class HomeFragment : Fragment() {

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentHomeBinding

    private val viewModel by viewModel<HomeViewModel>()
    private val gameAdapter: GameAdapter by lazy { GameAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvHome?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        getGameList(SortUtils.NEWEST)
        setFabButton()

        gameAdapter.onClickItem = { data ->
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, data.id)
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
                    view?.showSnackbar(getString(R.string.error_loading))
                }
                else -> {}
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) binding?.progressBar?.visible()
        else binding?.progressBar?.gone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentHomeBinding = null
    }
}