package info.esoft.qa.api.weaher_stack.current

import info.esoft.qa.api.weaher_stack.configuration.Settings
import info.esoft.qa.api.weaher_stack.model.CurrentWeatherCast
import io.restassured.RestAssured.requestSpecification
import io.restassured.mapper.ObjectMapperType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PositiveTest:Settings() {

    //?access_key=a81fec90dc60080676f94e572f425361&query=New York"
    @Test
    fun getNewYorkWeatherTest() {
        params?.put("access_key","a81fec90dc60080676f94e572f425361")
        params?.put("query", "New York")

        Given {
            spec(requestSpecification)
            queryParams(params)
        } When {
            get("/current")
        } Then {
            statusCode(200)
        }
    }

    @Test
    fun getLondonWeatherTest() {
        params?.put("access_key","a81fec90dc60080676f94e572f425361")
        params?.put("query", "London, United Kingdom")

        val nameCountry: String =
        Given {
            spec(requestSpecification)
            queryParams(params)
        } When {
            get("/current")
        } Then {
            statusCode(200)
        } Extract {
            path("location.name")
        }
        Assertions.assertEquals("London", nameCountry)
    }


    @Test
    fun getObjectTest() {
        params?.put("access_key","a81fec90dc60080676f94e572f425361")
        params?.put("query", "London, United Kingdom")

        val currentWeatherCast: CurrentWeatherCast =
        Given {
            spec(requestSpecification)
            queryParams(params)
        } When {
            get("/current")
        } Then {
            statusCode(200)
        } Extract {
            response().`as`(CurrentWeatherCast::class.java, ObjectMapperType.JACKSON_2)
        }

        println(currentWeatherCast.request.query)
    }
}