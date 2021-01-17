package dev.rinne.backuphone.listener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import dev.rinne.backuphone.processor.SmsProcessor

class SmsListener : BroadcastReceiver() {
    private val smsReceived = "android.provider.Telephony.SMS_RECEIVED"
    private val smsProcessor = SmsProcessor()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(smsReceived)) {
            val bundle = intent.extras
            if (bundle != null) {
                val messages: Array<SmsMessage> = Telephony.Sms.Intents.getMessagesFromIntent(intent)
                for (i in messages.indices) {
                    smsProcessor.dispatch(messages[i])
                }
            }
        }
    }
}
