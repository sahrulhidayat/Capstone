package com.sahrulhidayat.capstone.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahrulhidayat.capstone.R
import com.sahrulhidayat.capstone.databinding.FragmentSearchBinding
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.ui.GameAdapter
import com.sahrulhidayat.core.utils.gone
import com.sahrulhidayat.core.utils.showSnackbar
import com.sahrulhidayat.core.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _fragmentSearchBinding: FragmentSearchBinding? = null
    private val binding get() = _fragmentSearchBinding

    private val viewModel by viewModel<SearchViewModel>()
    private val gameAdapter: GameAdapter by lazy { GameAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvSearch?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        binding?.svSearchGame?.apply {
            requestFocus()
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    getSearchedGames(newText.orEmpty())
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    getSearchedGames(query.orEmpty())
                    return true
                }
            })
        }
    }

    private fun getSearchedGames(name: String) {
        viewModel.getSearchedGames(name).observe(requireActivity()) { games ->
            when (games) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    gameAdapter.setData(games.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    view?.showSnackbar(getString(R.string.error_loading))
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) binding?.progressBar?.visible()
        else binding?.progressBar?.gone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentSearchBinding = null
    }
}