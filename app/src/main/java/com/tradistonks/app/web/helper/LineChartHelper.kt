package com.tradistonks.app.web.helper

import android.R.bool
import com.tradistonks.app.components.charts.line.Point
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors
import kotlin.math.pow
import kotlin.math.roundToInt


class LineChartHelper {
    companion object {
        fun createNumericLabels(points: List<Point>, step: Int, toDate: Boolean): List<String>{
            val labels: List<String> = points.stream().map(Point::label).distinct().collect(Collectors.toList())

            if(toDate){
                val numericLabels: List<Long> = labels.map(String::toLong)
                val minAndMaxNumericLabels: Pair<Long, Long> = findMinMaxOfLabelsLong(numericLabels)
                val LabelsFromInterval: List<Long> = createNumericalLabelsFromIntervalLong(min= minAndMaxNumericLabels.first,
                    max= minAndMaxNumericLabels.second, step = step)
                val dateList: List<String> =  LabelsFromInterval.stream().map{
                        number -> Date(number).toString()
                }.collect(Collectors.toList())
                return dateList
            }

            if(points[0].label.toDoubleOrNull() != null){
                val numericLabels: List<Double> = labels.map(String::toDouble)
                val minAndMaxNumericLabels: Pair<Double, Double> = findMinMaxOfLabelsDouble(numericLabels)
                val LabelsFromInterval: List<Double> = createNumericalLabelsFromIntervalDouble(min= minAndMaxNumericLabels.first,
                    max= minAndMaxNumericLabels.second, step = step)

                return LabelsFromInterval.map(Double::toString)
            }

            return labels
        }

        fun findIntervalForLabelsDouble(min: Double, max: Double, step: Int): Double {
            return (max - min) / step
        }

        fun findIntervalForLabelsLong(min: Long, max: Long, step: Int): Long {
            return (max - min) / step
        }

        fun createNumericalLabelsFromIntervalDouble(min: Double, max: Double, step: Int): List<Double> {
            val interval = findIntervalForLabelsDouble(min, max, step = step-1)
            val listLabels = ArrayList<Double>()
            for (index in 0 until step){
                listLabels.add((min + index * interval).roundTo(2))
            }
            return listLabels
        }

        fun createNumericalLabelsFromIntervalLong(min: Long, max: Long, step: Int): List<Long> {
            val interval = findIntervalForLabelsLong(min, max, step = step-1)
            val listLabels = ArrayList<Long>()
            for (index in 0 until step){
                listLabels.add(min + index * interval)
            }
            return listLabels
        }

        fun findMinMaxOfLabelsDouble(numericLabels: List<Double>): Pair<Double, Double> {
            val minValue = numericLabels.minOrNull()!!
            val maxValue = numericLabels.maxOrNull()!!
            return Pair(minValue, maxValue)
        }

        fun findMinMaxOfLabelsLong(numericLabels: List<Long>): Pair<Long, Long> {
            val minValue = numericLabels.minOrNull()!!
            val maxValue = numericLabels.maxOrNull()!!
            return Pair(minValue, maxValue)
        }

        fun Double.roundTo(numFractionDigits: Int): Double {
            val factor = 10.0.pow(numFractionDigits.toDouble())
            return (this * factor).roundToInt() / factor
        }

        fun isTimeStampValid(inputString: String): Boolean {
            val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS")
            try {
                format.parse(inputString)
                return true
            } catch (e: ParseException) {
                return false
            }
        }

    }
}