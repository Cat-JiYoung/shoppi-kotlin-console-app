package extensions

// nullable 타입의 확장함수 구현
// *,# 등의 입력값을 요청했을 때 사용자가 빈칸 혹은 공백을 입력하지 않도록 확인하는 함수
fun String?.getNotEmptyString(): String {
    var input = this
    while (input.isNullOrBlank()){
        println("값을 입력해주세요")
        input = readlnOrNull()
    }
    return input.trim() // 공백을 포함한 것을 제거함
}

// 사용자에게 상품번호를 입력받을때 그값이 Int 타입으로 변환할 수 있는 값인지 확인하는 용도로 사용함
fun String?.getNotEmptyInt(): Int {
    var input = this?.trim()
    while (input.isNullOrEmpty() || input.toIntOrNull() == null) {
        println("값을 입력해주세요")
        input = readLine()
    }
    return input.toInt()
}