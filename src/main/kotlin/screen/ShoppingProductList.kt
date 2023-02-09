package screen

import LINE_DIVIDER
import data.CartItems
import data.Product
import extensions.getNotEmptyInt
import extensions.getNotEmptyString

class ShoppingProductList(private val selectedCategory: String) : Screen() {
    private val products = arrayOf(
        Product("패션", "겨울 패딩"),
        Product("패션", "겨울 바지"),
        Product("전자기기", "핸드폰"),
        Product("전자기기", "블루투스 이어폰"),
        Product("전자기기", "노트북"),
        Product("반려동물용품", "간식사료"),
        Product("반려동물용품", "습식사료"),
        Product("반려동물용품", "치약"),
        Product("반려동물용품", "간식"),
    )

    // categoryLabel을 기준으로 유저가 쉽게 조회할 수 있도록 함
    private val categories: Map<String, List<Product>> = products.groupBy { product ->
        product.categoryLabel
    }

    fun showProducts() {
        ScreenStack.push(this) // 이 화면에 진입했다는걸 알려줌
        val categoryProducts = categories[selectedCategory]
        if (!categoryProducts.isNullOrEmpty()) {
            println(
                """
                $LINE_DIVIDER
                선택하신 [$selectedCategory] 카테고리 상품입니다.
            """.trimIndent()
            )
            val productSize = categoryProducts.size
//            for(index in 0 until productSize){
//                println("${index}. ${categoryProducts[index].name}")
//            }
            categoryProducts.forEachIndexed { index, product ->
                println("${index}. ${product.name}")
            }
            showCartOption(categoryProducts)
        } else {
            showEmptyProductMessage(selectedCategory)
        }
    }

    private fun showCartOption(categoryProducts: List<Product>) {
        // ===와 같은 구분자는 다른 파일에서도 공통적으로 쓰기 때문에 전역 상수로 따로 뺀다.
        println(
            """
                $LINE_DIVIDER
                장바구니에 담은 상품 번호를 선택헤주세요.
            """.trimIndent()
        )
        // index를 입력받기 때문에 int로 변환하는 과정이 있고
        // Int로 변경할 수 없는 값으로 입력했을 때는 null로 변환한다.
        // selectedIndex가 nullable이 아니게끔 맨뒤에 !!를 써준다.
        val selectedIndex = readlnOrNull().getNotEmptyInt()
        // categoryProducts에 선택한 index가 있는지 확인 후 (getOrNull)
        // 존재하면 그 product를 cartItems에 추가함
        categoryProducts.getOrNull(selectedIndex)?.let { product ->
            CartItems.addProduct(product)
            println("=> 장바구니로 이동하시려면 #을, 계속 쇼핑하시려면 *을 입력해주세요")
            val answer = readlnOrNull().getNotEmptyString() // TODO: readLine()과 readLn() 차이 알아보기
            if (answer == "#") {
                val shoppingCart = ShoppingCart()
                shoppingCart.showCartItems()
            } else if (answer == "*") {
                showProducts()
            } else {
                // TODO 그 외 값을 입력한 경우에 대한 처리
            }
        } ?: kotlin.run {
            println("$selectedIndex 번은 목록에 없는 상품 번호 입니다. 다시 입력해주세요")
            showProducts()
        }
    }

    private fun showEmptyProductMessage(selectedCategory: String) {
        println("[$selectedCategory] 카테고리 상품이 등록되기 전입니다.")
    }
}