package com.foodgo.app.data.sample

import com.foodgo.app.data.model.*
import com.foodgo.app.data.repository.CatalogRepository
import com.foodgo.app.data.repository.GroupRepository
import java.util.UUID

/** Sample rooms on ONE device only. Room codes do not connect separate devices. */
class SampleGroupRepository(private val catalog: CatalogRepository) : GroupRepository {
    private val groups = linkedMapOf<String, GroupOrder>()

    private fun snapshot(group: GroupOrder) = group.copy(
        members = group.members.toList(),
        items = group.items.map { it.copy(cartItem = it.cartItem.copy()) },
    )
    private fun group(id: String) = requireNotNull(groups[id]) { "Group not found" }
    private fun open(id: String): GroupOrder = group(id).also {
        require(it.status == GroupOrderStatus.OPEN) { "Group is not open" }
    }
    private fun host(group: GroupOrder, memberId: String) {
        require(group.hostMemberId == memberId) { "Only the host can perform this action" }
    }
    private fun save(group: GroupOrder): GroupOrder {
        groups[group.id] = snapshot(group)
        return snapshot(group)
    }

    override fun createGroup(restaurantId: String, host: GroupMember): GroupOrder {
        requireNotNull(catalog.getRestaurant(restaurantId)) { "Restaurant not found" }
        require(host.id.isNotBlank() && host.displayName.isNotBlank())
        var code: String
        do { code = UUID.randomUUID().toString().take(6).uppercase(java.util.Locale.ROOT) }
        while (groups.values.any { it.roomCode == code })
        return save(GroupOrder(UUID.randomUUID().toString(), code, host.id, restaurantId,
            listOf(host), emptyList(), System.currentTimeMillis()))
    }

    override fun getGroup(id: String) = groups[id]?.let(::snapshot)

    override fun joinGroup(roomCode: String, member: GroupMember): GroupOrder {
        require(member.id.isNotBlank() && member.displayName.isNotBlank())
        val found = requireNotNull(groups.values.find {
            it.roomCode.equals(roomCode.trim(), ignoreCase = true)
        }) { "Room code not found" }
        val room = open(found.id)
        if (room.members.any { it.id == member.id }) return snapshot(room)
        return save(room.copy(members = room.members + member))
    }

    override fun addItem(groupId: String, memberId: String, foodId: String, quantity: Int, note: String): GroupOrder {
        val room = open(groupId)
        require(room.members.any { it.id == memberId }) { "Member not found" }
        require(quantity > 0)
        val food = requireNotNull(catalog.getFood(foodId)) { "Food not found" }
        require(food.restaurantId == room.restaurantId && food.isAvailable) { "Food must be available from this restaurant" }
        val id = UUID.randomUUID().toString()
        val item = GroupCartItem(id, memberId, CartItem(id, food.id, food.restaurantId,
            food.name, food.priceVnd, quantity, note, food.imageKey))
        return save(room.copy(items = room.items + item))
    }

    override fun removeItem(groupId: String, memberId: String, itemId: String): GroupOrder {
        val room = open(groupId)
        val item = requireNotNull(room.items.find { it.id == itemId }) { "Group item not found" }
        require(item.memberId == memberId) { "Only the item owner can remove it" }
        return save(room.copy(items = room.items.filterNot { it.id == itemId }))
    }

    override fun setSplitMode(groupId: String, hostMemberId: String, mode: SplitMode): GroupOrder {
        val room = open(groupId)
        host(room, hostMemberId)
        return save(room.copy(splitMode = mode))
    }

    override fun lockGroup(groupId: String, hostMemberId: String): GroupOrder {
        val room = open(groupId)
        host(room, hostMemberId)
        require(room.items.isNotEmpty()) { "Group cart is empty" }
        return save(room.copy(status = GroupOrderStatus.LOCKED))
    }

    override fun markOrdered(groupId: String, hostMemberId: String, orderId: String): GroupOrder {
        val room = group(groupId)
        host(room, hostMemberId)
        require(orderId.isNotBlank())
        if (room.status == GroupOrderStatus.ORDERED && room.orderId == orderId) return snapshot(room)
        require(room.status == GroupOrderStatus.LOCKED) { "Lock the group before placing an order" }
        return save(room.copy(status = GroupOrderStatus.ORDERED, orderId = orderId))
    }
}
