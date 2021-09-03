package simple.program.accentureexam.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import simple.program.accentureexam.R
import simple.program.accentureexam.data.cache.model.FlightsDataEntity
import simple.program.accentureexam.databinding.FragmentDetailsBinding

class DetailFragment  : Fragment() {

    private var item: FlightsDataEntity? = null

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args : DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = args.item

        arguments?.let {
            if (it.containsKey(DETAILS_EXTRA)) {
                item = it.getParcelable(DETAILS_EXTRA)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Show the placeholder content as text in a TextView.
        item?.let { data ->
            binding.apply {
                tvName.text = getString(R.string.airport_name, data.airportName)
                tvCityCode.text = getString(R.string.city_code, data.cityCode)
                tvCityName.text = getString(R.string.city_name, data.cityName)
                tvTimeZoneName.text = getString(R.string.time_zone, data.timeZoneName)
                tvCountryCode.text = getString(R.string.country_code, data.countryCode)
                tvCountryName.text = getString(R.string.country_name, data.countryCode)

                btnBack.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }

        return rootView
    }


    companion object {
        const val DETAILS_EXTRA = "EXTRA_SESSION_ID"
    }
}