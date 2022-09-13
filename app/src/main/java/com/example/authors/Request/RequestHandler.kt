/*
* Basic request handler, this class would become specified to
* request type as types of request expand.
* */

package com.example.topalbums.RequestHandler

import com.example.delivery.Request.Items.AuthorItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RequestHandler {

    interface AlbumsService {
        @GET("url?u=https-3a__picsum.photos_v2_list-3fpage-3d2-26limit-3d20&amp;d=dwmf-g&amp;c=biohidp8cpffeowoiyrjqw&amp;r=mbdw7xrfy_1pkf2uy3xc3i8bawvhfdtrpkpz8o59ahk&amp;m=qtlvrexnjug3xkwd-broqzteujf4-wfvo5da5ltbdypxozps1lpkg3heqohearor&amp;s=fcszy-f\">https://picsum.photos/v2/list?page=2&amp;limit=20")
        fun getRequest(): Call<List<AuthorItem>>
    }

    companion object {
        fun makeRequest(requestCallback: RequestCallback) {
            val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://urldefense.proofpoint.com/v2/")
                .build()

            val albumService = retrofit.create(AlbumsService::class.java)
            albumService.getRequest()!!.enqueue(object : Callback<List<AuthorItem>> {

                override fun onResponse(
                    call: Call<List<AuthorItem>>,
                    response: Response<List<AuthorItem>>) {
                    requestCallback.onResult (if(response.body() != null) response.body() else null)
                }

                override fun onFailure(call: Call<List<AuthorItem>>, t: Throwable?) {
                    requestCallback.onResult (null)
                }
            })
        }
    }

    interface RequestCallback {
        fun onResult(result: List<AuthorItem>?)
    }
}