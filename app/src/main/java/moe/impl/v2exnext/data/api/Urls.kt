package moe.impl.v2exnext.data.api

object Urls {
    val baseDomain = "https://v2ex.com"

    val index: String
        get() = baseDomain

    fun recent(page: Int): String = "https://v2ex.com/recent?p=$page"
}