package uz.gita.evos_app.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import uz.gita.evos_app.R
import uz.gita.evos_app.app.App
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.utils.invisible
import uz.gita.evos_app.utils.visible

class FoodsAdapter : ListAdapter<FoodData, FoodsAdapter.Vh>(DiffUtils) {
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
        val imageFood = itemView.findViewById<RoundedImageView>(R.id.roundedImageViewFood)
        val nameFood = itemView.findViewById<TextView>(R.id.textViewName)
        val costFood = itemView.findViewById<TextView>(R.id.textViewCost)
        val btnAdd = itemView.findViewById<AppCompatButton>(R.id.buttonAdd)
        val plusBtn = itemView.findViewById<AppCompatButton>(R.id.imagePlus)
        val minusBtn = itemView.findViewById<AppCompatButton>(R.id.imageMinus)
        val counterTxt = itemView.findViewById<TextView>(R.id.textViewCounter)
        init {
            itemView.setOnClickListener {
                itemListener?.invoke(getItem(absoluteAdapterPosition))
            }
            btnAdd.setOnClickListener {
                btnAdd.invisible()
                plusBtn.visible()
                minusBtn.visible()
                counterTxt.visible()
                increaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
            plusBtn.setOnClickListener {
                increaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
            minusBtn.setOnClickListener {
                decreaseListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
        }
        fun bind() {
          val data = getItem(absoluteAdapterPosition)
            Glide.with(App.instance)
                .load(data.imageURL)
                .into(imageFood)

            if(data.count == 0L){
                btnAdd.visible()
                plusBtn.invisible()
                minusBtn.invisible()
                counterTxt.invisible()
            }else{
                btnAdd.invisible()
                plusBtn.visible()
                minusBtn.visible()
                counterTxt.visible()
            }
            nameFood.text = data.name
            costFood.text = "${data.cost} so'm"
            counterTxt.text = "${data.count}x"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_food, parent, false
        )
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