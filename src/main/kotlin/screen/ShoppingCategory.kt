package screen

import extensions.getNotEmptyString

/*
실습4
1. 장바구니에 추가한 상품 관리
2. 사용자 입력값 요청 처리 공통화
3. 프로젝트 전역에서 참조하는 상수
 */
class ShoppingCategory : Screen(){
    fun showCategories() {
        ScreenStack.push(this)
        /*
        실습2
        1. 상품 카테고리 표기
        2. 사용자 입력 받기
        3. 사용자가 기대하는 값을 입력하지 않는 경우에 대한 처리
         */
        val categories = arrayOf("패션", "전자기기", "반려동물용품")
        for (category in categories) {
            println(category)
        }
        println("=> 장바구니로 이동하시려면 #을 입력해주세요")
        val selectedCategory = readlnOrNull().getNotEmptyString()
//        while (selectedCategory.isNullOrBlank()) {
//            println("값을 입력해주세요")
//            selectedCategory = readlnOrNull()
//        }
        if (selectedCategory == "#") {
            val shoppingCart = ShoppingCart()
            shoppingCart.showCartItems()
        } else {
            if(categories.contains(selectedCategory)){
                val shoppingProductList = ShoppingProductList(selectedCategory)
                shoppingProductList.showProducts()
            } else {
                showErrorMessage(selectedCategory)
            }
        }
    }

    private fun showErrorMessage(selectedCategory: String?) {
        println("[$selectedCategory] : 존재하지 않는 카테고리 입니다. 다시 입력해주세요")
        showCategories()
    }
}