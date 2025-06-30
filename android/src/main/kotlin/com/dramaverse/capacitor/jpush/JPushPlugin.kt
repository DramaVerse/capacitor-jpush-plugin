package com.dramaverse.capacitor.jpush

import android.content.Context
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import cn.jpush.android.api.JPushInterface
import cn.jpush.android.api.TagAliasCallback
import cn.jpush.android.api.JPushMessage
import android.util.Log

@CapacitorPlugin(name = "JPushPlugin")
class JPushPlugin : Plugin() {
    
    companion object {
        private const val TAG = "JPushPlugin"
        private var instance: JPushPlugin? = null
        
        fun getInstance(): JPushPlugin? = instance
    }
    
    override fun load() {
        super.load()
        instance = this
        
        // Initialize JPush
        val context = activity.applicationContext
        JPushInterface.setDebugMode(true) // Set to false in production
        JPushInterface.init(context)
        
        Log.d(TAG, "JPush plugin loaded and initialized")
    }
    
    @PluginMethod
    fun init(call: PluginCall) {
        try {
            val context = activity.applicationContext
            JPushInterface.init(context)
            
            val ret = JSObject()
            ret.put("success", true)
            ret.put("message", "JPush initialized successfully")
            call.resolve(ret)
            
            Log.d(TAG, "JPush init called successfully")
        } catch (e: Exception) {
            Log.e(TAG, "JPush init failed", e)
            call.reject("JPush initialization failed: ${e.message}")
        }
    }
    
    @PluginMethod
    fun getRegistrationID(call: PluginCall) {
        try {
            val context = activity.applicationContext
            val registrationId = JPushInterface.getRegistrationID(context)
            
            val ret = JSObject()
            ret.put("registrationId", registrationId)
            call.resolve(ret)
            
            Log.d(TAG, "Registration ID: $registrationId")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get registration ID", e)
            call.reject("Failed to get registration ID: ${e.message}")
        }
    }
    
    @PluginMethod
    fun setAlias(call: PluginCall) {
        val alias = call.getString("alias")
        if (alias == null) {
            call.reject("Alias is required")
            return
        }
        
        try {
            val context = activity.applicationContext
            JPushInterface.setAlias(context, 1001, alias)
            
            val ret = JSObject()
            ret.put("success", true)
            ret.put("message", "Alias set successfully")
            call.resolve(ret)
            
            Log.d(TAG, "Alias set: $alias")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to set alias", e)
            call.reject("Failed to set alias: ${e.message}")
        }
    }
    
    @PluginMethod
    fun deleteAlias(call: PluginCall) {
        try {
            val context = activity.applicationContext
            JPushInterface.deleteAlias(context, 1002)
            
            val ret = JSObject()
            ret.put("success", true)
            ret.put("message", "Alias deleted successfully")
            call.resolve(ret)
            
            Log.d(TAG, "Alias deleted")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to delete alias", e)
            call.reject("Failed to delete alias: ${e.message}")
        }
    }
    
    @PluginMethod
    fun addTags(call: PluginCall) {
        val tagsArray = call.getArray("tags")
        if (tagsArray == null) {
            call.reject("Tags array is required")
            return
        }
        
        try {
            val tags = mutableSetOf<String>()
            for (i in 0 until tagsArray.length()) {
                tags.add(tagsArray.getString(i))
            }
            
            val context = activity.applicationContext
            JPushInterface.addTags(context, 1003, tags)
            
            val ret = JSObject()
            ret.put("success", true)
            ret.put("message", "Tags added successfully")
            call.resolve(ret)
            
            Log.d(TAG, "Tags added: $tags")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to add tags", e)
            call.reject("Failed to add tags: ${e.message}")
        }
    }
    
    @PluginMethod
    fun deleteTags(call: PluginCall) {
        val tagsArray = call.getArray("tags")
        if (tagsArray == null) {
            call.reject("Tags array is required")
            return
        }
        
        try {
            val tags = mutableSetOf<String>()
            for (i in 0 until tagsArray.length()) {
                tags.add(tagsArray.getString(i))
            }
            
            val context = activity.applicationContext
            JPushInterface.deleteTags(context, 1004, tags)
            
            val ret = JSObject()
            ret.put("success", true)
            ret.put("message", "Tags deleted successfully")
            call.resolve(ret)
            
            Log.d(TAG, "Tags deleted: $tags")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to delete tags", e)
            call.reject("Failed to delete tags: ${e.message}")
        }
    }
    
    @PluginMethod
    fun cleanTags(call: PluginCall) {
        try {
            val context = activity.applicationContext
            JPushInterface.cleanTags(context, 1005)
            
            val ret = JSObject()
            ret.put("success", true)
            ret.put("message", "Tags cleaned successfully")
            call.resolve(ret)
            
            Log.d(TAG, "Tags cleaned")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to clean tags", e)
            call.reject("Failed to clean tags: ${e.message}")
        }
    }
    
    @PluginMethod
    fun setBadge(call: PluginCall) {
        val badgeNumber = call.getInt("badge", 0)
        
        try {
            val context = activity.applicationContext
            JPushInterface.setBadgeNumber(context, badgeNumber)
            
            val ret = JSObject()
            ret.put("success", true)
            ret.put("message", "Badge set successfully")
            call.resolve(ret)
            
            Log.d(TAG, "Badge set to: $badgeNumber")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to set badge", e)
            call.reject("Failed to set badge: ${e.message}")
        }
    }
    
    // Internal method to notify JS layer about events
    fun notifyJSEvent(eventName: String, data: JSObject) {
        notifyListeners(eventName, data)
    }
}
