package simple.program.accentureexam.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import simple.program.accentureexam.R
import kotlinx.coroutines.flow.collect
import simple.program.accentureexam.data.cache.model.FlightsDataEntity
import simple.program.accentureexam.databinding.FragmentListBinding
import simple.program.accentureexam.presentation.ui.MainViewModel
import simple.program.accentureexam.presentation.ui.details.DetailFragment.Companion.DETAILS_EXTRA
import simple.program.accentureexam.presentation.ui.main.adapter.FlightListAdapter
import simple.program.accentureexam.util.Resource
import simple.program.accentureexam.util.deocrator.DefaultItemDecorator

@AndroidEntryPoint
class ListFragment :Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var flightAdapter: FlightListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        lifecycleScope.launchWhenStarted  {
            viewModel.effect.collect {
                when (it) {
                    is  MainViewModel.Event.ShowErrorMessage -> {
                        Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        flightAdapter = FlightListAdapter(object : FlightListAdapter.Interaction {
            override fun onItemSelected(position: Int, item: FlightsDataEntity) {
                if (itemDetailFragmentContainer != null) {
                    val bundle = Bundle()
                    bundle.putParcelable(
                        DETAILS_EXTRA,
                        item
                    )
                    itemDetailFragmentContainer.findNavController()
                        .navigate(R.id.detailFragment2, bundle)
                } else {
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(item)
                    view.findNavController().navigate(action)
                }
            }
        })
        binding.recyclerView.apply {
            addItemDecoration(DefaultItemDecorator(10, 10))
            adapter = flightAdapter

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.flightData.collect {
                    val result = it ?: return@collect
                    binding.simpleProgressBar.isVisible =
                          result is Resource.Loading && result.data.isNullOrEmpty()

                    flightAdapter.submitList(result.data ?: mutableListOf())
                }
            }
        }
    }
}

