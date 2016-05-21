package moe.impl.v2exnext.ui.view

import moe.impl.v2exnext.data.model.TopicList

interface TopicListView : PresenterView {
    fun onFetchTopics(topicList: TopicList?)
    fun onFetchMore(topicList: TopicList?)
}