package moe.impl.v2exnext.ui.adapter

import android.graphics.Color
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import moe.impl.v2exnext.R
import moe.impl.v2exnext.data.model.TopicListItem
import moe.impl.v2exnext.ui.presenter.TopicListPresenter

class TopicListAdapter(data: MutableList<TopicListItem>, presenter: TopicListPresenter) : RecyclerView.Adapter<ViewHolder>() {

    val data by lazy { data }
    val presenter by lazy { presenter }

    enum class ItemViewTypes {
        UNDEFINED, TOPIC, BOTTOM
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        when (viewType) {
            ItemViewTypes.TOPIC.ordinal -> {
                val view = LayoutInflater
                        .from(parent?.context)
                        .inflate(R.layout.view_topic_list_item, parent, false)

                return TopicViewHolder(view)
            }
            ItemViewTypes.BOTTOM.ordinal -> {
                val view = LayoutInflater
                        .from(parent?.context)
                        .inflate(R.layout.view_topic_list_bottom, parent, false)

                return BottomViewHolder(view)
            }
        }
        return null
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ItemViewTypes.TOPIC.ordinal -> {
                holder as TopicViewHolder
                holder.title.text = data[position].title
                holder.node.text = data[position].node
                holder.author.text = data[position].author
                holder.lastReplyTime.text = data[position].lastReplyTime
                holder.lastReplyUser.text = data[position].lastReplyUser
                holder.replies.text = data[position].replies.toString()

                Glide.with(holder.view.context).load("https:" + data[position].avatarUrl).into(holder.avatar)
            }
            ItemViewTypes.BOTTOM.ordinal -> {
                holder as BottomViewHolder
                presenter.fetchMore()
            }
        }
    }

    override fun getItemCount(): Int = if (data.size > 0) data.size + 1 else 0

    override fun getItemViewType(position: Int): Int {
        if (position < itemCount - 1) {
            return ItemViewTypes.TOPIC.ordinal
        } else if (data.size > 0 && position == itemCount - 1) {
            return ItemViewTypes.BOTTOM.ordinal
        }

        return ItemViewTypes.UNDEFINED.ordinal
    }

    class TopicViewHolder(v: View) : ViewHolder(v) {
        val view = v;
        val title by lazy { v.findViewById(R.id.topic_title) as TextView }
        val node by lazy { v.findViewById(R.id.topic_node) as TextView }
        val author by lazy { v.findViewById(R.id.topic_author) as TextView }
        val lastReplyTime by lazy { v.findViewById(R.id.topic_last_reply_time) as TextView }
        val lastReplyUser by lazy { v.findViewById(R.id.topic_last_reply_user) as TextView }
        val replies by lazy { v.findViewById(R.id.topic_replies) as TextView }
        val avatar by lazy { v.findViewById(R.id.topic_avatar) as ImageView }
    }

    class BottomViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v;
    }
}