package ru.skillbranch.devintensive.utils

import java.lang.Character.toUpperCase

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if ((firstName == null) or (firstName == "")) {
            firstName = null
        }
        if ((lastName == null) or (lastName == "")) {
            lastName = null
        }
//        val firstName = parts?.getOrNull(0).orEmpty().trim().ifEmpty { null }
//        val lastName = parts?.getOrNull(1).orEmpty().trim().ifEmpty { null }
//        return Pair(firstName, lastName)
        return firstName to lastName
    }

//    fun transliteration(payload: String, divider: String = " "): String {
//        var result = ""
//        var first = true
//        val u1 = payload.split(" ")
//        for (y in u1) {
//            for ((ind, char) in y.withIndex()) {
//                if (ind == 0) {
//                    if (trans(char.toString()).length > 1) {
//                        result += trans(char.toString())[0].toUpperCase()
//                        result += trans(char.toString())[1]
//                    } else result += trans(char.toString()).toUpperCase()
//                } else {
//                    result += trans(char.toString())
//                }
//            }
//            if (first == true) {
//                result += divider
//                first = false
//            }
//        }
//        return result
//    }
    fun transliteration(payload: String, divider: String = " "): String {
        val map = fillTranslitMap()
        val builder = StringBuilder()

        for (char in payload.trim())
            builder.append(getTranslChar(char, map))

        return builder.toString().replace(" ", divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
//        val name = firstName.orEmpty().trim().getOrNull(0)?.toUpperCase()
//        val surname = lastName.orEmpty().trim().getOrNull(0)?.toUpperCase()
//        val firstInit = name?.toString() ?: ""
//        val secondInit = surname?.toString() ?: ""
//        return "$firstInit$secondInit".ifEmpty { null }
        val r1 = if (firstName != null) {
            if (firstName == "") {
                ""
            } else {
                firstName[0].toString().toUpperCase()
            }
        } else null
        val r2 = if (lastName != null) {
            if (lastName == "") {
                ""
            } else {
                lastName[0].toString().toUpperCase()
            }
        } else null
        if ((r1 == null) and (r2 == null)) return null
        else if ((r1 == null) and (r2 != null)) return r2
        else if ((r1 != null) and (r2 == null)) return r1
        else if (((r1 == "") and (r2 == "")) or ((r1 == "") and (r2 == " ")) or ((r1 == " ") and (r2 == "")) or ((r1 == " ") and (r2 == " "))) return null
        else return r1 + r2
    }

//    fun trans(string: String): String {
//        val str = when (string) {
//            "а" -> "a"
//            "б" -> "b"
//            "в" -> "v"
//            "г" -> "g"
//            "д" -> "d"
//            "е" -> "e"
//            "ё" -> "e"
//            "ж" -> "zh"
//            "з" -> "z"
//            "и" -> "i"
//            "й" -> "i"
//            "к" -> "k"
//            "л" -> "l"
//            "м" -> "m"
//            "н" -> "n"
//            "о" -> "o"
//            "п" -> "p"
//            "р" -> "r"
//            "с" -> "s"
//            "т" -> "t"
//            "у" -> "u"
//            "ф" -> "f"
//            "х" -> "h"
//            "ц" -> "c"
//            "ч" -> "ch"
//            "ш" -> "sh"
//            "щ" -> "sh'"
//            "ъ" -> ""
//            "ы" -> "i"
//            "ь" -> ""
//            "э" -> "e"
//            "ю" -> "yu"
//            "я" -> "ya"
//            "a" -> "a"
//            "b" -> "b"
//            "c" -> "c"
//            "d" -> "d"
//            "e" -> "e"
//            "f" -> "f"
//            "g" -> "g"
//            "h" -> "h"
//            "i" -> "i"
//            "j" -> "j"
//            "k" -> "k"
//            "l" -> "l"
//            "m" -> "m"
//            "n" -> "n"
//            "o" -> "o"
//            "p" -> "p"
//            "q" -> "q"
//            "r" -> "r"
//            "s" -> "s"
//            "t" -> "t"
//            "u" -> "u"
//            "v" -> "v"
//            "w" -> "w"
//            "x" -> "x"
//            "y" -> "y"
//            "z" -> "z"
//            " " -> " "
//            else -> {
//                trans(string.toLowerCase())
//            }
//        }
//        return str
//    }

    private fun getTranslChar(char: Char, map: HashMap<Char, String>): String {
        val transl = map[char.toLowerCase()]
        return if (transl != null)
            if (char.isUpperCase() && transl.isNotEmpty())
                transl[0].toUpperCase() + transl.substring(1)
            else transl
        else char.toString()
    }

    private fun fillTranslitMap(): HashMap<Char, String> {
        val map = hashMapOf<Char, String>()
        map['а'] = "a"
        map['б'] = "b"
        map['в'] = "v"
        map['г'] = "g"
        map['д'] = "d"
        map['е'] = "e"
        map['ё'] = "e"
        map['ж'] = "zh"
        map['з'] = "z"
        map['и'] = "i"
        map['й'] = "i"
        map['к'] = "k"
        map['л'] = "l"
        map['м'] = "m"
        map['н'] = "n"
        map['о'] = "o"
        map['п'] = "p"
        map['р'] = "r"
        map['с'] = "s"
        map['т'] = "t"
        map['у'] = "u"
        map['ф'] = "f"
        map['х'] = "h"
        map['ц'] = "c"
        map['ч'] = "ch"
        map['ш'] = "sh'"
        map['щ'] = "sh"
        map['ъ'] = ""
        map['ы'] = "i"
        map['ь'] = ""
        map['э'] = "e"
        map['ю'] = "yu"
        map['я'] = "ya"

        return map
    }
}