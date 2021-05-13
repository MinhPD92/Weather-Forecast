package com.nab.forecast.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nab.domain.models.WeatherInfo
import com.nab.forecast.R
import com.nab.forecast.databinding.ActivityMainBinding
import com.nab.forecast.deps.DependenciesInjectionProvider
import com.nab.forecast.extension.hideKeyboard
import com.nab.forecast.extension.setOnDebounceClickListener
import com.nab.forecast.presentation.adapter.WeatherForecastRecyclerViewAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DependenciesInjectionProvider).activityInjectionProvider().inject(this)
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()
        setupListener()
        setupObserver()
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
            is MainState.ErrorState -> showError(state.errorMessage)
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

    private fun showError(errorMessage: String?) {
        hideLoading()
        hideRecyclerView()
        viewBinding.tvError.visibility = View.VISIBLE
        viewBinding.tvError.text = errorMessage ?: getString(R.string.error_unknown)
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