package info.esoft.qa.api.weaher_stack.current

import info.esoft.qa.api.weaher_stack.configuration.Settings
import info.esoft.qa.api.weaher_stack.model.RecipeSearch
import io.restassured.mapper.ObjectMapperType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PositiveTest:Settings() {

    //?type=public&app_id=d85dd45b&app_key=ce120a6f62b9f64774014ee66a73a57c&q=paste
    @Test
    fun getSearchTest() {
        params?.put("type","public")
        params?.put("app_id", "d85dd45b")
        params?.put("app_key", "ce120a6f62b9f64774014ee66a73a57c")
        params?.put("q", "paste")

        Given {
            spec(requestSpecification)
            queryParams(params)
        } When {
            get("/api/recipes/v2")
        } Then {
            statusCode(200)
        }
    }

    @Test
    fun getEmptyTest() {
        params?.put("type","public")
        params?.put("app_id", "d85dd45b")
        params?.put("app_key", "ce120a6f62b9f64774014ee66a73a57c")

        Given {
            spec(requestSpecification)
            queryParams(params)
        } When {
            get("/api/recipes/v2")
        } Then {
            statusCode(200)
        }
    }

    @Test
    fun getDietBalancedTest() {
        params?.put("type","public")
        params?.put("app_id", "d85dd45b")
        params?.put("app_key", "ce120a6f62b9f64774014ee66a73a57c")
        params?.put("q", "paste")
        params?.put("diet", "balanced")

        val recipeSearch: RecipeSearch =
        Given {
            spec(requestSpecification)
            queryParams(params)
        } When {
            get("/api/recipes/v2")
        } Then {
            statusCode(200)
        } Extract {
            response().`as`(RecipeSearch::class.java, ObjectMapperType.JACKSON_2)
        }
        Assertions.assertTrue(recipeSearch.hits.all { it.recipe.dietLabels.contains("Balanced")})
    }

    @Test
    fun getCuisineTypeTest() {
        params?.put("type","public")
        params?.put("app_id", "d85dd45b")
        params?.put("app_key", "ce120a6f62b9f64774014ee66a73a57c")
        params?.put("cuisineType", "American")

        val recipeSearch: RecipeSearch =
            Given {
                spec(requestSpecification)
                queryParams(params)
            } When {
                get("/api/recipes/v2")
            } Then {
                statusCode(200)
            } Extract {
                response().`as`(RecipeSearch::class.java, ObjectMapperType.JACKSON_2)
            }
        Assertions.assertTrue(recipeSearch.hits.all { it.recipe.cuisineType.contains("american")})
    }

    @Test
    fun getAllParametersTest() {
        params?.put("type","public")
        params?.put("app_id", "d85dd45b")
        params?.put("app_key", "ce120a6f62b9f64774014ee66a73a57c")
        params?.put("health", "alcohol-free")
        params?.put("mealType", "Dinner")
        params?.put("dishType", "Main course")

        val recipeSearch: RecipeSearch =
            Given {
                spec(requestSpecification)
                queryParams(params)
            } When {
                get("/api/recipes/v2")
            } Then {
                statusCode(200)
            } Extract {
                response().`as`(RecipeSearch::class.java, ObjectMapperType.JACKSON_2)
            }
        Assertions.assertTrue(recipeSearch.hits.all { it.recipe.healthLabels.contains("Alcohol-Free")
             && it.recipe.mealType.contains("lunch/dinner")
                && it.recipe.dishType.contains("main course")
           })
    }


}

















