package love.bside

import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import com.expediagroup.graphql.server.execution.GraphQLRequestHandler
import com.expediagroup.graphql.server.ktor.DefaultKtorGraphQLContextFactory
import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.graphQLPostRoute
import com.expediagroup.graphql.server.types.GraphQLServerRequest
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.application.install
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import love.bside.schema.BSideSchema

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
//    install(ContentNegotiation) {
//        json()
//    }
    install(StatusPages)
    install(CallLogging)
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        get("/tasks") {
            call.respondText(
                contentType = ContentType.Text.Html,
                text = """
                <h3>TODO:</h3>
                <ol>
                    <li>A table of all the tasks</li>
                    <li>A form to submit new tasks</li>
                </ol>
                """.trimIndent()
            )
        }
        post("/graphql") {
            graphQLModule()
        }
    }
}

fun Application.graphQLModule(graphQLEndpoint: String = "graphql",
                              streamingResponse: Boolean = true,
                              jacksonConfiguration: ObjectMapper.() -> Unit = {}) {
    install(GraphQL) {
        schema {
            packages = listOf("love.bside.schema")
            queries = listOf(
                BSideSchema.UserQuery()
            )
            mutations = listOf(
                BSideSchema.UserMutation()
            )
            schemaObject = BSideSchema
        }
        server {
            contextFactory = DefaultKtorGraphQLContextFactory()
        }
    }
    routing {
        val graphQLPlugin = this.application.plugin(GraphQL)
//        post("/$graphQLEndpoint") {
//            graphQLPlugin.server.execute(call.request)
//        }
        val route = post("/$graphQLEndpoint") {
            graphQLPlugin.server.execute(call.request)
        }
        route.install(ContentNegotiation) {
            jackson(streamRequestBody = streamingResponse) {
                apply(jacksonConfiguration)
            }
        }
    }
//    routing {
//        post(graphQLPostRoute()) {
//            val request = call.receive<GraphQLServerRequest>()
//            val response = GraphQLRequestHandler().executeRequest(request, call)
//            call.respond(response)
//        }
//    }
}