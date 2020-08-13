package com.kabarnagari

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(this)
        val prefManager:PrefManager= PrefManager(this)

        val nama=namauserReg.editableText.toString()
        prefManager.save("nama", nama)
        if (nama==null){
            intent = Intent(applicationContext, WebView::class.java)
            startActivity(intent)
        }
        btnRegist.setOnClickListener { simpan() }

    }

    private fun simpan() {
        AndroidNetworking.post("http://sayursegar.pelangiindahtransport.com/api/register.php")
            .addBodyParameter("email", emailuserReg.getText().toString())
            .addBodyParameter("nama", namauserReg.getText().toString())
            .addBodyParameter("alamat", alamatuserReg.getText().toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        if (response.getString("success") == 1.toString()) {
                            Toast.makeText(
                                applicationContext,
                                response.getString("message") + " Hahah",
                                Toast.LENGTH_SHORT
                            )
                            val prefManager:PrefManager= PrefManager(this@MainActivity)
                            prefManager.save("id",1)
                            intent = Intent(applicationContext, WebView::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            finish()
                            startActivity(intent)

                        } else {
                            Toast.makeText(
                                applicationContext,
                                response.getString("message"),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onError(anError: ANError) {
                    Log.d("error regis", "error : $anError")
                }
            })
    }
}
