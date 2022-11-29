package info.esoft.qa.api.weaher_stack.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class RecipeSearch(
    @param:JsonProperty("hits") var hits: List<HitsInfo>
    )

@JsonIgnoreProperties(ignoreUnknown = true)
data class HitsInfo(
    @param:JsonProperty("recipe") var recipe: RecipeInfo
    )

@JsonIgnoreProperties(ignoreUnknown = true)
data class RecipeInfo (
    @param:JsonProperty("dietLabels") var dietLabels: List<String>,
    @param:JsonProperty("cuisineType") var cuisineType: List<String>,
    @param:JsonProperty("healthLabels") var healthLabels: List<String>,
    @param:JsonProperty("mealType") var mealType: List<String>,
    @param:JsonProperty("dishType") var dishType: List<String>,
    )