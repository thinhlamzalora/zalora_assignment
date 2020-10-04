package zalora.assignment.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import zalora.assignment.data.model.CatRemote
import zalora.assignment.data.utils.Constant

interface CatAPI {
    @GET("/${Constant.VERSION_API}/images/search")
    suspend fun getCats(@Query("limit") limit: Int = Constant.PAGE_COUNT,
                        @Query("page") page: Int,
                        @Query("mime_types") type: String = "png"): List<CatRemote>
}