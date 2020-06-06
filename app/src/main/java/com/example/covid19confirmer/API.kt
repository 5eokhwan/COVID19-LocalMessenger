package com.example.covid19confirmer

import retrofit2.Call
import retrofit2.http.GET

interface API {
    //@Headers("Accept: application/json")
    @GET("openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=HfqivhIZ0LdSWOWJuO6cYiOUrrrTGDG0yB22Wsc6pbmvUOfwIuIzUOqnv9ZK6Ppvw2mHnFk%2Bf2yNllDWnCrfRw%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200410&endCreateDt=20200410&_type=json")
    fun getData() : Call<JsonStart>
    //fun getData(@Path("key") key: String) : Call<dataInfo>
}