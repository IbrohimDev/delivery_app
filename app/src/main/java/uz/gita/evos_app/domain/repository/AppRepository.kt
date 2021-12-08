package uz.gita.evos_app.domain.repository

import uz.gita.evos_app.data.enums.StartScreenEnum
import uz.gita.evos_app.data.models.AdsData
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.data.models.LocationData

interface AppRepository {
    val adsData: List<AdsData>
    val foodsData : List<FoodData>
    val locationData:List<LocationData>
    val favouriteList:List<FoodData>
    val orderList:List<FoodData>
    val orderSum:Long
    fun getStartScreen(): StartScreenEnum


    fun successLoadListener(block : () -> Unit)
    fun getAds()
    fun getAllFoods()
    fun getLocationsByType()
    fun locationsByType(type:Long):List<LocationData>

    fun increaseCount(id:Long)
    fun decreaseCount(id:Long)
    fun addFavouriteItem(pos : Long)
    fun deleteFavouriteItem(pos : Long)
    fun getCountById(id:Long):Long
    fun emptyFav()
    fun setSumOrder():Long
}