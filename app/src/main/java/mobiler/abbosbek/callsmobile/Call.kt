package mobiler.abbosbek.callsmobile

import java.util.*

data class Call(
    val phone: String,
    val callType: String,
    val date: Date,
    val duration: String
)
