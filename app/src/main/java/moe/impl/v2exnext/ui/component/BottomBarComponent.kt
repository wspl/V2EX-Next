package moe.impl.v2exnext.ui.component

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.animation.AccelerateInterpolator
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.BottomBarTab
import com.roughike.bottombar.OnTabClickListener
import moe.impl.v2exnext.R
import moe.impl.v2exnext.ui.activity.MainActivity
import moe.impl.v2exnext.ui.fragment.MainPage
import moe.impl.v2exnext.ui.fragment.NodePage
import moe.impl.v2exnext.utils.MaterialDrawableBuilderFix
import net.steamcrafted.materialiconlib.MaterialDrawableBuilder
import net.steamcrafted.materialiconlib.MaterialDrawableBuilder.IconValue

class BottomBarComponent(activity: MainActivity, savedInstanceState: Bundle?) : OnTabClickListener {

    enum class Pages {
        MAIN_PAGE,
        NODE_PAGE
    }

    val activity by lazy { activity }
    //val bottomBarView by lazy { activity.findViewById(R.id.bottom_bar) }

    val bottomBar by lazy { BottomBar.attachShy(activity.findViewById(R.id.coordinator_layout) as CoordinatorLayout?, null, savedInstanceState) }

    val pagesCache = mapOf(
            Pages.MAIN_PAGE to MainPage(),
            Pages.NODE_PAGE to NodePage()
    )

    init {
        //bottomBar.toggleChild
        bottomBar.setItems(
                BottomBarTab(makeIcon(IconValue.EYE), "浏览"),
                BottomBarTab(makeIcon(IconValue.FLAG_VARIANT), "节点"),
                BottomBarTab(makeIcon(IconValue.FIRE), "热门"),
                BottomBarTab(makeIcon(IconValue.NEWSPAPER), "周报"),
                BottomBarTab(makeIcon(IconValue.ACCOUNT), "个人")
        );
        bottomBar.setOnTabClickListener(this)

        bottomBar.mapColorForTab(0, "#1abc9c")
    }

    fun makeIcon(icon: IconValue): Drawable = MaterialDrawableBuilderFix.build(MaterialDrawableBuilder
            .with(activity.applicationContext)
            .setIcon(icon)
    )

    fun switchPage(page: Pages) {
        TabComponent.clear(activity)
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, pagesCache[page]).commit()
    }

    override fun onTabReSelected(position: Int) {
            //throw UnsupportedOperationException()
    }

    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> switchPage(Pages.MAIN_PAGE)
            1 -> switchPage(Pages.NODE_PAGE)
        }
    }

}