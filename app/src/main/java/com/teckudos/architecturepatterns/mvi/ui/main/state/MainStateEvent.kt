package com.teckudos.architecturepatterns.mvi.ui.main.state

sealed class MainStateEvent {
    class GetBlogPostsEvent : MainStateEvent()
    class GetUserEvent(val userId: String) : MainStateEvent()
    class None() : MainStateEvent()
}

/*
* Difference between enum and sealed classes
* In Kotlin, Sealed Classes can be termed as Enum classes on steroids.
* Sealed classes allow us to create instances with different types, unlike Enums which restrict
* us to use the same type for all enum constants.
*
* enum class Months(string: String){
*       January("Jan"), February(2),
* }
* Enum classes allow only a single type for all constants.
*
* sealed class Months {
*       class January(var shortHand: String) : Months()
*       class February(var number: Int) : Months()
*       class March(var shortHand: String, var number: Int) : Months()
* }
* Sealed Classes allowing multiple instances
* */