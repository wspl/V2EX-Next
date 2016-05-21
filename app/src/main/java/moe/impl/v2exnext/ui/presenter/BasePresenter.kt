package moe.impl.v2exnext.ui.presenter

import moe.impl.v2exnext.ui.view.PresenterView

interface BasePresenter<T: PresenterView> {
    val view: T
}