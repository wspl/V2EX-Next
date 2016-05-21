package moe.impl.v2exnext.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import moe.impl.v2exnext.R
import moe.impl.v2exnext.data.model.Partition
import moe.impl.v2exnext.data.model.TopicList
import moe.impl.v2exnext.data.model.TopicListItem
import moe.impl.v2exnext.ui.activity.MainActivity
import moe.impl.v2exnext.ui.adapter.TopicListAdapter
import moe.impl.v2exnext.ui.presenter.TopicListPresenter
import moe.impl.v2exnext.ui.view.TopicListView

class TopicListFragment(partition: Partition) : Fragment(), TopicListView, SwipeRefreshLayout.OnRefreshListener {
    val mainActivity by lazy { activity as MainActivity }
    val presenter by lazy { TopicListPresenter(this, partition) }
    val partition by lazy { partition }

    var topicListAdapter: TopicListAdapter? = null
    val topics = mutableListOf<TopicListItem>()

    var recyclerView: RecyclerView? = null
    var swipeLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.view_topic_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_topic_list) as RecyclerView

        topicListAdapter = TopicListAdapter(topics, presenter, partition.isSinglePage)

        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = topicListAdapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        RecyclerViewDivider.with(context)
                .addTo(recyclerView!!)
                .color(Color.argb(10, 0, 0, 0))
                .size(3)
                .build()
                .attach()

        swipeLayout = view.findViewById(R.id.swipe_fresh_layout) as SwipeRefreshLayout
        swipeLayout?.setOnRefreshListener(this)
        swipeLayout?.setColorSchemeResources(R.color.colorPrimary)

        presenter.fetchTopics()

        Handler().postDelayed({
            swipeLayout?.isRefreshing = true
        }, 100)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onRefresh() {
        presenter.fetchTopics()
    }

    override fun onFetchTopics(topicList: TopicList?) {
        mainActivity.runOnUiThread {
            topics.clear()
            topicList?.topics?.forEach { topicListItem ->
                topics.add(topicListItem)
            }
            topicListAdapter?.notifyDataSetChanged()
            swipeLayout?.isRefreshing = false
        }
    }

    override fun onFetchMore(topicList: TopicList?) {
        mainActivity.runOnUiThread {
            topicList?.topics?.forEach { topicListItem ->
                topics.add(topicListItem)
            }
            topicListAdapter?.notifyDataSetChanged()
        }
    }

}
