package com.example.perona


data class CloudResponse(
    val success : Boolean,
    val result : ResultData,
    val records : RecordData
    ) {
    data class ResultData (
        val resource_id : String,
        val fields : List<FieldData>,
    ) {
        data class FieldData(
            val id: String,
            val type: String
        )
    }

    data class RecordData(
        val datasetDescription: String,
        val location: List<LocationData>
    ) {
        data class LocationData(
            val locationName: String,
            val weatherElement: List<WeatherData>
        ) {
            data class WeatherData(
                val elementName: String,
                val time: List<TimeData>
            ) {
                data class TimeData(
                    val startTime: String,
                    val endTime: String,
                    val parameter: Pardata
                ) {
                    data class Pardata(
                        val parameterName: String,
                        val parameterUnit: String
                    )
                }
            }
        }
    }

}