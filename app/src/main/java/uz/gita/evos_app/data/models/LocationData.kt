package uz.gita.evos_app.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(
    val id: Long,
    val name: String,
    val description: String,
    val time: String,
    val latitude: Double,
    val longitude: Double,
    val type: Long
):Parcelable

enum class LocationEnum(val pos: Int) {
    TASHKENT(1),
    FARGONA(2),
    QASHQADARYO(3),
    ANDIJON(4),
    QOQON(5),
    NAMANGAN(6),
    SAMARQAND(7)
}


