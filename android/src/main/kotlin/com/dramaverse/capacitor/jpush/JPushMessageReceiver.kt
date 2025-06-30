package com.dramaverse.capacitor.jpush

import android.content.Context
import android.util.Log
import cn.jpush.android.api.JPushMessage
import cn.jpush.android.service.JPushMessageReceiver
import com.getcapacitor.JSObject
import org.json.JSONException
import org.json.JSONObject

class JPushMessageReceiver : JPushMessageReceiver() {
    
    companion object {
        private const val TAG = "JPushMessageReceiver"
    }
    
    override fun onRegister(context: Context?, registrationId: String?) {
        super.onRegister(context, registrationId)
        Log.d(TAG, "JPush registration successful, registrationId: $registrationId")
        
        val plugin = JPushPlugin.getInstance()
        if (plugin != null && registrationId != null) {
            val data = JSObject()
            data.put("registrationId", registrationId)
            plugin.notifyJSEvent("jpushRegistrationId", data)
        }
    }
    
    override fun onMessage(context: Context?, customMessage: cn.jpush.android.api.CustomMessage?) {
        super.onMessage(context, customMessage)
        Log.d(TAG, "Received custom message: ${customMessage?.message}")
        
        val plugin = JPushPlugin.getInstance()
        if (plugin != null && customMessage != null) {
            val data = JSObject()
            data.put("messageId", customMessage.messageId ?: "")
            data.put("title", customMessage.title ?: "")
            data.put("message", customMessage.message ?: "")
            data.put("extra", customMessage.extra ?: "")
            data.put("contentType", customMessage.contentType ?: "")
            
            plugin.notifyJSEvent("jpushMessageReceived", data)
        }
    }
    
    override fun onNotifyMessageArrived(context: Context?, message: cn.jpush.android.api.NotificationMessage?) {
        super.onNotifyMessageArrived(context, message)
        Log.d(TAG, "Notification message arrived: ${message?.notificationContent}")
        
        val plugin = JPushPlugin.getInstance()
        if (plugin != null && message != null) {
            val data = JSObject()
            data.put("messageId", message.msgId ?: "")
            data.put("title", message.notificationTitle ?: "")
            data.put("content", message.notificationContent ?: "")
            data.put("extra", message.notificationExtras ?: "")
            data.put("notificationId", message.notificationId)
            
            plugin.notifyJSEvent("jpushNotificationReceived", data)
        }
    }
    
    override fun onNotifyMessageOpened(context: Context?, message: cn.jpush.android.api.NotificationMessage?) {
        super.onNotifyMessageOpened(context, message)
        Log.d(TAG, "Notification message opened: ${message?.notificationContent}")
        
        val plugin = JPushPlugin.getInstance()
        if (plugin != null && message != null) {
            val data = JSObject()
            data.put("messageId", message.msgId ?: "")
            data.put("title", message.notificationTitle ?: "")
            data.put("content", message.notificationContent ?: "")
            data.put("extra", message.notificationExtras ?: "")
            data.put("notificationId", message.notificationId)
            
            plugin.notifyJSEvent("jpushNotificationOpened", data)
        }
    }
    
    override fun onConnected(context: Context?, isConnected: Boolean) {
        super.onConnected(context, isConnected)
        Log.d(TAG, "JPush connection status: $isConnected")
        
        val plugin = JPushPlugin.getInstance()
        if (plugin != null) {
            val data = JSObject()
            data.put("isConnected", isConnected)
            plugin.notifyJSEvent("jpushConnectionChanged", data)
        }
    }
    
    override fun onTagOperatorResult(context: Context?, jPushMessage: JPushMessage?) {
        super.onTagOperatorResult(context, jPushMessage)
        Log.d(TAG, "Tag operation result: ${jPushMessage?.errorCode}")
        
        val plugin = JPushPlugin.getInstance()
        if (plugin != null && jPushMessage != null) {
            val data = JSObject()
            data.put("sequence", jPushMessage.sequence)
            data.put("errorCode", jPushMessage.errorCode)
            data.put("tags", jPushMessage.tags?.joinToString(",") ?: "")
            
            plugin.notifyJSEvent("jpushTagOperationResult", data)
        }
    }
    
    override fun onAliasOperatorResult(context: Context?, jPushMessage: JPushMessage?) {
        super.onAliasOperatorResult(context, jPushMessage)
        Log.d(TAG, "Alias operation result: ${jPushMessage?.errorCode}")
        
        val plugin = JPushPlugin.getInstance()
        if (plugin != null && jPushMessage != null) {
            val data = JSObject()
            data.put("sequence", jPushMessage.sequence)
            data.put("errorCode", jPushMessage.errorCode)
            data.put("alias", jPushMessage.alias ?: "")
            
            plugin.notifyJSEvent("jpushAliasOperationResult", data)
        }
    }
    
    override fun onMobileNumberOperatorResult(context: Context?, jPushMessage: JPushMessage?) {
        super.onMobileNumberOperatorResult(context, jPushMessage)
        Log.d(TAG, "Mobile number operation result: ${jPushMessage?.errorCode}")
        
        val plugin = JPushPlugin.getInstance()
        if (plugin != null && jPushMessage != null) {
            val data = JSObject()
            data.put("sequence", jPushMessage.sequence)
            data.put("errorCode", jPushMessage.errorCode)
            data.put("mobileNumber", jPushMessage.mobileNumber ?: "")
            
            plugin.notifyJSEvent("jpushMobileNumberOperationResult", data)
        }
    }
}
