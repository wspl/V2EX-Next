package moe.impl.v2exnext.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import moe.impl.v2exnext.data.model.Tab
import moe.impl.v2exnext.ui.fragment.TopicListFragment

class TagPagerAdapter(tabs: List<Tab>, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    val tabs by lazy { tabs }
    val fragments by lazy {
        val list = mutableListOf<Fragment>()
        tabs.forEach { tab -> list.add(TopicListFragment(tab)) }
        list
    }

    override fun getItem(position: Int): Fragment? = fragments[position]

    override fun getCount(): Int = tabs.size

    override fun getPageTitle(position: Int): String = tabs[position].name

}