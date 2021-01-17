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
import org.json.JSONObject
import org.json.JSONException

class WeChatWork {
    private val preferenceService = PreferenceService()
    private val dateUtil = DateUtil()
    private val requestQueue = Volley.newRequestQueue(App.getContext())

    private val corpId = preferenceService.getString("wechatwork_corp_id")
    private val secret = preferenceService.getString("wechatwork_secret")
    private val agentId = preferenceService.getString("wechatwork_agent_id")

    fun sendMessage(sms: SmsMessage) {
        val msg = formatMessage(sms)
        val url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=$corpId&corpsecret=$secret"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                try {
                    val token = it.getString("access_token")
                    send(token, msg)
                } catch (e: JSONException) {
                    Log.d("Error", "JSON: " + e.message)
                }
            },
            Response.ErrorListener {
                Log.d("ERROR", it.message, it)
            }
        )

        requestQueue.add(request)
    }

    private fun send(token: String, msg: String) {
        val url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=$token"
        val msgData = JSONObject()
        msgData.put("content", msg)
        val data = JSONObject()
        data.put("touser", "@all")
        data.put("msgtype", "text")
        data.put("agentid", agentId)
        data.put("text", msgData)
        data.put("safe", 0)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
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

        var content = "Sender: $sender\n"
        content += "Time: $date\n"
        content += "Message: $message"

        return content
    }
}
