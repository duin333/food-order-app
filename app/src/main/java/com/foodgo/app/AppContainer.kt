package com.foodgo.app

import com.foodgo.app.data.repository.*
import com.foodgo.app.data.sample.*

/** Shared single-process BC3 sample repositories. All state resets after process death. */
object AppContainer {
    val catalogRepository: CatalogRepository = SampleCatalogRepository()
    val cartRepository: CartRepository = SampleCartRepository(catalogRepository)
    val orderRepository: OrderRepository = SampleOrderRepository()
    val groupRepository: GroupRepository = SampleGroupRepository(catalogRepository)
    val voucherRepository: VoucherRepository = SampleVoucherRepository()
}
