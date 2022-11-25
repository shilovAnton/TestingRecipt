package info.esoft.qa.api.weaher_stack.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class CurrentWeatherCast(
    @param:JsonProperty("request") var request: RequestInfo
    )

@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestInfo(
    @param:JsonProperty("type") var type: String,
    @param:JsonProperty("query") var query: String
)
