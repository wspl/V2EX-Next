package moe.impl.v2exnext.data.api

import moe.impl.v2exnext.data.model.TopicList
import moe.impl.v2exnext.data.model.TopicListItem
import moe.impl.v2exnext.data.parser.TopicListParser
import okhttp3.*
import java.io.IOException

class TopicListFetcher(page: Int, callback: (topics: TopicList?) -> Unit) {
//        callback(TopicList(topics = listOf(TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"),
//                TopicListItem(123, "test", "asdfsad", "asdfasd", "asdfadsf", "asdfasd", 123, "213123"))))

    init {
        val client = OkHttpClient()
        val request = Request.Builder().url(Urls.recent(page)).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                throw e
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) throw IOException("Unexpected code " + response)
                callback(TopicListParser(response.body().string()).topicList)
            }
        })
    }
}