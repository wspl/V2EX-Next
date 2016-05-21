package moe.impl.v2exnext.data.api

import moe.impl.v2exnext.data.model.Partition
import moe.impl.v2exnext.data.model.TopicList
import moe.impl.v2exnext.data.model.TopicListItem
import moe.impl.v2exnext.data.parser.TopicListParser
import okhttp3.*
import java.io.IOException

class TopicListFetcher(partition: Partition, page: Int, callback: (topics: TopicList?) -> Unit) {
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

        var url = when (partition.type) {
            Partition.PartitionType.Tab ->
                if (partition.mark == "recent") Urls.recent(page)
                else Urls.tab(partition.mark)
            Partition.PartitionType.Node -> Urls.node(partition.mark, page)
        }

        val request = Request.Builder().url(url).build()

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