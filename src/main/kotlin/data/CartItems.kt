package data

// object를 쓰는 이유
// CartItems는 프로젝트 전역에서 상품 데이터를 저장하는 클래스이기 때문에 항상 같은 값을 유지해야함
// 이게 바로 싱글턴 객체임!
object CartItems {
    // private를 쓰는 이유?
    // 외부에서 상품의 갯수를 수정하면 안되기 때문!
    private val mutableProducts = mutableMapOf<Product, Int>()
    val products: Map<Product, Int> = mutableProducts

    fun addProduct(product: Product) {
        mutableProducts[product]?.let {
            mutableProducts[product] = it + 1
        } ?: kotlin.run {
            mutableProducts[product] = 1
        }
        // 처음 product를 추가할때는 product의 수량 정보를 1로 할당해줘야함
    }
}