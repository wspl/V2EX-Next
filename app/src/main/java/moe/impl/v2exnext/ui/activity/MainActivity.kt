package moe.impl.v2exnext.ui.activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import moe.impl.v2exnext.R
import moe.impl.v2exnext.ui.component.BottomBarComponent

class MainActivity : AppCompatActivity() {
    val toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }
    var bottomBarComponent: BottomBarComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.hide()

        bottomBarComponent = BottomBarComponent(this, savedInstanceState)
    }

}
