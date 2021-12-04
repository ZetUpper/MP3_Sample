package com.cookandroid.mp3

import android.telecom.Call
import java.net.CacheResponse

interface MusicService {
    @GET("/v3/e4db045a-23a9-4b49-a3fc-78cf51f3f964")
    fun listMusics(): Call<MusicDto>

    private fun getVideoListFromServer(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://runn.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MusicService::class.java)
            .also {
                it.listMusics()
                    .enqueue(object : Callback<MusicDto> {
                        override fun onResponse(
                            call: Call<MusicDto>,
                            response: Response<MusicDto>
                        ){
                            response.body()?.let { musicDto ->
                                model = musicDto.mapper()

                                setMusicList(model.getAdapterModels())
                                adapter.submitList(model.getAdapterModels())
                            }
                        }
                        override fun onFailure(call : Call<MusicDto>, t: Trrowable) {

                        }
                    })
            }
    }

}