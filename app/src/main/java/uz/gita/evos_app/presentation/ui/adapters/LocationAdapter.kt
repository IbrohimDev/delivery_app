package uz.gita.evos_app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.evos_app.R
import uz.gita.evos_app.data.models.LocationData

class LocationAdapter:ListAdapter<LocationData,LocationAdapter.Vh>(DiffUtils) {

    private var clickItemListener: ((LocationData)->(Unit))? = null
    object DiffUtils:DiffUtil.ItemCallback<LocationData>(){
        override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
           return oldItem == newItem
        }

    }

    inner class Vh(view: View):RecyclerView.ViewHolder(view){
        val name = itemView.findViewById<TextView>(R.id.name_loc)
        val desc = itemView.findViewById<TextView>(R.id.desc_loc)
        val time = itemView.findViewById<TextView>(R.id.time_loc)

        init {
            itemView.setOnClickListener {
                clickItemListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }
        fun bind(){
            val data = getItem(absoluteAdapterPosition)
            name.text = data.name
            desc.text = data.description
            time.text = data.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_location,parent,false))

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind()
    }
    fun setClickListener(f:(LocationData)->(Unit)){
        clickItemListener = f
    }
}