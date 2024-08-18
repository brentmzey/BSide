package love.bside.models

data class User(val id: String, val name: String, val email: String, val profilePhoto: ProfilePhoto?, val voiceRecordings: List<VoiceRecording>?, val proustResponses: List<ProustResponse>?, val dateDetails: List<DateDetail>?, val reflections: List<Reflection>?)
data class ProfilePhoto(val id: String, val url: String, val userId: String)
data class VoiceRecording(val id: String, val url: String, val userId: String)
data class ProustQuestionnaire(val id: String, val questions: List<String>)
data class ProustResponse(val id: String, val questionnaireId: String, val userId: String, val responses: List<String>)
data class DateDetail(val id: String, val userId: String, val date: String, val location: String, val details: String)
data class Reflection(val id: String, val userId: String, val dateDetailId: String, val reflection: String)