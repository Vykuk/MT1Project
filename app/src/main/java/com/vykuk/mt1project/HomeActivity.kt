package com.vykuk.mt1project

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.vykuk.mt1project.databinding.ActivityHomeBinding
import okhttp3.*
import java.io.IOException
import kotlin.math.log

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.getMyIP.setOnClickListener{
            val message = Snackbar.make(view,"Zjistím svou IP", Snackbar.LENGTH_LONG)
            //message.setAction("Action",null)
            message.setTextColor(Color.GRAY)
            message.show()
            run("https://api.ipify.org?format=json")
        }

        binding.locateIP.setOnClickListener{

            val ipAddress = ipValidate(binding.inputIP.text.toString())
            if( ipAddress!= null ){
                val message = Snackbar.make(view,"Zadána IP: $ipAddress", Snackbar.LENGTH_LONG)
                //message.setAction("Action",null)
                message.setTextColor(Color.GREEN)
                message.show()
            }
            else {
                val message = Snackbar.make(view,"IP zadána ve špatném formátu", Snackbar.LENGTH_LONG)
                //message.setAction("Action",null)
                message.setTextColor(Color.RED)
                message.show()
            }
        }
    }

    private fun ipValidate( inputIP: String) : String? {
        val sourceIP = inputIP.split(".")
        var ip = arrayOf<Int>()
        for( stringIP in sourceIP){
            val convert = stringIP.toIntOrNull()
            //convert?.let {ip+=it}
            if( convert != null )
                ip+=convert
            else
                return null
        }
        if(ip.size!=4)
            return null
        if(ip[0]==0)
            return null
        for( i in ip ){
            if(i<0 || i> 255)
                return null
        }
        return  ip.joinToString(".")
    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.v("IPLOCATOR", "error ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.v("IPLOCATOR", "response ${response.body()?.toString()}")
            }
        })
    }
}