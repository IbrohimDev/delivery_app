package uz.gita.evos_app.domain.repository.impl

import com.google.firebase.firestore.FirebaseFirestore
import uz.gita.evos_app.data.enums.StartScreenEnum
import uz.gita.evos_app.data.models.AdsData
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.data.models.LocationData
import uz.gita.evos_app.data.shared.MyPrefs
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.utils.myLog
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val prefs: MyPrefs,
    private val firestore: FirebaseFirestore
) : AppRepository {
    override val adsData = ArrayList<AdsData>()

    override val foodsData = ArrayList<FoodData>()
    override val locationData = ArrayList<LocationData>()
    override val favouriteList :List<FoodData> get() = foodsData.filter { it.isSelected }
    override val orderList: List<FoodData> get() = foodsData.filter { it.count > 0 }
    override val orderSum: Long get() = setSumOrder()

    private var successLoadListener: (() -> Unit)? = null

    override fun getStartScreen(): StartScreenEnum = prefs.startScreen

    override fun successLoadListener(block: () -> Unit) {
        successLoadListener = block
    }


    override fun getAds() {
        firestore.collection("ads")
            .get()
            .addOnSuccessListener { result ->
                for (item in result) {
                    val id = item["id"] as Long
                    val imageURL = item["imageURL"] as String
                    adsData.add(AdsData(id, imageURL))
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {
                myLog(it.message.toString())
            }
    }

    override fun getAllFoods() {
        firestore.collection("foods")
            .get()
            .addOnSuccessListener { result ->
                for (item in result) {
                    item.data.apply {
                        val id = this["id"] as Long
                        val name = this["name"] as String
                        val cost = this["cost"] as Long
                        val imageURL = this["imageURL"] as String
                        val type = this["type"] as Long
                        val isFavourite = this["isFavourite"] as Boolean
                        foodsData.add(FoodData(id, name, cost, imageURL, type, isFavourite))
                    }
                }
                foodsData.sortBy { it.type }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {
                myLog(it.message.toString())
            }
    }

    override fun getLocationsByType() {
        firestore.collection("locations")
            .get()
            .addOnSuccessListener { result ->
                for (item in result) {
                    item.data.apply {
                        val description = this["description"] as String
                        val id = this["id"] as Long
                        val latitude = this["latitude"] as Double
                        val longitude = this["longitude"] as Double
                        val name = this["name"] as String
                        val time = this["time"] as String
                        val type = this["type"] as Long

                        locationData.add(
                            LocationData(
                                id,
                                name,
                                description,
                                time,
                                latitude,
                                longitude,
                                type
                            )
                        )
                    }
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {
                myLog(it.message.toString())
            }
    }

    override fun locationsByType(type: Long): List<LocationData> {
        val list = ArrayList<LocationData>()
        for (locationDatum in locationData) {
            if (type == locationDatum.type) {
                list.add(locationDatum)
            }
        }
        return list
    }

    override fun increaseCount(id: Long) {
        foodsData.forEach {
            if (it.id == id)
                it.count++
        }

    }

    override fun decreaseCount(id: Long) {
        foodsData.forEach {
            if (it.id == id)
                it.count--
        }

    }

    override fun addFavouriteItem(pos: Long) {
       foodsData.forEach {
           if(it.id == pos){
               it.isSelected = true
           }
       }
    }

    override fun deleteFavouriteItem(pos: Long) {
        foodsData.forEach {
            if(it.id == pos){
                it.isSelected = false
            }
        }
    }

    override fun getCountById(id: Long):Long {
        foodsData.forEach {
            if(it.id == id){
                return it.count
            }
        }
        return 0
    }

    override fun emptyFav() {
        foodsData.forEach {
            if(it.isSelected){
                it.isSelected = false
            }
        }
    }

    override fun setSumOrder():Long {
        var sum :Long= 0
        orderList.forEach {
            if(it.count == 0L){
                sum += it.cost
            }else{
                sum += it.cost * it.count
            }
        }
        return sum
    }
}