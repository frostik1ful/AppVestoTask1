package com.example.appvestotask1.util

import com.example.appvestotask1.data.InfoData
import com.example.appvestotask1.factory.InfoDataFactory
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class PageParser {

    fun parse(link: String): ArrayList<InfoData> {
        val dataList = ArrayList<InfoData>()
        try {
            val doc: Document = Jsoup.connect(link).get()
            val elements: Elements = doc.select("div[data-purpose=\"course-curriculum\"]")
            val container = elements[0]
            val sections: Elements = container.getElementsByClass("section--panel--1tqxC")

            sections.forEach { section ->
                val rows = section.getElementsByClass("udlite-block-list-item-content")
                rows.forEach { element ->
                    if (element.getElementsByClass("section--hidden-on-mobile--171Q9 section--preview-text--38nT0").size > 0) {
                        val text: String = element.text()
                        dataList.add(InfoDataFactory.createInfoData(text))
                    }
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return dataList
    }

}