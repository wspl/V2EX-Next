package moe.impl.v2exnext.data.model

import moe.impl.v2exnext.ui.fragment.TopicListFragment

class Tab(
        name: String,
        mark: String,
        isSinglePage: Boolean = true
) : Partition(name, mark, isSinglePage) {
    override val type = PartitionType.Tab
}