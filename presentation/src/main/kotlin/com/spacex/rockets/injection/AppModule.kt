package com.spacex.rockets.injection

import android.content.Context
import com.spacex.rockets.data.database.AppDatabase
import com.spacex.rockets.domain.executor.PostExecutionThread
import com.spacex.rockets.domain.executor.ThreadExecutor
import com.spacex.rockets.executor.JobExecutor
import com.spacex.rockets.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val VERSION_APP_NAME = "versionAppName"
    }

    @Provides
    fun provideDatabase(context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    @Named(VERSION_APP_NAME)
    fun provideVersionAppName(context: Context): String {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return pInfo.versionName
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }
}