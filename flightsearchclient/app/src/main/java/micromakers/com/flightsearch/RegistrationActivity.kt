package micromakers.com.flightsearch

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_registration.*
import micromakers.com.dto.Role
import micromakers.com.dto.User
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        registerButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                HttpRequestTask().execute()
            }
        })
    }

    private inner class HttpRequestTask : AsyncTask<Void, Void,  User>() {
        override fun doInBackground(vararg params: Void): User {
            val restTemplate = RestTemplate()
            restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())
            val url = getString(R.string.aws_host) + "/uaa/save"
            val user = User(username.text.toString(), password.text.toString(), hobby.text.toString(), ArrayList<Role>())
            return restTemplate.postForObject(url,user,User::class.java)
        }

        override fun onPostExecute(user:  User) {
            if (user!=null) {
                val nextIntent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                startActivity(nextIntent)
            }
        }
    }
}
