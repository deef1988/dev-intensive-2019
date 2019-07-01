package ru.skillbranch.devintensive.utils

object Utils {
    fun  parseFullname(fullName:String?):Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if ((firstName == null) or (firstName == "")) {
            firstName = "null"
        }
        if ((lastName == null) or (lastName == "")) {
            lastName = "null"
        }
//        return Pair(firstName, lastName)
        return firstName to lastName
    }
}