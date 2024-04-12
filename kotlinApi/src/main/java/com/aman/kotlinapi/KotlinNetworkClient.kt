

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
//import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
//import com.cynoteck.petofyOPHR.utils.Config

object KotlinNetworkClient {

    //----------------------------TESTING-----------------
//    var BASE_URL = "https://petofyoptimizedapi.azurewebsites.net/api/"      //new testing

    //---------------------LIVE----------------------
    var BASE_URL = "https://petofymobileapi.azurewebsites.net/api/";          //new live*/


    var authToken: String? = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjVkMGJkNmQ0LTIzNjQtNGU1Ny04Yzk1LTA3MzZlYTgwMDIyMSIsIm5iZiI6MTcxMjU2MjE4OSwiZXhwIjoxNzQ0MDk4MTg5LCJpYXQiOjE3MTI1NjIxODl9.tdbktBsna-GgaucQc4KpmIzR5QazbVXWfOGTF-pWXLU"

    private val authInterceptor = Interceptor{chain ->
        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                .header("Authorization", it)
                .build()
        }
        chain.proceed(req)
    }


    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2,TimeUnit.SECONDS)


    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())



    val publicApi = retrofitBuilder
        .build()
        .create(NetworkAPI::class.java)

    val authApi = retrofitBuilder
        .client(okHttpBuilder.addInterceptor(authInterceptor).build())
        .build()
        .create(NetworkAPI::class.java)

    val authApiDi = retrofitBuilder
        .client(okHttpBuilder.addInterceptor(authInterceptor).build())


}