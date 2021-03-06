package com.arasvitkus.mesbglister.view.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.arasvitkus.mesbglister.R
import com.arasvitkus.mesbglister.databinding.ItemArmyLayoutBinding
import com.arasvitkus.mesbglister.model.entities.MesbgLister
import com.arasvitkus.mesbglister.utils.Constants
import com.arasvitkus.mesbglister.view.activities.AddUpdateListActivity
import com.arasvitkus.mesbglister.view.fragments.AllArmiesFragment
import com.arasvitkus.mesbglister.view.fragments.FavoriteArmiesFragment
import com.bumptech.glide.Glide

class MesbgListerAdapter(private val fragment: Fragment): RecyclerView.Adapter<MesbgListerAdapter.ViewHolder>() {

    private var armies: List<MesbgLister> = listOf()

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArmyLayoutBinding =
            ItemArmyLayoutBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. Either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val army = armies[position]

        // Load the army image in the ImageView.
        Glide.with(fragment)
            .load(army.image)
            .into(holder.ivArmyImage)

        holder.tvTitle.text = army.title

        holder.itemView.setOnClickListener {
            if(fragment is AllArmiesFragment){
                fragment.armyDetails(army)
            }
            if(fragment is FavoriteArmiesFragment){
                fragment.armyDetails(army)
            }
        }

        holder.ibMore.setOnClickListener {
            val popup = PopupMenu(fragment.context, holder.ibMore)
            //Inflating the Popup using xml file.
            popup.menuInflater.inflate(R.menu.menu_adapter, popup.menu)

            popup.setOnMenuItemClickListener {
                if(it.itemId == R.id.action_edit_army){
                    val intent = Intent(fragment.requireActivity(), AddUpdateListActivity::class.java)
                    intent.putExtra(Constants.EXTRA_ARMY_DETAILS, army)
                    fragment.requireActivity().startActivity(intent)

                } else if(it.itemId == R.id.action_delete_army){
                    if(fragment is AllArmiesFragment){
                        fragment.deleteArmy(army)
                    }
                }
                true
            }

            popup.show() //Showing popup menu
        }

        if(fragment is AllArmiesFragment){
            holder.ibMore.visibility = View.VISIBLE
        }else if (fragment is FavoriteArmiesFragment){
            holder.ibMore.visibility = View.GONE
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return armies.size
    }

    fun armiesList(list: List<MesbgLister>) {
        armies = list
        notifyDataSetChanged()
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: ItemArmyLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        // Holds the TextView that will add each item to
        val ivArmyImage = view.ivArmyImage
        val tvTitle = view.tvArmyTitle
        val ibMore = view.ibMore
    }
}