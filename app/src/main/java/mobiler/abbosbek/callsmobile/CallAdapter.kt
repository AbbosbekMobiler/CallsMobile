package mobiler.abbosbek.callsmobile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobiler.abbosbek.callsmobile.databinding.ItemCallBinding

/**
 * Created by Alisher Kazakbaev on 15.06.2022.
 */
class CallAdapter(val activity: MainActivity) : RecyclerView.Adapter<CallAdapter.CallViewHolder>() {

    inner class CallViewHolder(private val binding: ItemCallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(call: Call) {
            Log.d("tekshiriish", "$call")
            binding.apply {
                phoneNumber.text = "Phone: ${call.phone}"
                callType.text = "CallType: ${call.callType}"
                callDate.text = "Date: ${call.date}"
                callDuration.text = "Duration: ${call.duration}"
            }
        }
    }

    private val models = mutableListOf<Call>()

    fun setData(calls: List<Call>) {

        models.addAll(calls)
        notifyDataSetChanged()
        val dates = Array<ArrayList<Call>>(24) { arrayListOf() }
        calls.forEach {
            dates[it.date.hours].add(it)
        }

        dates.sortBy { it.size }
        val hour = dates[0][0].date.hours
        val number = dates[0][0].phone

        activity.maxCountCalls.postValue("${hour-1}:00-${hour}:00,$number")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        val binding = ItemCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        Log.d("tekshiri", "onBindViewHolder: $position")
        holder.bind(models[position])
    }

    override fun getItemCount() = models.size
}