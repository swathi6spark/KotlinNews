package com.swathi.kotlinnews

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import com.swathi.kotlinnews.RecyclerView.KotlinFeedAdapter
import com.swathi.kotlinnews.Retrofit.Api
import com.swathi.kotlinnews.Retrofit.KotlinFeed

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var kotlinFeed: KotlinFeed? = null

    private var recyclerView: RecyclerView? = null
    private var adapter: KotlinFeedAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var articles: List<KotlinFeed.FeedData.Article>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(Api::class.java)

        val call = api.kotlinFeed

        call.enqueue(object : Callback<KotlinFeed> {
            override fun onResponse(call: Call<KotlinFeed>, response: Response<KotlinFeed>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Couldn't fetch data from the url", Toast.LENGTH_SHORT).show()

                } else {

                    kotlinFeed = response.body()
                    articles = ArrayList<KotlinFeed.FeedData.Article>()
                    articles = kotlinFeed!!.feedData!!.articles

                    recyclerView = findViewById(R.id.recyclerView)
                    adapter = KotlinFeedAdapter(articles!!)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView!!.setHasFixedSize(false)
                    recyclerView!!.layoutManager = layoutManager
                    recyclerView!!.adapter = adapter


                    adapter!!.onItemClickListener(object : KotlinFeedAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val article_title = articles!![position].articleData!!.title
                            val article_thumbnail = articles!![position].articleData!!.thumbnail
                            val article_body = articles!![position].articleData!!.url

                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            intent.putExtra("title", article_title)
                            intent.putExtra("thumbnail", article_thumbnail)
                            intent.putExtra("articleBody", article_body)


                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<KotlinFeed>, t: Throwable) {

                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
