package moe.impl.v2exnext.data.api

object Urls {
    val baseDomain = "https://v2ex.com"

    val index: String
        get() = baseDomain

    fun recent(page: Int): String = "https://v2ex.com/recent?p=$page"
    fun tab(mark: String): String = "https://v2ex.com/?tab=$mark"
    fun node(mark: String, page: Int): String = "https://v2ex.com/go/$mark?p=$page"
}