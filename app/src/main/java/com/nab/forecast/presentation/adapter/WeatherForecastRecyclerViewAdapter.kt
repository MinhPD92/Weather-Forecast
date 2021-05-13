package com.nab.forecast.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nab.domain.models.WeatherInfo
import com.nab.forecast.databinding.LayoutWeatherItemBinding

class WeatherForecastRecyclerViewAdapter :
    ListAdapter<WeatherInfo, WeatherForecastViewHolder>(WeatherForecastDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val viewBinding =
            LayoutWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherForecastViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.setData(getItem(position))
    }
}

private class WeatherForecastDiffCallback : DiffUtil.ItemCallback<WeatherInfo>() {
    override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
        return oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
        return oldItem == newItem
    }
}

class WeatherForecastViewHolder(private val viewBinding: LayoutWeatherItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun setData(item: WeatherInfo) {
        with(viewBinding) {
            tvDate.text = item.time
            tvAverageTemp.text = item.averageTemperature
            tvPressure.text = item.pressure
            tvHumidity.text = item.humidity
            tvDescription.text = item.description
        }

    }
}

