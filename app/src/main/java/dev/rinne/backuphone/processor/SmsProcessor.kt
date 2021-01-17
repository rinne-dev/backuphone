package dev.rinne.backuphone.processor

import android.telephony.SmsMessage
import android.util.Log
import dev.rinne.backuphone.sender.ServerChan
import dev.rinne.backuphone.sender.WeChatWork
import dev.rinne.backuphone.service.PreferenceService
import dev.rinne.backuphone.util.DateUtil

class SmsProcessor {
    private val preferenceService = PreferenceService()
    private val dateUtil = DateUtil()
    private val serverChan = ServerChan()
    private val wechatWork = WeChatWork()

    fun dispatch(sms : SmsMessage) {
        val sender = sms.displayOriginatingAddress
        val message = sms.displayMessageBody
        val date = dateUtil.getDate(sms.timestampMillis)
        var content = "Sender: $sender\n"
        content += "Time: $date\n"
        content += "Message: $message"

        Log.d("SmsReceived", content)

        val globalSwitch = preferenceService.getBoolean("global_switch")
        if (!globalSwitch) {
            return
        }

        val globalSender = preferenceService.getString("global_sender")
        if (globalSender == "serverchan") {
            serverChan.sendMessage(sms)
        }
        if (globalSender == "wechatwork") {
            wechatWork.sendMessage(sms)
        }
    }
}