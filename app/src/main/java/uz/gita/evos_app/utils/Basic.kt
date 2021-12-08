package uz.gita.evos_app.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import uz.gita.evos_app.data.enums.StartScreenEnum

fun <T : ViewBinding> T.scope(block: T.() -> Unit) {
    block(this)
}

fun myLog(message: String, tag: String = "TTT") {
    Log.d(tag, message)
}

fun String.startScreen(): StartScreenEnum {
    return if(this == StartScreenEnum.LOGIN.name) StartScreenEnum.LOGIN
    else StartScreenEnum.MAIN
}


fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message,duration)
}
fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.invisible(){
    this.visibility = View.INVISIBLE
}

