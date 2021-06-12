package com.bshapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bshapp.R
import com.bshapp.classes.URLs
import com.bshapp.model.HomeNewsModel
import com.bumptech.glide.Glide
import org.json.JSONObject


class ReadNewsActivity : AppCompatActivity() {
    var image: ImageView? = null
    var like: ImageView? = null
    var dislike: ImageView? = null
    var headlineText: TextView? = null
    var content: TextView? = null
    var uploadedBy: TextView? = null
    var newsId: String? = null
    lateinit var model: HomeNewsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_read)
        var bundle: Bundle? = intent.extras
        newsId = bundle!!.getString("id")
        if (newsId != null) {
            getData()
        } else {
            Toast.makeText(this, "Something error", Toast.LENGTH_SHORT).show()
        }
        image = findViewById<ImageView>(R.id.image)
        like = findViewById<ImageView>(R.id.like)
        dislike = findViewById<ImageView>(R.id.dislike)
        headlineText = findViewById<TextView>(R.id.headline)
        content = findViewById<TextView>(R.id.content)
        uploadedBy = findViewById<TextView>(R.id.name)
        findViewById<Button>(R.id.readMoreBtn).visibility = View.GONE
    }

    private fun getData() {


        val queue = Volley.newRequestQueue(this)
        val url = URLs.NEWS_URL + "?id=" + newsId

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("API", response.toString())
                try {

                    val respObj = JSONObject(response)
                    val success = respObj.getInt("status")
                    val jsonArray = respObj.getJSONObject("data")
                    if (success.equals(1)) {
                        val images = jsonArray.getString("imageName")
                        val headline = jsonArray.getString("heading")
                        val news = jsonArray.getString("news")
                        val mandal = jsonArray.getString("mandal")
                        val dates = jsonArray.getString("date")
                        val uploader = jsonArray.getString("uploader")
                        headlineText?.text = headline
                        content?.text = news
                        uploadedBy?.text = uploader
                        val url1 = URLs.BASE_URL + "Images/" + images;
                        image?.let { Glide.with(this).load(url1).into(it) }
                        like?.setOnClickListener(View.OnClickListener {
                            likePost(1)
                        })
                        dislike?.setOnClickListener(View.OnClickListener {
                            likePost(0)
                        })

                    }

                } catch (e: Exception) {
                    Log.d("Home", e.toString())
                }
            },
            {
                Log.d("API", "that didn't work")
            })
        queue.add(stringReq)
    }

    private fun likePost(likeDislike: Int) {


        val queue = Volley.newRequestQueue(this)
        val url = URLs.LIKE_URL + "?id=" + newsId+"&type="+likeDislike

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("API", response.toString())
                try {
                    val respObj = JSONObject(response)
                    val success = respObj.getInt("status")
                    if (success == 1) {
                        Toast.makeText(this, respObj.getString("msg"), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.d("Home", e.toString())
                }
            },
            {
                Log.d("API", "that didn't work")
            })
        queue.add(stringReq)
    }
}