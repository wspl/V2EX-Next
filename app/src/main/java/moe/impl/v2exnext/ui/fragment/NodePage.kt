package moe.impl.v2exnext.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import moe.impl.v2exnext.R
import moe.impl.v2exnext.ui.component.TabComponent

class NodePage : Fragment() {

    val mainActivity by lazy { activity as AppCompatActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_node_page, container, false)
        return view
    }

}
