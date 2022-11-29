package info.esoft.qa.api.weaher_stack.configuration

import io.kotest.core.spec.style.AnnotationSpec
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

open class Settings {
    private var URL = "https://api.edamam.com"
    private var BASE_PATH = ""

    protected var headers:    HashMap<String, Any>? = null
    protected var body:       HashMap<String, Any>? = null
    protected var params:     HashMap<String, Any>? = null
    protected var pathParams: HashMap<String, Any>? = null

    protected var response: Response? = null

    private val apiLogger = AllureRestAssured()
        .setRequestTemplate("custom-http-request.ftl")
        .setResponseTemplate("custom-http-response.ftl")

    private val requestSpec = RequestSpecBuilder()
        .log(LogDetail.ALL)
        .addFilter(apiLogger)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)

    protected lateinit var requestSpecification: RequestSpecification

    private fun setup() {
        RestAssured.baseURI  = URL
        RestAssured.basePath = BASE_PATH

        requestSpec.setBaseUri(URL)
        requestSpec.setBasePath(BASE_PATH)
        requestSpecification = requestSpec.build()
    }

    @AnnotationSpec.BeforeAll
    open fun beforeTest() {
        setup()
    }

    @AnnotationSpec.AfterAll
    open fun afterTest() {
        RestAssured.reset()
    }

    @AnnotationSpec.BeforeEach
    open fun beforeEach() {
        response = null

        headers = HashMap()
        body = HashMap()
        params = HashMap()
        pathParams = HashMap()
    }

    init {
        setup()
        beforeTest()
        beforeEach()
        afterTest()
    }
}