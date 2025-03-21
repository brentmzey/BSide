package love.bside.schema

import com.expediagroup.graphql.server.Schema
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import kotlinx.serialization.Serializable

object BSideSchema : Schema {

    @Serializable
    data class User(
        val id: String,
        val name: String,
        val email: String,
        val profilePhoto: ProfilePhoto?,
        val voiceRecordings: List<VoiceRecording>?,
        val proustResponses: List<ProustResponse>?,
        val dateDetails: List<DateDetail>?,
        val reflections: List<Reflection>?
    )

    @Serializable
    data class ProfilePhoto(val id: String, val url: String, val userId: String)

    @Serializable
    data class VoiceRecording(val id: String, val url: String, val userId: String)

    @Serializable
    data class ProustQuestionnaire(val id: String, val questions: List<String>)

    @Serializable
    data class ProustResponse(
        val id: String,
        val questionnaireId: String,
        val userId: String,
        val responses: List<String>
    )

    @Serializable
    data class DateDetail(
        val id: String,
        val userId: String,
        val date: String,
        val location: String,
        val details: String
    )

    @Serializable
    data class Reflection(
        val id: String,
        val userId: String,
        val dateDetailId: String,
        val reflection: String
    )

    class UserQuery : Query {
        fun users(): List<User> = listOf()
    }

    class UserMutation : Mutation {
        fun createUser(name: String, email: String): User = User(
            id = java.util.UUID.randomUUID().toString(),
            name = name,
            email = email,
            profilePhoto = null,
            voiceRecordings = null,
            proustResponses = null,
            dateDetails = null,
            reflections = null
        )
    }
}