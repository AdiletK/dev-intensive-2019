package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.DAY
import ru.skillbranch.devintensive.extensions.HOUR
import ru.skillbranch.devintensive.extensions.MINUTE
import ru.skillbranch.devintensive.extensions.SECOND
import kotlin.math.roundToInt

object Utils {

//
//    fun transliteration(payload: String,divider: String = ""): String {
//        return if (divider.isNotEmpty()){
//            convertRU(payload)
//        } else{
//            val parts:List<String>? = payload.trim().split(" ")
//            val firstName:String
//            var lastName = ""
//
//            firstName = convertRU(parts!![0])
//            if (parts.size>1){
//                lastName = convertRU(parts[1])
//                if (isNullOrEmpty(lastName)){
//                    return "$firstName$divider"
//                }
//            }
//
//            "$firstName$divider$lastName"
//        }
//    }

//    fun toInitials(firstName: String?, lastName: String?): String? {
//        val strokeFirst: String?
//        val strokeTwo: String?
//
//        when {
//            firstName == null -> strokeFirst = null
//            firstName.isBlank() -> strokeFirst = ""
//            else -> strokeFirst = firstName.trim(' ')[0].toUpperCase().toString()
//        }
//
//        when {
//            lastName == null -> strokeTwo = null
//            lastName.isBlank() -> strokeTwo = ""
//            else -> strokeTwo = lastName.trim(' ')[0].toUpperCase().toString()
//        }
//
//        return if ((strokeFirst == null && strokeTwo == null) || (strokeFirst == "" && strokeTwo == "")) {
//            null
//        } else {
//            "${if (strokeFirst != "") strokeFirst else ""}${if (strokeTwo != null) strokeTwo else ""}"
//        }
//    }

    fun parseFullName(fullName:String?):Pair<String?,String?>{
        if (isNullOrEmpty(fullName)){
            return Pair(null,null)
        }
        val parts:List<String>? = fullName?.trim()?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return Pair(firstName,lastName)
    }

    private fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && str.trim().isNotEmpty())
            return false
        return true
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initials = (if (firstName.isNullOrBlank()) "" else firstName.substring(0, 1)) +
                if (lastName.isNullOrBlank()) "" else lastName.substring(0, 1)

        return (if (initials.isEmpty()) null else initials.toUpperCase())
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val mapping = payload.map {
            when (it) {
                ' ' -> divider
                'а' -> 'a'
                'б' -> 'b'
                'в' -> 'v'
                'г' -> 'g'
                'д' -> 'd'
                'е' -> 'e'
                'ё' -> 'e'
                'ж' -> "zh"
                'з' -> 'z'
                'и' -> 'i'
                'й' -> 'i'
                'к' -> 'k'
                'л' -> 'l'
                'м' -> 'm'
                'н' -> 'n'
                'о' -> 'o'
                'п' -> 'p'
                'р' -> 'r'
                'с' -> 's'
                'т' -> 't'
                'у' -> 'u'
                'ф' -> 'f'
                'х' -> 'h'
                'ц' -> 'c'
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> 'i'
                'ь' -> ""
                'э' -> 'e'
                'ю' -> "yu"
                'я' -> "ya"
                'А' -> 'A'
                'Б' -> 'B'
                'В' -> 'V'
                'Г' -> 'G'
                'Д' -> 'D'
                'Е' -> 'E'
                'Ё' -> 'E'
                'Ж' -> "Zh"
                'З' -> 'Z'
                'И' -> 'I'
                'Й' -> 'I'
                'К' -> 'K'
                'Л' -> 'L'
                'М' -> 'M'
                'Н' -> 'N'
                'О' -> 'O'
                'П' -> 'P'
                'Р' -> 'R'
                'С' -> 'S'
                'Т' -> 'T'
                'У' -> 'U'
                'Ф' -> 'F'
                'Х' -> 'H'
                'Ц' -> 'C'
                'Ч' -> "Ch"
                'Ш' -> "Sh"
                'Щ' -> "Sh'"
                'Ъ' -> ""
                'Ы' -> 'I'
                'Ь' -> ""
                'Э' -> 'E'
                'Ю' -> "Yu"
                'Я' -> "Ya"
                else -> it
            }
        }

        return mapping.joinToString(separator = "")
    }

    /**
     * @param pluralForms - три варианта множественной формы существительного, разделённых
     *                    точкой с запятой (пример: огурец;огурца;огурцов)
     * @param count       - количество
     * @return - множественная форма, соответствующая количеству
     */
    fun getPluralForm(pluralForms: String, count: Int): String {
        val forms = pluralForms.split(";")
        when (count % 10) {
            1 -> if (count % 100 != 11)
                return forms[0]
            in 2..4 -> if (count % 100 !in 12..14) {
                return forms[1]
            }
        }

        return forms[2]
    }
}