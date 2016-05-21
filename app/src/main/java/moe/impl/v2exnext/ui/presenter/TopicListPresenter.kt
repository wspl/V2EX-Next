package moe.impl.v2exnext.ui.presenter

import moe.impl.v2exnext.data.api.TopicListFetcher
import moe.impl.v2exnext.data.model.Partition
import moe.impl.v2exnext.ui.view.TopicListView

class TopicListPresenter(override val view: TopicListView, partition: Partition) : BasePresenter<TopicListView> {

    val partition by lazy { partition }

    var isLoading = false
    var currentPage = 1

    fun fetchTopics() {
        if (!isLoading) {
            isLoading = true
            TopicListFetcher(partition, currentPage) { topicList ->
                isLoading = false
                currentPage == 1
                view.onFetchTopics(topicList)
            }
        }
    }

    fun fetchMore() {
        if (!isLoading) {
            isLoading = true
            currentPage += 1
            TopicListFetcher(partition, currentPage) { topicList ->
                isLoading = false
                view.onFetchMore(topicList)
            }
        }
    }

}