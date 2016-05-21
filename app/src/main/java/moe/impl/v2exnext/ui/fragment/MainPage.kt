package moe.impl.v2exnext.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

import moe.impl.v2exnext.R
import moe.impl.v2exnext.data.model.Tab
import moe.impl.v2exnext.ui.activity.MainActivity
import moe.impl.v2exnext.ui.adapter.TagPagerAdapter
import moe.impl.v2exnext.ui.component.TabComponent

class MainPage : Fragment(), TabLayout.OnTabSelectedListener {

    val mainActivity by lazy { activity as MainActivity }
    var viewCache: View? = null
    var viewPager: ViewPager? = null
    var tabComponent: TabComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.supportActionBar?.show()
    }

    val tabs = listOf(
            Tab("全部", "all"),
            Tab("最近", "recent", false),
            Tab("问与答", "qna"),
            Tab("技术", "tech"),
            Tab("创意", "creative"),
            Tab("好玩", "play"),
            Tab("酷工作", "jobs"),
            Tab("交易", "deals"),
            Tab("城市", "city")
    )

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        tabComponent = TabComponent(mainActivity, tabs, this)

        if (viewCache == null) {
            viewCache = inflater!!.inflate(R.layout.fragment_main_page, container, false)

            viewPager = viewCache?.findViewById(R.id.main_page_pager) as ViewPager?
            viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabComponent?.tabLayout))
            viewPager?.adapter = TagPagerAdapter(tabs ,fragmentManager)
        }

        return viewCache
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {}
    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab) {
        viewPager?.currentItem = tab.position
    }
}
