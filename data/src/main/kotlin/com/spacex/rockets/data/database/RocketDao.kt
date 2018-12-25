package com.spacex.rockets.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface RocketDao {

    @Insert(onConflict = IGNORE)
    fun insert(country: List<Rocket>): Array<Long>

    @Query("DELETE FROM Rocket")
    fun deleteAll()

    @Query("SELECT * FROM Rocket")
    fun get(): Flowable<List<Rocket>>
}