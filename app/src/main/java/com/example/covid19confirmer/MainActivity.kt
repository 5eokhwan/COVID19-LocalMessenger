package com.example.covid19confirmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private var BASE_URL = "http://openapi.data.go.kr/"
    private var selectedLocal : String? = "전남"   //지역 데이터를 여기서 넣어줘야됨

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit : Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build()

        val api = retrofit.create(API::class.java)

        var call = api.getData()

        call.enqueue(object : Callback<JsonStart> {
            override fun onFailure(call: Call<JsonStart>, t: Throwable) {
                //호출 실패
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<JsonStart>, response: Response<JsonStart>) {
                //성공시
                Log.e("DDDDAA", response.body()?.response?.header?.resultCode!!)
                for(localInfo : Item in response.body()?.response?.body?.items?.item!!) {
                    if(localInfo.gubun != selectedLocal) {   //선택 지역과 일치하지 않을시
                        continue
                    }
                    else if (localInfo.gubun == selectedLocal) { //지역과 일치시
                        main_local_text_view.text = selectedLocal
                        main_10man_num_text_view.text = localInfo.qurRate
                        main_today_num_text_view.text = localInfo.incDec
                    }
                }
            }
        })
    }
}
