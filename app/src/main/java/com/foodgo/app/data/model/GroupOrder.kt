package com.foodgo.app.data.model

/** Local demo room. Split amounts are calculated at checkout, not stored as duplicate totals. */
data class GroupOrder(
    val id: String,
    val roomCode: String,
    val hostMemberId: String,
    val restaurantId: String,
    val members: List<GroupMember>,
    val items: List<GroupCartItem>,
    val createdAtMillis: Long,
    val splitMode: SplitMode = SplitMode.EACH_MEMBER,
    val status: GroupOrderStatus = GroupOrderStatus.OPEN,
    val orderId: String? = null,
)
