package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentAddArticleBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentLoginBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Book
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Global.isLogin
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Global.username
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.User
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ProfileViewModel

class LoginFragment : Fragment(), LoginClick, RegisterClick {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        return dataBinding.root
        // return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.user = User("","","","","","","")
        dataBinding.loginListener = this
        dataBinding.registerListener = this
    }

    override fun onLoginClick(v: View, u: User) {
        Log.d("user", u.toString())
        viewModel.checkLogin(u.username, u.password)
        if(isLogin == true){
            username = u.username
            Toast.makeText(v.context, "Successfully login", Toast.LENGTH_SHORT).show()
            val action = LoginFragmentDirections.actionLoginFragmentToItemHome()
            Navigation.findNavController(v).navigate(action)
        } else {
            Toast.makeText(v.context, "Failed to login", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRegisterClick(v: View) {
        val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        Navigation.findNavController(v).navigate(action)
    }
}