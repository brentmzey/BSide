package love.bside.models

import com.benasher44.uuid.Uuid
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class User(@Contextual val id: Uuid, val name: String, val email: String, val profilePhoto: ProfilePhoto?, val voiceRecordings: List<VoiceRecording>?, val proustResponses: List<ProustResponse>?, val dateDetails: List<DateDetail>?, val reflections: List<Reflection>?)
@Serializable
data class ProfilePhoto(val id: String, val url: String, val userId: String)
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