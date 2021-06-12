package com.bshapp.API


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface RetrofitApi {

   @FormUrlEncoded
   @POST("api/createNews.php")
   suspend fun uploadNews(@FieldMap params: HashMap<String?, String?>): Response<ResponseBody>

   @GET("api/getSlider.php")
   suspend fun getSlider() : Response<ResponseBody>
}