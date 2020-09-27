package com.corp.luqman.kotlintraining.lesson6n7.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.corp.luqman.kotlintraining.databinding.ListGridSleepNightBinding
import com.corp.luqman.kotlintraining.lesson6n7.database.SleepNight
import org.jetbrains.annotations.NotNull

class SleepNightGridAdapter (val clickListener: SleepNightListener) : ListAdapter<SleepNight, SleepNightGridAdapter.ViewHolder>(SleepNightGridDiffCallback()) {

//    var listItem = listOf<SleepNight>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }


//    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position), clickListener)
//        if(item.sleepQuality <= 1){
//            holder.textView.setTextColor(Color.RED)
//        }else{
//            holder.textView.setTextColor(Color.BLACK)
//        }
//        holder.textView.text = item.sleepQuality.toString()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: @NotNull ListGridSleepNightBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: SleepNight,
            clickListener: SleepNightListener
        ) {
            val res = itemView.context.resources
            binding.clickListener = clickListener
            binding.sleep = item
            binding.executePendingBindings()
//            binding.timeSleepNight.text =
//                convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
//            binding.qualitySleepNight.text = convertNumericQualityToString(item.sleepQuality, res)
//            binding.imageQualitySleep.setImageResource(
//                when (item.sleepQuality) {
//                    0 -> R.drawable.ic_sleep_0
//                    1 -> R.drawable.ic_sleep_1
//                    2 -> R.drawable.ic_sleep_2
//                    3 -> R.drawable.ic_sleep_3
//                    4 -> R.drawable.ic_sleep_4
//                    5 -> R.drawable.ic_sleep_5
//                    else -> R.drawable.ic_sleep_active
//                }
//            )

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListGridSleepNightBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }


}

class SleepNightGridDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }
}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}