package uz.gita.evos_app.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.evos_app.app.App
import uz.gita.evos_app.data.shared.MyPrefs
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getSharedPref() = MyPrefs(App.instance)

    @Provides
    @Singleton
    fun getFirebaseFirestore() = Firebase.firestore

}