package com.example.appvestotask1.factory

import com.example.appvestotask1.data.InfoData
import java.lang.StringBuilder


class InfoDataFactory {
    companion object{
        private val builder:StringBuilder = StringBuilder()
        fun createInfoData(text: String): InfoData {
            builder.clear()
            builder.append(text)
            val title = builder.substring(0, builder.indexOf("Preview"))
            val time = builder.substring(builder.indexOf(":")-2)
            return InfoData(title,time)
        }
    }
}