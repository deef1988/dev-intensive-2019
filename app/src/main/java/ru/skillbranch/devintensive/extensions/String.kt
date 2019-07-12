package ru.skillbranch.devintensive.extensions

fun String.truncate(len: Int = 16): String {
    //Удалить пробелы в конце
    // проверить длину строки, если больше добавить ..., если не то нечего не делать
    var result = drop_space_end(this)
    if (result.length >= len) {
        result = result.dropLast(result.length - len)
        result = drop_space_end(result)
        result = result.padEnd(result.length + 3, '.')
    }
    return result
}


//fun String.stripHtml(html: String): String {
//    var result = ""
//    var drop_html = fale
//    for ((ind, car) in html.withIndex()) {
//        if (drop_html == true) {
//            //удаяем лишнии символы и проверяем не конец ли тега
//        } else{
//            //проверяем нет ли начала тега
//        }
//    }
//
//    return result
//}

fun drop_space_end(string: String): String {
    var result = string
    for (symbol in string) {
        if (result.takeLast(1) == " ") {
//            result = result.substring(0, result.length - 1)
            result = result.dropLast(1)
        }
    }
    return result
}