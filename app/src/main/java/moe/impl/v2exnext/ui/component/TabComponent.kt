package moe.impl.v2exnext.ui.component

import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import moe.impl.v2exnext.R
import moe.impl.v2exnext.data.model.Partition
import moe.impl.v2exnext.ui.activity.MainActivity

class TabComponent(activity: MainActivity, items: List<Partition>, onTabSelectedListener: TabLayout.OnTabSelectedListener) {

    val tabLayout by lazy { activity.findViewById(R.id.tab_layout) as TabLayout }

    init {
        tabLayout.visibility = View.VISIBLE

        items.forEach { item ->
            tabLayout.addTab(tabLayout.newTab().setText(item.name))
        }

        tabLayout.setOnTabSelectedListener(onTabSelectedListener)
    }

    companion object {

        fun clear(activity: MainActivity) {
            val tabLayout = activity.findViewById(R.id.tab_layout) as TabLayout
            tabLayout.visibility = View.GONE
            tabLayout.removeAllTabs()
        }

    }
}