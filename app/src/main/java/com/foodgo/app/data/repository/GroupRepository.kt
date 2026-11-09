package com.foodgo.app.data.repository

import com.foodgo.app.data.model.*

/** BC3 synchronous contract. Invalid mutations throw IllegalArgumentException; no UI dependencies. */
interface GroupRepository {
    fun createGroup(restaurantId: String, host: GroupMember): GroupOrder
    fun getGroup(id: String): GroupOrder?
    fun joinGroup(roomCode: String, member: GroupMember): GroupOrder
    fun addItem(groupId: String, memberId: String, foodId: String, quantity: Int, note: String = ""): GroupOrder
    fun removeItem(groupId: String, memberId: String, itemId: String): GroupOrder
    fun setSplitMode(groupId: String, hostMemberId: String, mode: SplitMode): GroupOrder
    /** Host locks the room before checkout. */
    fun lockGroup(groupId: String, hostMemberId: String): GroupOrder
    fun markOrdered(groupId: String, hostMemberId: String, orderId: String): GroupOrder
}
