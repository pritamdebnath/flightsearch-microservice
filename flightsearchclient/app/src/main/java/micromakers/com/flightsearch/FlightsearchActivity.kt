package micromakers.com.flightsearch

import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_flightsearch.*
import micromakers.com.sabre.dto.LeadPriceCalendarResponse
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


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
            try {
                val df = SimpleDateFormat("dd-MM-yyyy")
                val lenghtOfStay = TimeUnit.DAYS.convert(df.parse(arrDate.text.toString()).time - df.parse(depDate.text
                        .toString()).time, TimeUnit.MILLISECONDS)
                val accessToken = intent.extras.get("access_token")
                val url = getString(R.string.aws_host) + "/flightsearch/api/lowAirfareSearch?origin=" + origin.text +
                        "&destination=" + destination.text +
                        "&departureDate=" + depDate.text +
                        "&lengthOfStay=" + lenghtOfStay +
                        "&access_token=" + accessToken

                val restTemplate = RestTemplate()
                restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())
                return restTemplate.getForObject<LeadPriceCalendarResponse>(url, LeadPriceCalendarResponse::class.java)
            } catch (e: Exception) {
                Log.e("connection error", e.toString())
            }
            return null
        }

        override fun onPostExecute(flightSearchResponse: LeadPriceCalendarResponse?) {
            val df = SimpleDateFormat("dd-MM-yyyy HH:mm")
            searchResponse.setTextColor(Color.BLACK)
            if (flightSearchResponse != null) {
                for (fareInfoResponse in flightSearchResponse!!.fareInfo) {
                    val row = "\n Departure:" + df.format(fareInfoResponse.departureDateTime) +
                            "\n Return:" + df.format(fareInfoResponse.returnDateTime) +
                            "\n lowest fare: Airlines," + fareInfoResponse.lowestFare.airlineCodes[0] + " fare= " + fareInfoResponse
                            .lowestFare.fare + fareInfoResponse.currencyCode +
                            "\n lowest nonstop fare:  Airlines," + fareInfoResponse.lowestNonStopFare.airlineCodes[0] + " \nfare=" +
                            fareInfoResponse.lowestNonStopFare.fare + fareInfoResponse.currencyCode
                    searchResponse.append(row)
                    searchResponse.append("\n\n\n\n")
                }
            }
        }
    }
}
