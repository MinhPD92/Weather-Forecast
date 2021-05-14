package com.nab.forecast.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nab.data.DailyWeatherForecastResult
import com.nab.domain.models.WeatherInfo
import com.nab.forecast.R
import com.nab.forecast.databinding.ActivityMainBinding
import com.nab.forecast.deps.DependenciesInjectionProvider
import com.nab.forecast.extension.getTimeAtBeginningOfDay
import com.nab.forecast.extension.hideKeyboard
import com.nab.forecast.extension.setOnDebounceClickListener
import com.nab.forecast.presentation.adapter.WeatherForecastRecyclerViewAdapter
import com.scottyab.rootbeer.RootBeer
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DependenciesInjectionProvider).activityInjectionProvider().inject(this)
        super.onCreate(savedInstanceState)

        if (isRootDevice()) {
            Toast.makeText(this, getString(R.string.error_run_on_rooted_device), Toast.LENGTH_SHORT)
                .show()
            finish()
            return
        }

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()
        setupListener()
        setupObserver()
        checkToClearCache()
    }

    private fun isRootDevice(): Boolean {
        val rootBeer = RootBeer(this)
        return rootBeer.isRooted
    }

    private fun checkToClearCache() {
        val time = Calendar.getInstance().getTimeAtBeginningOfDay()
        mainViewModel.clearWeatherForecastCacheIfNeed(time)
    }

    private fun setupListener() {
        viewBinding.btnGetWeather.setOnDebounceClickListener {
            val cityName = viewBinding.editQuery.text
            if (cityName.length < 3) {
                Toast.makeText(
                    this,
                    getString(R.string.error_not_reach_minimum_character),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                hideKeyboard(viewBinding.editQuery)
                mainViewModel.getDailyWeatherForecast(cityName = cityName.toString())
            }
        }
    }

    private fun setupObserver() {
        mainViewModel.state.observe(this, {
            onStateChanged(it)
        })
    }

    private fun onStateChanged(state: MainState) {
        when (state) {
            is MainState.LoadingState -> showLoading()
            is MainState.ErrorState -> showError(state.errorType)
            is MainState.SucceedState -> onDataReturn(state.results)
        }
    }

    private fun onDataReturn(results: List<WeatherInfo>) {
        hideLoading()
        hideError()
        viewBinding.rvWeatherForecast.visibility = View.VISIBLE
        (viewBinding.rvWeatherForecast.adapter as WeatherForecastRecyclerViewAdapter).submitList(
            results
        )
    }

    private fun showError(error: DailyWeatherForecastResult.DailyWeatherForecastError) {
        hideLoading()
        hideRecyclerView()
        viewBinding.tvError.visibility = View.VISIBLE
        viewBinding.tvError.text = getErrorMessage(error)
    }

    private fun getErrorMessage(error: DailyWeatherForecastResult.DailyWeatherForecastError): String {
        return when (error) {
            is DailyWeatherForecastResult.NoDataFoundError -> {
                getString(R.string.error_no_data_found)
            }
            is DailyWeatherForecastResult.NetWorkError -> {
                getString(R.string.error_network)
            }
            is DailyWeatherForecastResult.UnCatchError -> {
                error.errorMessage ?: getString(R.string.error_unknown)
            }
            else -> {
                getString(R.string.error_unknown)
            }
        }
    }

    private fun hideRecyclerView() {
        viewBinding.rvWeatherForecast.visibility = View.GONE
    }

    private fun hideError() {
        viewBinding.tvError.visibility = View.GONE
    }

    private fun showLoading() {
        viewBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        viewBinding.progressBar.visibility = View.GONE
    }

    private fun initRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val adapter = WeatherForecastRecyclerViewAdapter()
        viewBinding.rvWeatherForecast.adapter = adapter
        viewBinding.rvWeatherForecast.layoutManager = LinearLayoutManager(this)
        viewBinding.rvWeatherForecast.addItemDecoration(dividerItemDecoration)
    }
}