package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentAddArticleBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.databinding.FragmentSignUpBinding
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.User
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.DetailArticleViewModel
import id.ac.ubaya.informatika.ubayalibrary_160419144.viewmodel.ProfileViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SignUpFragment : Fragment(), CreateUserClick, BackLoginClick {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_up, container, false
        )
        return dataBinding.root
        // return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = now.format(formatter)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.user = User("","","","",formatted,"","")
        dataBinding.loginListener = this
        dataBinding.createListener = this
    }

    override fun onLoginClick(v: View) {
        Navigation.findNavController(v).popBackStack()
    }

    override fun onCreateNewUser(v: View) {
        dataBinding.user?.let{
            val list = listOf(it)
            viewModel.register(list)
            Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
//            val myWorkRequest = OneTimeWorkRequestBuilder<ToDoWorker>()
//                //Notif will show 30 seconds later after work queued
//                .setInitialDelay(diff, TimeUnit.SECONDS)
//                //This is an InputData object that constructed from the key and value pair.
//                //Left -> key, right->value
//                .setInputData(
//                    workDataOf(
//                        "title" to "Todo ${it.title} Created",
//                        "message" to "A new todo has been created! Stay focus!"
//                    )
//                )
//                .build()
//            //Work request is being queued
//            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
            Navigation.findNavController(v).popBackStack()
        }
    }
}