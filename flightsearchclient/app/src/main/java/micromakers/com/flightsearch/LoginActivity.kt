package micromakers.com.flightsearch

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*
import micromakers.com.dto.User
import org.sdf.danielsz.OAuth2Client
import org.sdf.danielsz.Token
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                HttpRequestTask().execute()
            }
        })

        registerButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val nextIntent = Intent(this@LoginActivity, RegistrationActivity::class.java)
                startActivity(nextIntent)
            }
        })
    }

    private inner class HttpRequestTask : AsyncTask<Void, Void, Token>() {
        override fun doInBackground(vararg params: Void): Token? {
            val restTemplate = RestTemplate()
            restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())
            FirebaseMessaging.getInstance().subscribeToTopic(restTemplate.getForObject(getString(R.string.aws_host)
                    + "/uaa/findByUsername?username=" + userName.text, User::class.java).hobby)
            val url = getString(R.string.aws_host) + "/uaa"
            val client = OAuth2Client(userName.text.toString(), password.text.toString(), getString(R.string.client_id), getString(R.string.client_secret), url)
            return try {
                client.accessToken
            } catch (e: Throwable) {
                null
            }
        }

        override fun onPostExecute(accessToken: Token?) {
            if (accessToken != null) {
                val nextIntent = Intent(this@LoginActivity, FlightsearchActivity::class.java)
                nextIntent.putExtra("access_token", accessToken.accessToken)
                nextIntent.putExtra("username", userName.text)
                startActivity(nextIntent)
            } else {
                infoMsg.text = "wrong credentials"
            }
        }
    }
}
