package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Category
import kotlinx.android.synthetic.main.category_list_item.view.*
import kotlin.random.Random

class DiscoverCategoryAdapter(val categoryList:ArrayList<Category>): RecyclerView.Adapter<DiscoverCategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_list_item, parent, false)

        return CategoryViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val rnd = Random.Default //kotlin.random
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        val category = categoryList[position]
        with(holder.view){
            cardCategory.setBackgroundColor(color)
            txtCategoryName.text = category.name
            cardCategory.setOnClickListener {
                val action = DiscoverFragmentDirections.actionDiscoverToDetailBookListFragment("Category", category.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = categoryList.size

    fun updateCategoryList(newCategoryList: List<Category>) {
        categoryList.clear()
        categoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }
}