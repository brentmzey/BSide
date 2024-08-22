package love.bside.database

import kotlinx.coroutines.coroutineScope
import love.bside.models.User

object UserRepository {
    private const val GET_USERS_QUERY = """
        query {
            users {
                id
                name
                email
            }
        }
    """

    suspend fun loadUsers(onUsersLoaded: (List<User>) -> Unit) {
        coroutineScope {
//            val users: List<User> = GraphQLClient.query(GET_USERS_QUERY)
//            onUsersLoaded(users)
        }
    }
}