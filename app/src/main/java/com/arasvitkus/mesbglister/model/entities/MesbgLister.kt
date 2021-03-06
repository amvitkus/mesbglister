package com.arasvitkus.mesbglister.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
//Define table name
@Entity(tableName = "army_list_table")
data class MesbgLister (
    //Column in army_list_table
    @ColumnInfo val image: String,
    @ColumnInfo(name = "image_source") val imageSource: String, // image source, from internal, web, etc
    @ColumnInfo val title: String,
    @ColumnInfo val type: String,
    @ColumnInfo val faction: String,
    @ColumnInfo val list: String,
    // Specifies the name of the column in the table to be different from the name of the member variable.
    @ColumnInfo(name = "points") val armyPoints: String,
    @ColumnInfo(name = "notes") val armyNotes: String,
    @ColumnInfo(name = "favorite_army") var favoriteArmy: Boolean = false, //true = 1
    @PrimaryKey(autoGenerate = true) val id: Int = 0 //automatically increment any entry in table. Generate new primary key, unique ID for that specific entry.
): Parcelable //Pass this data class as a string to make use, similar to serializable
