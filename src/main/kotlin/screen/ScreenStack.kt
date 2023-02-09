package screen

// 화면 이동을 관리함
// 프로젝트 전역에서 관리하는 navigation이기 때문에 object로 설정!
object ScreenStack {
    private val screenStack = mutableListOf<Screen>()

    fun push(screen: Screen){
        screenStack.add(screen)
    }

    fun pop(){
        screenStack.removeLastOrNull()
    }

    fun peek(): Screen? {
        return screenStack.lastOrNull()
    }
}

sealed class Screen
// ScreenStack이 관리하는 Screen 타입을 sealed class에 추가한다
// 지금까지 우리가 추가했던 모든 화면들이 Screen의 sub class가 될 것임!
