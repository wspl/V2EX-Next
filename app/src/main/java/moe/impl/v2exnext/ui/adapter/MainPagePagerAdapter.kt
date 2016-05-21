package moe.impl.v2exnext.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import moe.impl.v2exnext.ui.fragment.TopicListFragment

class MainPagePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? = TopicListFragment()

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): String = "标题"

}