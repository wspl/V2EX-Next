package moe.impl.v2exnext.data.parser

import moe.impl.v2exnext.data.model.TopicList
import moe.impl.v2exnext.data.model.TopicListItem
import org.jsoup.Jsoup

class TopicListParser(body: String) {

    val document by lazy { Jsoup.parse(body) }
    var topicList: TopicList? = null

    init {
        val cellItems = document.select("#Main > .box > .cell.item")
        val mutableTopicList = mutableListOf<TopicListItem>()

        cellItems.forEach { cellItem ->
            val smallFades = cellItem.select(".small.fade").first().text().split("  •  ")
            if (smallFades.size == 4) {
                val topic = TopicListItem(
                        id = cellItem.select(".item_title a").first().attr("href").split("/")[2].split("#")[0].toInt(),
                        title = cellItem.select(".item_title").first().text(),
                        node = smallFades[0],
                        author = smallFades[1],
                        lastReplyTime = smallFades[2],
                        lastReplyUser = smallFades[3].replace("最后回复来自 ", ""),
                        replies = cellItem.select(".count_livid").first().text().toInt(),
                        avatarUrl = cellItem.select(".avatar").first().attr("src")
                )
                mutableTopicList.add(topic)
            }
        }

        topicList = TopicList(mutableTopicList as List<TopicListItem>)
    }

}