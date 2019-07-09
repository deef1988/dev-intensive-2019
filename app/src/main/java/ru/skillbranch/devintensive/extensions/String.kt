package ru.skillbranch.devintensive.extensions

fun String.truncate(string: String, len: Int = 16): String {
    //Удалить пробелы в конце
    // проверить длину строки, если больше добавить ..., если не то нечего не делать
    var result = drop_space_end(string)
    if (result.length >= len) {
        result = result.dropLast(result.length - len)
        result = drop_space_end(result)
        result = result.padEnd(result.length+3,'.')
    }
    return result
}
//fun sech_pass(string: String): String {
//    for ((i, symbol) in string.withIndex() downTo 1) {
//
//    }
//}
fun drop_space_end(string: String): String{
    var result = string
    for (symbol in string){
        if (result.takeLast(1) == " ") {
//            result = result.substring(0, result.length - 1)
            result = result.dropLast(1)
        }
    }
    return result
}