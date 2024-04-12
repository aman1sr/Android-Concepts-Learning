import com.aman.kotlinapi.model.CartCountResponse
import com.cynoteck.kotlinapi.models.d2v.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET


interface NetworkAPI {

    // --------------------------------D2V---------------------------------------------
    // get the Lists of e-Commerce Products
    @GET("b2B/GetItemList")
    suspend fun getD2VProductList(): Response<ProductListResponse>

    // get card count
    @GET("b2B/GetCartCount")
    suspend fun getCartCount(): Response<CartCountResponse>





    // --------------------------------xxxx--------------------------------------------


}