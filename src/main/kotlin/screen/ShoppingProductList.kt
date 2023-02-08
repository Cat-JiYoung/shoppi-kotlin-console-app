package screen

import data.Product

class ShoppingProductList {
    private val products = arrayOf(
        Product("패션","겨울 패딩"),
        Product("패션","겨울 바지"),
        Product("전자기기","핸드폰"),
        Product("전자기기","블루투스 이어폰"),
        Product("전자기기","노트북"),
        Product("반려동물용품","간식사료"),
        Product("반려동물용품","습식사료"),
        Product("반려동물용품","치약"),
        Product("반려동물용품","간식"),
    )
    // categoryLabel을 기준으로 유저가 쉽게 조회할 수 있도록 함
    private val categories: Map<String, List<Product>> = products.groupBy { product ->
        product.categoryLabel
    }

    fun showProducts(seletedCategory: String) {
        val categoryProducts = categories[seletedCategory]
        if(!categoryProducts.isNullOrEmpty()){
            println("""
                ***==============================***
                선택하신 [$seletedCategory] 카테고리 상품입니다.
            """.trimIndent()
            )
            val productSize = categoryProducts.size
            for(index in 0 until productSize){
                println("${index}. ${categoryProducts[index].name}")
            }
        } else {
            showEmptyProductMessage(seletedCategory)
        }
    }

    private fun showEmptyProductMessage(seletedCategory: String){
        println("[$seletedCategory] 카테고리 상품이 등록되기 전입니다.")
    }
}