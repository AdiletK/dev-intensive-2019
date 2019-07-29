package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_abstract_factory() {
        println("""
           ${"Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate()}
           ${"Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15) }
           ${"A     ".truncate(3) }
           ${"<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml() }
        """.trimIndent())
    }

}
