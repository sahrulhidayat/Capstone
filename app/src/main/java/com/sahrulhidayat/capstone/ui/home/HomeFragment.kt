package com.sahrulhidayat.capstone.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahrulhidayat.capstone.R
import com.sahrulhidayat.capstone.databinding.FragmentHomeBinding
import com.sahrulhidayat.capstone.ui.detail.DetailsActivity
import com.sahrulhidayat.capstone.ui.detail.DetailsActivity.Companion.EXTRA_GAME
import com.sahrulhidayat.capstone.ui.search.SearchFragment
import com.sahrulhidayat.core.data.source.Resource
import com.sahrulhidayat.core.ui.GameAdapter
import com.sahrulhidayat.core.utils.SortUtils
import com.sahrulhidayat.core.utils.gone
import com.sahrulhidayat.core.utils.showSnackbar
import com.sahrulhidayat.core.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        viewModel.getGameList(SortUtils.RATING).observe(requireActivity()) { games ->
            when (games) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    gameAdapter.setData(games.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    view.showSnackbar(getString(R.string.error_loading))
                }
            }
        }

        gameAdapter.onClickItem = { data ->
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(EXTRA_GAME, data)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> loadFragment(SearchFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.homeFragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
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