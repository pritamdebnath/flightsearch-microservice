package micromakers.com.flightsearch

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_flightsearch.*
import micromakers.com.sabre.dto.LeadPriceCalendarResponse
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate


class FlightsearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flightsearch)
        searchButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                HttpRequestTask().execute()
            }
        })
    }


    private inner class HttpRequestTask : AsyncTask<Void, Void, LeadPriceCalendarResponse>() {
        override fun doInBackground(vararg params: Void): LeadPriceCalendarResponse? {
          /*  try {
                val url = "http://192.168.1.6:8020/lowAirfareSearch?origin=LAX&destination=JFK&departureDate=22-12-2017&lengthOfStay=5"
                val restTemplate = RestTemplate()
                restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())
                return restTemplate.getForObject<LeadPriceCalendarResponse>(url, LeadPriceCalendarResponse::class.java)
            } catch (e: Exception) {
                Log.e("connection error",e.toString())
            }*/

            return null
        }

        override fun onPostExecute(response: LeadPriceCalendarResponse) {
            Log.i("responseeeee::::::",response.toString())
            //will do something later here
        }

    }
}
