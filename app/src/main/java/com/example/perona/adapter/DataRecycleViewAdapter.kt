package com.example.perona.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.perona.CloudResponse
import com.example.perona.R

data class ItemData (
    val startTime : String,
    val endTime : String,
    val temperature : String
        )

class DataRecycleViewAdapter(
    private val context: Context,
    private val timeData: List<CloudResponse.RecordData.LocationData.WeatherData.TimeData>?
): RecyclerView.Adapter<DataRecycleViewAdapter.PageHolder>(){

    interface OnFuncListener {
        fun onItemClick(item: ItemData?)
    }

    private var mCallback: OnFuncListener? = null
    fun setOnFuncListener(callback: OnFuncListener) {
        this.mCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        return PageHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item, parent, false))
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val temperature = timeData?.get(position)?.parameter?.parameterName + timeData?.get(position)?.parameter?.parameterUnit
        holder.dogImage.setImageResource(R.drawable.dog)

        holder.data.text = timeData?.get(position)?.startTime  + "\n" +
                timeData?.get(position)?.endTime + "\n" + temperature
        val item = ItemData(timeData!![position].startTime, timeData[position].endTime, temperature)
        holder.data.setOnClickListener{
            mCallback?.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return timeData!!.size
    }

    inner class PageHolder(view: View): RecyclerView.ViewHolder(view){
        val dogImage: ImageView = view.findViewById(R.id.dog)
        val data: TextView = view.findViewById(R.id.cloudData)
    }
}