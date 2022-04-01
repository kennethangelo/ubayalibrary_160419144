package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.FriendViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ProfileViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_review.*

class FriendsFragment : Fragment() {
    private lateinit var viewModel: FriendViewModel
    private val friendAdapter  = FriendAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }
    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Students data
        viewModel.friendLD.observe(viewLifecycleOwner) {
            friendAdapter.updateFriendList(it)
        }

        viewModel.friendLoadErrorLD.observe(viewLifecycleOwner) {
            if (it == true) {
                txtFriendsError.visibility = View.VISIBLE
            } else {
                txtFriendsError.visibility = View.GONE
            }
        }

        viewModel.loadingLD.observe(viewLifecycleOwner) {
            if (it == true) {
                recFriends.visibility = View.GONE
                pbFriends.visibility = View.VISIBLE
            } else {
                recFriends.visibility = View.VISIBLE
                pbFriends.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val username = FriendsFragmentArgs.fromBundle(requireArguments()).username

            viewModel = ViewModelProvider(this).get(FriendViewModel::class.java)
            viewModel.refresh(username)

            val lm = LinearLayoutManager(context)
            recFriends.layoutManager = lm
            recFriends.adapter = friendAdapter

            observeViewModel()
        }
    }
}