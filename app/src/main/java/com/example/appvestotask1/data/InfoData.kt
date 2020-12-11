package com.example.appvestotask1.data

class InfoData(var title: String, var time: String) : Comparable<InfoData> {

    private var seconds: Int

    init {
        seconds = calculateTimeInSeconds(time)
    }

    private fun calculateTimeInSeconds(time: String): Int {
        val dimensions: List<String> = time.split(":")
        val mins = dimensions[0].toInt()
        val secs = dimensions[1].toInt()
        return mins * 60 + secs

    }

    override fun compareTo(other: InfoData): Int {
        return seconds.compareTo(other.seconds)
    }

}