package com.ahanafrifat.yourplants.enhos.presentation.util

import kotlin.math.roundToInt

object AmplitudeNormalizer {

    private const val AMPLITUDE_MIN_OUTPUT_THRESHOLD = 0.1f
    private const val MIN_OUTPUT = 0.25f
    private const val MAX_OUTPUT = 1f

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
        if (sourceAmplitude.isEmpty()) {
            return emptyList()
        }

        val barsCount = (trackWidth / (barWidth + spacing)).roundToInt()
        val resampled = resampledAmplitudes(sourceAmplitude, barsCount)
        val remapped = remapAmplitudes(resampled)

        return remapped
    }

    private fun remapAmplitudes(amplitudes: List<Float>): List<Float> {
        val outputRange = MAX_OUTPUT - MIN_OUTPUT
        val scaleFactor = MAX_OUTPUT - AMPLITUDE_MIN_OUTPUT_THRESHOLD
        return amplitudes.map { amplitudes ->
            if (amplitudes <= AMPLITUDE_MIN_OUTPUT_THRESHOLD) {
                MIN_OUTPUT
            } else {
                val amplitudesRange = amplitudes - AMPLITUDE_MIN_OUTPUT_THRESHOLD

                MIN_OUTPUT + (amplitudesRange * outputRange) / scaleFactor
            }
        }
    }

    private fun resampledAmplitudes(
        sourceAmplitude: List<Float>,
        targetSize: Int
    ): List<Float> {
        return when {
            targetSize == sourceAmplitude.size -> sourceAmplitude
            targetSize < sourceAmplitude.size -> downsample(sourceAmplitude, targetSize)
            else -> upsample(sourceAmplitude, targetSize)
        }
    }

    private fun downsample(source: List<Float>, targetSize: Int): List<Float> {
        val ratio = source.size.toFloat() / targetSize
        return List(targetSize) { index ->
            val start = (index * ratio).toInt()
            val end = ((index + 1) * ratio).toInt().coerceAtMost(source.size)

            source.subList(start, end).max()
        }
    }

    private fun upsample(source: List<Float>, targetSize: Int): List<Float> {
        val result = mutableListOf<Float>()

        val steps = (source.size - 1).toFloat() / (targetSize - 1)
        for (i in 0 until targetSize) {
            //How far we move along the source list
            val pos = i * steps
            //0,0,1,1,2,2 Which existing element lies exactly to the left of this position?
            val index = pos.toInt()

            //How far we are past that item
            val fraction = pos - index

            val value = if (index + 1 < source.size) {
                (1 - fraction) * source[index] + fraction * source[index + 1]
            } else {
                source[index]
            }
            result.add(value)
        }

        return result.toList()
    }
}