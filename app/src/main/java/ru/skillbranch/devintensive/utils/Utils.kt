package ru.skillbranch.devintensive.utils

import java.lang.Character.toUpperCase

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
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

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        val u1 = payload.split(divider)
        for (y in u1) {
            for ((ind, char) in y.withIndex()) {
                if (ind == 0) {
                    if (trans(char.toString()).length > 1) {
                        result += trans(char.toString())[0].toUpperCase()
                        result += trans(char.toString())[1]
                    } else result += trans(char.toString()).toUpperCase()
                } else {
                    result += trans(char.toString())
                }
            }
            result += " "
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val r1 = if (firstName != null) {
                            if (trans(firstName[0].toString()).length > 1) {
                                "${trans(firstName[0].toString())[0].toUpperCase()}${trans(firstName[0].toString())[1]}"
                            } else {
                                trans(firstName[0].toString()).toUpperCase()
                            }
                        }
                        else ""
        val r2 = if (lastName != null) {
                            if (trans(lastName[0].toString()).length > 1) {
                                "${trans(lastName[0].toString())[0].toUpperCase()}${trans(lastName[0].toString())[1]}"
                            } else {
                                trans(lastName[0].toString()).toUpperCase()
                            }
                        }
                        else ""
        return r1 + r2
    }

    fun trans(string: String): String {
        val str = when (string) {
            "а" -> "a"
            "б" -> "b"
            "в" -> "v"
            "г" -> "g"
            "д" -> "d"
            "е" -> "e"
            "ё" -> "e"
            "ж" -> "zh"
            "з" -> "z"
            "и" -> "i"
            "й" -> "i"
            "к" -> "k"
            "л" -> "l"
            "м" -> "m"
            "н" -> "n"
            "о" -> "o"
            "п" -> "p"
            "р" -> "r"
            "с" -> "s"
            "т" -> "t"
            "у" -> "u"
            "ф" -> "f"
            "х" -> "h"
            "ц" -> "c"
            "ч" -> "ch"
            "ш" -> "sh"
            "щ" -> "sh'"
            "ъ" -> ""
            "ы" -> "i"
            "ь" -> ""
            "э" -> "e"
            "ю" -> "yu"
            "я" -> "ya"
            " " -> " "
            else -> {
                trans(string.toLowerCase())
            }
        }
        return str
    }
}