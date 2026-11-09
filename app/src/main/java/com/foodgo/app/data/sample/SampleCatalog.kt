package com.foodgo.app.data.sample

import com.foodgo.app.data.model.FoodItem
import com.foodgo.app.data.model.Restaurant

/**
 * SAMPLE DATA ONLY: fictional addresses/ratings/fees and declared ingredient/allergen metadata.
 * Exactly 17 foods per the customer's confirmed list. Images are placeholder keys, not resources yet.
 * Empty allergenCodes does NOT certify allergy safety; confirm ingredients with the restaurant.
 */
object SampleCatalog {
    val restaurants: List<Restaurant> = listOf(
        Restaurant("quan_an_ngon", "Quán Ăn Ngon", "Cơm và món Việt", "12 Nguyễn Trãi, TP.HCM", "restaurant_quan_an_ngon", 4.6, 25, 15000L),
        Restaurant("pizza_fresh", "Pizza Fresh", "Pizza và mì Ý", "35 Lê Lợi, TP.HCM", "restaurant_pizza_fresh", 4.5, 35, 20000L),
        Restaurant("tra_chill", "Trà & Chill", "Trà và đồ uống", "20 Võ Văn Tần, TP.HCM", "restaurant_tra_chill", 4.7, 20, 12000L),
        Restaurant("xanh_veggie", "Xanh Veggie", "Món chay rau củ", "48 Nguyễn Đình Chiểu, TP.HCM", "restaurant_xanh_veggie", 4.8, 30, 15000L),
        Restaurant("an_vat_pho", "Ăn Vặt Phố", "Món ăn nhẹ", "60 Trần Hưng Đạo, TP.HCM", "restaurant_an_vat_pho", 4.4, 25, 10000L),
    )

    val foods: List<FoodItem> = listOf(
        FoodItem("food_com_tam", "quan_an_ngon", "Cơm tấm sườn bì", "Món mẫu: Cơm tấm sườn bì", 45000L,
            "food_com_tam_01", "rice", listOf("Cơm", "Sườn heo", "Bì heo", "Trứng", "Nước mắm"), listOf("egg")),
        FoodItem("food_bun_bo", "quan_an_ngon", "Bún bò Huế", "Món mẫu: Bún bò Huế", 50000L,
            "food_bun_bo_01", "noodle", listOf("Bún", "Thịt bò", "Sả", "Mắm ruốc"), listOf("shellfish")),
        FoodItem("food_pho_bo", "quan_an_ngon", "Phở bò tái", "Món mẫu: Phở bò tái", 55000L,
            "food_pho_bo_01", "noodle", listOf("Bánh phở", "Thịt bò", "Hành", "Nước dùng bò"), listOf()),
        FoodItem("food_cha_gio", "quan_an_ngon", "Chả giò", "Món mẫu: Chả giò", 30000L,
            "food_cha_gio_01", "snack", listOf("Bánh tráng lúa mì", "Thịt heo", "Miến", "Trứng"), listOf("egg", "gluten")),
        FoodItem("food_pizza_hai_san", "pizza_fresh", "Pizza hải sản", "Món mẫu: Pizza hải sản", 129000L,
            "food_pizza_hai_san_01", "pizza", listOf("Bột mì", "Tôm", "Mực", "Phô mai", "Sốt cà chua"), listOf("shellfish", "milk", "gluten")),
        FoodItem("food_pizza_margherita", "pizza_fresh", "Pizza Margherita", "Món mẫu: Pizza Margherita", 99000L,
            "food_pizza_margherita_01", "pizza", listOf("Bột mì", "Phô mai", "Cà chua", "Húng quế"), listOf("milk", "gluten")),
        FoodItem("food_mi_y", "pizza_fresh", "Mì Ý bò bằm", "Món mẫu: Mì Ý bò bằm", 69000L,
            "food_mi_y_01", "noodle", listOf("Mì lúa mì", "Trứng", "Thịt bò", "Cà chua"), listOf("gluten", "egg")),
        FoodItem("food_tra_dao", "tra_chill", "Trà đào cam sả", "Món mẫu: Trà đào cam sả", 35000L,
            "food_tra_dao_01", "drink", listOf("Trà", "Đào", "Cam", "Sả", "Đường"), listOf()),
        FoodItem("food_tra_sua", "tra_chill", "Trà sữa trân châu", "Món mẫu: Trà sữa trân châu", 40000L,
            "food_tra_sua_01", "drink", listOf("Trà", "Sữa", "Trân châu bột năng", "Đường"), listOf("milk")),
        FoodItem("food_matcha", "tra_chill", "Matcha latte", "Món mẫu: Matcha latte", 45000L,
            "food_matcha_01", "drink", listOf("Bột matcha", "Sữa", "Đường"), listOf("milk")),
        FoodItem("food_sinh_to_bo", "tra_chill", "Sinh tố bơ", "Món mẫu: Sinh tố bơ", 42000L,
            "food_sinh_to_bo_01", "drink", listOf("Bơ", "Sữa đặc", "Sữa tươi", "Đá"), listOf("milk")),
        FoodItem("food_com_chay", "xanh_veggie", "Cơm chay thập cẩm", "Món mẫu: Cơm chay thập cẩm", 49000L,
            "food_com_chay_01", "rice", listOf("Cơm", "Đậu phụ", "Rau củ", "Nước tương"), listOf("soy")),
        FoodItem("food_goi_cuon_rau", "xanh_veggie", "Gỏi cuốn rau củ", "Món mẫu: Gỏi cuốn rau củ", 39000L,
            "food_goi_cuon_rau_01", "vegetarian", listOf("Bánh tráng gạo", "Rau củ", "Bún", "Đậu phộng"), listOf("peanut")),
        FoodItem("food_mi_nam", "xanh_veggie", "Mì xào nấm", "Món mẫu: Mì xào nấm", 52000L,
            "food_mi_nam_01", "noodle", listOf("Mì lúa mì", "Nấm", "Rau cải", "Nước tương"), listOf("gluten", "soy")),
        FoodItem("food_banh_trang", "an_vat_pho", "Bánh tráng trộn", "Món mẫu: Bánh tráng trộn", 25000L,
            "food_banh_trang_01", "snack", listOf("Bánh tráng gạo", "Xoài", "Rau răm", "Đậu phộng"), listOf("peanut")),
        FoodItem("food_goi_cuon_tom", "an_vat_pho", "Gỏi cuốn tôm", "Món mẫu: Gỏi cuốn tôm", 35000L,
            "food_goi_cuon_tom_01", "snack", listOf("Bánh tráng gạo", "Tôm", "Bún", "Rau", "Đậu phộng"), listOf("shellfish", "peanut")),
        FoodItem("food_khoai_tay", "an_vat_pho", "Khoai tây chiên", "Món mẫu: Khoai tây chiên", 30000L,
            "food_khoai_tay_01", "snack", listOf("Khoai tây", "Dầu thực vật", "Muối"), listOf()),
    )
}
