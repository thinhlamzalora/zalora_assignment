package zalora.assignment.data.model

import com.google.gson.annotations.SerializedName

data class CatRemote(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    var height: Int
)