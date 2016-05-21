package moe.impl.v2exnext.data.model

import moe.impl.v2exnext.ui.fragment.TopicListFragment

class Node(
        name: String,
        mark: String
) : Partition(name, mark) {
    override val type = PartitionType.Node
}