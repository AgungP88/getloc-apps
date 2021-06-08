package com.cals.getloc.utils

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle

class LifeCycleHandler (private val lifeCycleDelegate: LifeCycleDelegate) :
    Application.ActivityLifecycleCallbacks,
    ComponentCallbacks2 {

    private var appInForeground = false

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        if (!appInForeground) {
            appInForeground = true
            lifeCycleDelegate.onAppForegrounded()
        }
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
    }

    override fun onLowMemory() {}

    override fun onConfigurationChanged(newConfig: Configuration) {}

    override fun onTrimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            appInForeground = false
            lifeCycleDelegate.onAppBackgrounded()
        }
    }

}

interface LifeCycleDelegate {
    fun onAppBackgrounded()
    fun onAppForegrounded()
}