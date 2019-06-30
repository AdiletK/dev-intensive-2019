package ru.skillbranch.devintensive.utils

object Utils {


    fun transliteration(payload: String,divider: String = ""): String {
        return if (isNullOrEmpty(divider)){
            convertRU(payload)
        } else{
            val parts:List<String>? = payload.trim().split(" ")
            val firstName:String
            var lastName = ""

            firstName = convertRU(parts!![0])
            if (parts.size>1){
                lastName = convertRU(parts[1])
                if (isNullOrEmpty(lastName)){
                    return "$firstName$divider"
                }
            }

            "$firstName$divider$lastName"
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return if (isNullOrEmpty(firstName)&& isNullOrEmpty(lastName)){
            null
        }else if( isNullOrEmpty(lastName)){
            return firstName?.get(0).toString().toUpperCase()
        }
        else {
            val firstChar = firstName?.get(0)
            val secondChar = lastName?.get(0)
            "$firstChar$secondChar".toUpperCase()
        }
    }

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

     private fun convertRU(cyr: String): String {

        var lat: String

        //Lower case letters
        lat = cyr.replace("а".toRegex(), "a")
        lat = lat.replace("б".toRegex(), "b")
        lat = lat.replace("в".toRegex(), "v")
        lat = lat.replace("г".toRegex(), "g")
        lat = lat.replace("д".toRegex(), "d")
        lat = lat.replace("е".toRegex(), "e")
        lat = lat.replace("ё".toRegex(), "e")
        lat = lat.replace("ж".toRegex(), "zh")
        lat = lat.replace("з".toRegex(), "z")
        lat = lat.replace("и".toRegex(), "i")
        lat = lat.replace("й".toRegex(), "i")
        lat = lat.replace("к".toRegex(), "k")
        lat = lat.replace("л".toRegex(), "l")
        lat = lat.replace("м".toRegex(), "m")
        lat = lat.replace("н".toRegex(), "n")
        lat = lat.replace("о".toRegex(), "o")
        lat = lat.replace("п".toRegex(), "p")
        lat = lat.replace("р".toRegex(), "r")
        lat = lat.replace("с".toRegex(), "s")
        lat = lat.replace("т".toRegex(), "t")
        lat = lat.replace("у".toRegex(), "u")
        lat = lat.replace("ф".toRegex(), "f")
        lat = lat.replace("х".toRegex(), "h")
        lat = lat.replace("ц".toRegex(), "с")
        lat = lat.replace("ч".toRegex(), "ch")
        lat = lat.replace("ш".toRegex(), "sh")
        lat = lat.replace("щ".toRegex(), "sh")
        lat = lat.replace("ъ".toRegex(), "")
        lat = lat.replace("ы".toRegex(), "i")
        lat = lat.replace("ь".toRegex(), "")
        lat = lat.replace("э".toRegex(), "e")
        lat = lat.replace("ю".toRegex(), "yu")
        lat = lat.replace("я".toRegex(), "ya")

        //Capital letters
        lat = lat.replace("А".toRegex(), "A")
        lat = lat.replace("Б".toRegex(), "B")
        lat = lat.replace("В".toRegex(), "V")
        lat = lat.replace("Г".toRegex(), "G")
        lat = lat.replace("Д".toRegex(), "D")
        lat = lat.replace("Е".toRegex(), "E")
        lat = lat.replace("Ё".toRegex(), "E")
        lat = lat.replace("Ж".toRegex(), "ZH")
        lat = lat.replace("З".toRegex(), "Z")
        lat = lat.replace("И".toRegex(), "I")
        lat = lat.replace("Й".toRegex(), "I")
        lat = lat.replace("К".toRegex(), "K")
        lat = lat.replace("Л".toRegex(), "L")
        lat = lat.replace("М".toRegex(), "M")
        lat = lat.replace("Н".toRegex(), "N")
        lat = lat.replace("О".toRegex(), "O")
        lat = lat.replace("П".toRegex(), "P")
        lat = lat.replace("Р".toRegex(), "R")
        lat = lat.replace("С".toRegex(), "S")
        lat = lat.replace("Т".toRegex(), "T")
        lat = lat.replace("У".toRegex(), "U")
        lat = lat.replace("Ф".toRegex(), "F")
        lat = lat.replace("Х".toRegex(), "H")
        lat = lat.replace("Ц".toRegex(), "C")
        lat = lat.replace("Ч".toRegex(), "CH")
        lat = lat.replace("Ш".toRegex(), "SH")
        lat = lat.replace("Щ".toRegex(), "SH")
        lat = lat.replace("Ъ".toRegex(), "")
        lat = lat.replace("Ы".toRegex(), "I")
        lat = lat.replace("Ь".toRegex(), "")
        lat = lat.replace("Э".toRegex(), "E")
        lat = lat.replace("Ю".toRegex(), "YU")
        lat = lat.replace("Я".toRegex(), "YA")


        return lat //Return latinized string


    }
}