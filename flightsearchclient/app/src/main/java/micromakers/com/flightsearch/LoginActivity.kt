package micromakers.com.flightsearch

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*
import org.sdf.danielsz.OAuth2Client
import org.sdf.danielsz.Token


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        loginButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                HttpRequestTask().execute()
            }
        })
    }

    private inner class HttpRequestTask : AsyncTask<Void, Void, Token>() {
        override fun doInBackground(vararg params: Void): Token? {
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
                nextIntent.putExtra("access-token", accessToken.accessToken)
                startActivity(nextIntent)
            } else {
                infoMsg.text = "wrong credentials"
            }
        }
    }
}
