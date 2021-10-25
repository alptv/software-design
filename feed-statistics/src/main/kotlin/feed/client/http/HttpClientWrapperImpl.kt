package feed.client.http

import okhttp3.OkHttpClient
import okhttp3.Request

class HttpClientWrapperImpl(
    private val client: OkHttpClient = OkHttpClient()
) : HttpClientWrapper {

    companion object {
        const val OK_STATUS_CODE = 200
    }

    override fun request(url: String): String {
        try {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use {
                if (it.code == OK_STATUS_CODE) {
                    return it.body?.string()!!
                }
            }
        } catch (e: Exception) {
            throw HttpException("Error during http request", e)
        }
        throw HttpException("Http status code is not OK")
    }


}