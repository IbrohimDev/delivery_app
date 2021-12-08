package uz.gita.evos_app.data.shared

import android.content.Context
import com.securepreferences.SecurePreferences
import uz.gita.evos_app.data.enums.StartScreenEnum
import uz.gita.evos_app.utils.startScreen

class MyPrefs(context: Context) {
    private val prefs = SecurePreferences(context,"MyPrefs","ibrohim")

    var startScreen:StartScreenEnum
    get() = prefs.getString("startScreen",StartScreenEnum.LOGIN.name)?.startScreen()!!
    set(value) = prefs.edit().putString("startScreen",value.name).apply()
}