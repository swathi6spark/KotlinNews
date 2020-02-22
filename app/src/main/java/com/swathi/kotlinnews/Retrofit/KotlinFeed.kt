package com.swathi.kotlinnews.Retrofit

import com.google.gson.annotations.SerializedName

class KotlinFeed {

    @SerializedName("kind")
    var feedKind: String? = null
    @SerializedName("data")
    var feedData: FeedData? = null

    inner class FeedData {

        @SerializedName("dist")
        private val dist: Int? = null

        @SerializedName("children")
        var articles: List<Article>? = null

        inner class Article {

            @SerializedName("kind")
            var articleKind: String? = null


            @SerializedName("data")
            var articleData: ArticleData? = null

            inner class ArticleData {


                @SerializedName("subreddit")
                var subreddit: String? = null

                @SerializedName("selftext")
                var selftext: String? = null

                @SerializedName("author_fullname")
                var authorFullname: String? = null


                @SerializedName("title")
                var title: String? = null

                @SerializedName("thumbnail")
                var thumbnail: String? = null

                @SerializedName("url")
                var url: String? = null
            }


        }


    }


}
