package love.bside.models.graphql

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(val query: String, val variables: Map<String, String?>)

@Serializable
data class GraphQLResponse<T>(val data: T)