package com.ahanafrifat.yourplants.enhos.presentation.util

import kotlin.math.roundToInt

object AmplitudeNormalizer {
    fun normalize(
        sourceAmplitude: List<Float>,
        trackWidth: Float,
        barWidth: Float,
        spacing: Float
    ): List<Float> {
        require(trackWidth >= 0f) {
            "Track width must be positive"
        }
        require(trackWidth >= barWidth + spacing) {
            "Track width must be at least the size of one bar and spacing"
        }
        if(sourceAmplitude.isEmpty()){
            return emptyList()
        }

        val barsCount = (trackWidth / (barWidth + spacing)).roundToInt()
        val resampled = resampledAmplitudes(sourceAmplitude, barsCount)
    }

    private fun resampledAmplitudes(
        sourceAmplitude: List<Float>,
        targetSize: Int
    ): List<Float> {
        return when{
            targetSize == sourceAmplitude.size -> sourceAmplitude
            targetSize < sourceAmplitude.size -> downsample(sourceAmplitude, targetSize)
            else -> upsample(sourceAmplitude,targetSize)
        }
    }
    private fun downsample(source: List<Float>, targetSize: Int): List<Float> {
        val ratio = source.size.toFloat() / targetSize
        return List(targetSize){index ->
            val start = (index * ratio).toInt()
            val end = ((index + 1) * ratio).toInt().coerceAtMost(source.size)

            source.subList(start, end).max()
        }
    }
    private fun upsample(source: List<Float>, targetSize: Int): List<Float> {
        val ratio = source.size.toFloat() / targetSize
        return List(targetSize){index ->
            val start = (index * ratio).toInt()
            val end = ((index + 1) * ratio).toInt().coerceAtMost(source.size)

            source.subList(start, end).max()
        }
    }
}