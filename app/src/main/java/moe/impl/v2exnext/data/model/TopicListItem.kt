package moe.impl.v2exnext.data.model

data class TopicListItem(
        val id: Int,
        val title: String,
        val node: String,
        val author: String,
        val lastReplyTime: String,
        val lastReplyUser: String,
        val replies: Int,
        val avatarUrl: String
)