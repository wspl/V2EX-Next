package moe.impl.v2exnext.ui.presenter

import moe.impl.v2exnext.data.api.TopicListFetcher
import moe.impl.v2exnext.ui.view.TopicListView

class TopicListPresenter(override val view: TopicListView) : BasePresenter<TopicListView> {

    var isLoading = false
    var currentPage = 1

    fun fetchTopics() {
        if (!isLoading) {
            isLoading = true
            TopicListFetcher(currentPage) { topicList ->
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
            TopicListFetcher(currentPage) { topicList ->
                isLoading = false
                view.onFetchMore(topicList)
            }
        }
    }

}