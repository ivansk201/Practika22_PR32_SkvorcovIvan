package com.example.pr22


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

lateinit var enter:EditText
lateinit var information: TextView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enter=findViewById(R.id.editText)
        information=findViewById(R.id.info)


    }

    fun getResult (city:String)
    {
        if (city.isNotEmpty() && city!=null)
        { var key = "a6291dbb105f5aea6f7b9219b3e728b2"
           var url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+key+"&units=metric&lang=ru"
            var queue = Volley.newRequestQueue(this)
            var stringRequest = StringRequest(
            Request.Method.GET,
                url,
                {
                    response ->
                    val obj = JSONObject(response)
                    val temp = obj.getJSONObject("main")
                    val wind = obj.getJSONObject("wind")
                    var string = "Температура: ${temp.getString("temp")} С \n Давление: ${temp.getString("pressure")} \n Скорость ветра: ${wind.getString("speed")} мс"
                   information.text=string
                },
                {
                    Log.d("MyLog","Volley error: $it")
                }
            )
            queue.add(stringRequest)
        }
        else
        {
            var sn= Snackbar.make(findViewById<View>(android.R.id.content),R.string.nevveligorod,Snackbar.LENGTH_LONG)

            sn.show()
        }
    }

    fun click(view: View) {
        getResult(enter.text.toString())
    }
}