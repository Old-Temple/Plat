package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_plat_list.view.*

/**
 * A simple [Fragment] subclass.
 * 메인화면
 */
class MainFragment : Fragment() {
    //임시로 만든 arraylist
    private val myList = arrayListOf<PlatListItem>(
        PlatListItem(1),
        PlatListItem(2),
        PlatListItem(3),
        PlatListItem(4),
        PlatListItem(5),
        PlatListItem(6),
        PlatListItem(7),
        PlatListItem(8),
        PlatListItem(9),
        PlatListItem(10),
        PlatListItem(11),
        PlatListItem(12),
        PlatListItem(13),
        PlatListItem(14),
        PlatListItem(15)
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main, null)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val platlistView = view.findViewById<RecyclerView>(R.id.platListView)
        platlistView.layoutManager = layoutManager
        val platListAdapter = PlatListAdapter(myList)

        platlistView.adapter = platListAdapter

        return view

    }

}

class PlatListItem(val num: Int)

class PlatListAdapter(val items: ArrayList<PlatListItem>): RecyclerView.Adapter<PlatListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatListAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_plat_list, parent, false)

        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlatListAdapter.ViewHolder, position: Int) {
        val num = items[position]

        holder.bind(num)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private var v: View) : RecyclerView.ViewHolder(v){
        fun bind(item: PlatListItem){
            v.item_plat_list.text = item.num.toString()
        }
    }
}