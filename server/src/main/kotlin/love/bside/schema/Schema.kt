package love.bside.schema

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import love.bside.models.DateDetail
import love.bside.models.ProfilePhoto
import love.bside.models.ProustQuestionnaire
import love.bside.models.ProustResponse
import love.bside.models.Reflection
import love.bside.models.User
import love.bside.models.VoiceRecording

fun SchemaBuilder.schema() {
    type<User> {
        description = "A user in the system"
    }

    type<ProfilePhoto> {
        description = "A user's profile photo"
    }

    type<VoiceRecording> {
        description = "A user's voice recording"
    }

    type<ProustQuestionnaire> {
        description = "A Proust questionnaire"
    }

    type<ProustResponse> {
        description = "A response to a Proust questionnaire"
    }

    type<DateDetail> {
        description = "Details of an in-person date"
    }

    type<Reflection> {
        description = "A user's reflection on a date"
    }

    query("users") {
        resolver { -> listOf<User>() }
    }

//    mutation("createUser") {
//        resolver { name: String, email: String -> User(
//            id = uuid(),
//            name = name,
//            email = email
//        )
//        }
//    }

    // Add other queries and mutations similarly
}