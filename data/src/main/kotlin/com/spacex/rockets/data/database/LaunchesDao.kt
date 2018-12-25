package com.spacex.rockets.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface LaunchesDao {

    @Insert(onConflict = IGNORE)
    fun insert(country: List<Launch>): Array<Long>

    @Query("DELETE FROM Launch")
    fun deleteAll()

    @Query("SELECT * FROM Launch where rocketId = :rocketId")
    fun get(rocketId : String): Flowable<List<Launch>>
}