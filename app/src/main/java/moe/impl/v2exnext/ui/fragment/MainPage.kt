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
import moe.impl.v2exnext.ui.activity.MainActivity
import moe.impl.v2exnext.ui.adapter.MainPagePagerAdapter
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        tabComponent = TabComponent(mainActivity, listOf("全部", "最热", "问与答"), this)

        if (viewCache == null) {
            viewCache = inflater!!.inflate(R.layout.fragment_main_page, container, false)

            viewPager = viewCache?.findViewById(R.id.main_page_pager) as ViewPager?
            viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabComponent?.tabLayout))
            viewPager?.adapter = MainPagePagerAdapter(fragmentManager)
        }

        return viewCache
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {}
    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab) {
        viewPager?.currentItem = tab.position
    }
}
