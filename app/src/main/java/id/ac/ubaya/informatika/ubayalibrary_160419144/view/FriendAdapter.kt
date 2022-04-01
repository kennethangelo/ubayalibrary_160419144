package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.User
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.friends_list_item.view.*

class FriendAdapter(val friendList:ArrayList<User>): RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {
    class FriendViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.friends_list_item, parent, false)

        return FriendViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val user = friendList[position]
        with(holder.view){
            txtFriendName.text = user.name
            txtFriendUsername.text = user.username
            imgFriend.loadImage(user.imgUrl, pbImgFriend, null, null)
            cardFriend.setOnClickListener {
                val action =
                    FriendsFragmentDirections.actionFriendsFragmentToDetailProfileFragment(user.username.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = friendList.size

    fun updateFriendList(newfriendList: List<User>) {
        friendList.clear()
        friendList.addAll(newfriendList)
        notifyDataSetChanged()
    }
}