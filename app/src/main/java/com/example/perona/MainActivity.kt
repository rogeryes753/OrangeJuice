package com.example.perona

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perona.adapter.DataRecycleViewAdapter
import com.example.perona.adapter.ItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var cloudResponse : CloudResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val authorization = "CWB-DF4B14B4-DFA3-4868-B1C1-283C0141726E"
        val format = "JSON"
        val locationName = listOf("臺北市")
        val apiService = APIClient.client.create(CloudAPI::class.java)
        val recyclerView : RecyclerView? = this.findViewById(R.id.dataRecyclerView)

        val preferences: SharedPreferences = this.getSharedPreferences("readyComeIn", 0)
        val isComeIn = preferences.getBoolean("comeIn", false)
        if (!isComeIn) {
            preferences.edit().putBoolean("comeIn", true).apply()
        }
        else {
            Toast.makeText(this, "歡迎回來", Toast.LENGTH_LONG).show()
        }
        apiService.data(authorization, format, locationName ).enqueue(object : Callback<CloudResponse> {
            override fun onResponse(call: Call<CloudResponse>, response: Response<CloudResponse>) {
                if (response.isSuccessful) {
                    cloudResponse = response.body()
                    setRecyclerView(recyclerView, cloudResponse)
                }
            }

            override fun onFailure(call: Call<CloudResponse>, t: Throwable) {
                Log.d("tesstttttt fail:", "$call and t : $t")
            }

        })
    }


    private  fun setRecyclerView(recyclerView: RecyclerView?, cloudResponse: CloudResponse?) {
        val mLayoutManager = GridLayoutManager(this, 1)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        var timeData : List<CloudResponse.RecordData.LocationData.WeatherData.TimeData> = listOf()
        cloudResponse?.records?.location?.forEach {
            if ( it.locationName == "臺北市") {
                it.weatherElement.forEach { data ->
                    if (data.elementName == "MinT") timeData = data.time
                }
            }
        }
        val adapter = DataRecycleViewAdapter(this, timeData)

        val callback = object : DataRecycleViewAdapter.OnFuncListener {
            override fun onItemClick(item: ItemData?) {
                val secondPage = SecondPageDialogFragment(item)
                val manager = this@MainActivity.supportFragmentManager
                secondPage.show(manager,"secondPage")
            }
        }

        adapter.setOnFuncListener(callback)

        recyclerView?.layoutManager = mLayoutManager
        recyclerView!!.adapter = adapter
    }




}