package net.igoseas.logindemo.data

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import net.igoseas.logindemo.data.model.LoggedInUser
import org.json.JSONObject
import java.io.IOException
import android.content.Context
import net.igoseas.logindemo.ui.login.LoginActivity
/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            Log.i("LoginDataSource","login")
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
            val url = "http://192.168.2.104:8000/device/login"
            val `jsInfo` = JSONObject()
            try { //input your API parameters
                `jsInfo`.put("email", username)
                `jsInfo`.put("password", password)
                `jsInfo`.put("device_serial", "USDFQWEIUGU23EUI")
                val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsInfo,
                    Response.Listener { response ->
                        Log.i("LoginDataSource", "Response: %s".format(response.toString()))
                    },
                    Response.ErrorListener { error ->
                        Log.i("LoginDataSource", error.toString())
                    }
                )
                val context: Context = LoginActivity.applicationContext()
                val requestQueue = Volley.newRequestQueue(context)
                requestQueue.add(jsonObjectRequest);
            } catch (e: Exception) {
                e.printStackTrace()
            }

            Log.i("LoginDataSource","fakeUser")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

