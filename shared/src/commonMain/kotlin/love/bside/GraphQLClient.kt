package love.bside

//import io.ktor.client.*
//import io.ktor.client.call.body
//import io.ktor.client.engine.cio.*
//import io.ktor.client.plugins.json.*
//import io.ktor.client.plugins.json.serializer.*
//import io.ktor.client.plugins
//import io.ktor.client.request.*
//import io.ktor.client.statement.HttpResponse
//import io.ktor.client.statement.bodyAsText
//import io.ktor.client.statement.readText
//import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import love.bside.models.graphql.GraphQLRequest
import love.bside.models.graphql.GraphQLResponse

object GraphQLClient {
//    val client = HttpClient(CIO) {
//        install(ContentNegotiation) {
//            serializer = KotlinxSerializer(Json {
//                ignoreUnknownKeys = true
//                isLenient = true
//                encodeDefaults = false
//            })
//        }
//        install(Logging) {
//            level = LogLevel.BODY
//        }
//    }

//    suspend inline fun <reified T> query(query: String,
//                                         variables: Map<String, String?> = emptyMap()): T {
//        val response: HttpResponse = client.post("http://localhost:8080/graphql") {
//            GraphQLRequest(query, variables)
//        }
//        return GraphQLResponse(response.bodyAsText()).data as T
//    }
}