package simple.program.accentureexam.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import simple.program.accentureexam.R
import simple.program.accentureexam.databinding.ActivityMainBinding
import simple.program.accentureexam.presentation.ui.main.adapter.FlightListAdapter


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var flightAdapter: FlightListAdapter

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_item_detail) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,  appBarConfiguration)




//        flightAdapter = FlightListAdapter(this)
//        binding.recyclerView.apply {
//            addItemDecoration(DefaultItemDecorator(10, 10))
//            adapter = flightAdapter
//
//            viewModel.restaurants.observe(this@MainActivity) { result ->
//                flightAdapter.submitList(result.data ?: mutableListOf())
//
//                binding.simpleProgressBar.isVisible =
//                    result is Resource.Loading && result.data.isNullOrEmpty()
////            textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
////            textViewError.text = result.error?.localizedMessage
//            }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_item_detail)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }
}

