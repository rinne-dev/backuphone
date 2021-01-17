package dev.rinne.backuphone.sender

import android.telephony.SmsMessage
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import dev.rinne.backuphone.App
import dev.rinne.backuphone.service.PreferenceService
import dev.rinne.backuphone.util.DateUtil
import java.net.URLEncoder

class ServerChan {
    private val preferenceService = PreferenceService()
    private val dateUtil = DateUtil()
    private val requestQueue = Volley.newRequestQueue(App.getContext())

    private val sckey = preferenceService.getString("serverchan_sckey")

    fun sendMessage(sms: SmsMessage) {
        val title = URLEncoder.encode("收到新的信息", "UTF-8")
        val msg = URLEncoder.encode(formatMessage(sms), "UTF-8")
        val url = "https://sc.ftqq.com/$sckey.send?text=$title&desp=$msg"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                Log.d("OK", it.toString())
            },
            Response.ErrorListener {
                Log.d("ERROR", it.message, it)
            }
        )

        requestQueue.add(request)
    }

    private fun formatMessage(sms: SmsMessage) : String {
        val sender = sms.displayOriginatingAddress
        val message = sms.displayMessageBody
        val date = dateUtil.getDate(sms.timestampMillis)

        var content = "Sender: $sender\n\n"
        content += "Time: $date\n\n"
        content += "Message: $message"

        return content
    }
}
