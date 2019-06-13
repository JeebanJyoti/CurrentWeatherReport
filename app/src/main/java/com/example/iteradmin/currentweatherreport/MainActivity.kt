package com.example.iteradmin.currentweatherreport

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.textclassifier.TextLinks
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val e=findViewById<EditText>(R.id.city)
        val b=findViewById<Button>(R.id.save)
        val t=findViewById<TextView>(R.id.report)
        val queue=Volley.newRequestQueue(this)
        val url:String="https://api.openweathermap.org/data/2.5/weather?q="
        val api:String="&appid=3dd6d921ef1d85fe3390a6049027a14f"

        b.setOnClickListener {
            val complink=url+e.text.toString()+api
            val jsnRequest:JsonObjectRequest= JsonObjectRequest(Request.Method.GET,complink,null,
                    Response.Listener<JSONObject> {
                        response ->
                        val ar:JSONObject=response.getJSONObject("coord")
                        val s:String="Longitude" + ar.get("lon") +
                                "Latitude" + ar.get("lat")
                        t.text=s
                    },
                    Response.ErrorListener {

                    })
            queue.add(jsnRequest)
        }
    }
}
