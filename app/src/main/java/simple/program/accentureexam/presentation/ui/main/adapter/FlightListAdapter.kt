package simple.program.accentureexam.presentation.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import simple.program.accentureexam.R
import simple.program.accentureexam.data.cache.model.FlightsDataEntity

class FlightListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FlightsDataEntity>() {

        override fun areItemsTheSame(
            oldItem:FlightsDataEntity,
            newItem: FlightsDataEntity
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
            oldItem: FlightsDataEntity,
            newItem: FlightsDataEntity
        ): Boolean {
            return false
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return FlightListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_flight_list,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FlightListViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<FlightsDataEntity>) {
        differ.submitList(list)
    }

    class FlightListViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView =  itemView.findViewById(R.id.tv_name)
        private val country: TextView =  itemView.findViewById(R.id.tv_country)


        fun bind(item: FlightsDataEntity) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            name.text = item.airportName
            country.text = item.countryName

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: FlightsDataEntity)
    }
}

