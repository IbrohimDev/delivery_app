package uz.gita.evos_app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.evos_app.R
import uz.gita.evos_app.app.App
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.utils.invisible
import uz.gita.evos_app.utils.visible

class FavouriteAdapter : ListAdapter<FoodData, FavouriteAdapter.Vh>(DiffUtils) {
    private var increaseListener: ((Long) -> Unit)? = null
    private var decreaseListener: ((Long) -> Unit)? = null
    private var itemListener: ((FoodData) -> Unit)? = null

    object DiffUtils : DiffUtil.ItemCallback<FoodData>() {
        override fun areItemsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
            return oldItem == newItem
        }

    }

    inner class Vh(view: View) : RecyclerView.ViewHolder(view) {
        val productImage = itemView.findViewById<ImageView>(R.id.ivProductImage)
        val productName = itemView.findViewById<TextView>(R.id.tvTitle)
        val productCost = itemView.findViewById<TextView>(R.id.tvCost)
        val productMinus = itemView.findViewById<AppCompatButton>(R.id.deleteItem)
        val productAdd = itemView.findViewById<AppCompatButton>(R.id.addItem)
        val countItem = itemView.findViewById<TextView>(R.id.countItem)
        val checkLayout = itemView.findViewById<LinearLayoutCompat>(R.id.check_layout)
        val addBtn = itemView.findViewById<AppCompatButton>(R.id.add_btn)

        init {
            addBtn.setOnClickListener {
                checkLayout.visible()
                addBtn.invisible()
                increaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
            productAdd.setOnClickListener {
                increaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
            productMinus.setOnClickListener {
                decreaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
            itemView.setOnClickListener {
                itemListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            if (data.count == 0L) {
                checkLayout.invisible()
                addBtn.visible()
            } else {
                checkLayout.visible()
                addBtn.invisible()
            }
            Glide.with(App.instance)
                .load(data.imageURL)
                .into(productImage)
            productName.text = data.name
            productCost.text = "${data.cost} so'm"
            countItem.text = "${data.count}x"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
    )

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind()
    }

    fun setItemClickListener(block: (FoodData) -> Unit) {
        itemListener = block
    }

    fun setDecreaseItemClickListener(block: (Long) -> Unit) {
        decreaseListener = block
    }

    fun setIncreaseItemClickListener(block: (Long) -> Unit) {
        increaseListener = block
    }
}