package love.bside.models

import com.apollographql.apollo.api.Adapter
import com.apollographql.apollo.api.CompiledField
import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.json.JsonWriter
import com.benasher44.uuid.uuid4

//import com.benasher44.uuid.Uuid
//import kotlinx.serialization.Contextual
//import kotlinx.serialization.Serializable
//
//@Serializable
//data class User(val id: String, val name: String, val email: String, val profilePhoto: ProfilePhoto?, val voiceRecordings: List<VoiceRecording>?, val proustResponses: List<ProustResponse>?, val dateDetails: List<DateDetail>?, val reflections: List<Reflection>?)
//@Serializable
//data class ProfilePhoto(val id: String, val url: String, val userId: String)
//@Serializable
//data class VoiceRecording(val id: String, val url: String, val userId: String)
//@Serializable
//data class ProustQuestionnaire(val id: String, val questions: List<String>)
//@Serializable
//data class ProustResponse(val id: String, val questionnaireId: String, val userId: String, val responses: List<String>)
//@Serializable
//data class DateDetail(val id: String, val userId: String, val date: String, val location: String, val details: String)
//@Serializable
//data class Reflection(val id: String, val userId: String, val dateDetailId: String, val reflection: String)

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

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
) : Query.Data, Mutation.Data

@Serializable
data class ProfilePhoto(val id: String, val url: String, val userId: String)  : Query.Data

@Serializable
data class VoiceRecording(val id: String, val url: String, val userId: String)

@Serializable
data class ProustQuestionnaire(val id: String, val questions: List<String>)

@Serializable
data class ProustResponse(val id: String, val questionnaireId: String, val userId: String, val responses: List<String>)

@Serializable
data class DateDetail(val id: String, val userId: String, val date: String, val location: String, val details: String)

@Serializable
data class Reflection(val id: String, val userId: String, val dateDetailId: String, val reflection: String)

class UserQuery: Query<User> {
    fun users(): List<User> = listOf()
    override fun adapter(): Adapter<User> {
        TODO("Not yet implemented")
    }

    override fun document(): String {
        TODO("Not yet implemented")
    }

    override fun id(): String {
        TODO("Not yet implemented")
    }

    override fun name(): String {
        TODO("Not yet implemented")
    }

    override fun rootField(): CompiledField {
        TODO("Not yet implemented")
    }

    override fun serializeVariables(
        writer: JsonWriter,
        customScalarAdapters: CustomScalarAdapters,
        withDefaultValues: Boolean
    ) {
        TODO("Not yet implemented")
    }
}

class UserMutation: Mutation<User> {
    fun createUser(name: String, email: String): User = User(
        id = uuid4().toString(),
        name = name,
        email = email,
        profilePhoto = null,
        voiceRecordings = null,
        proustResponses = null,
        dateDetails = null,
        reflections = null
    )

    override fun adapter(): Adapter<User> {
        TODO("Not yet implemented")
    }

    override fun document(): String {
        TODO("Not yet implemented")
    }

    override fun id(): String {
        TODO("Not yet implemented")
    }

    override fun name(): String {
        TODO("Not yet implemented")
    }

    override fun rootField(): CompiledField {
        TODO("Not yet implemented")
    }

    override fun serializeVariables(
        writer: JsonWriter,
        customScalarAdapters: CustomScalarAdapters,
        withDefaultValues: Boolean
    ) {
        TODO("Not yet implemented")
    }
}