package moe.impl.v2exnext.data.model

abstract class Partition(
    val name: String,
    val mark: String,
    val isSinglePage: Boolean = false
) {
    abstract val type: PartitionType

    enum class PartitionType {
        Node, Tab
    }
}