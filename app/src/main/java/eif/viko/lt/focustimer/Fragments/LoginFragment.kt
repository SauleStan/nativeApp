package eif.viko.lt.focustimer.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import eif.viko.lt.focustimer.Models.Item
import eif.viko.lt.focustimer.ViewModels.TodoListViewModel
import java.util.Random

const val RC_SIGN_IN = 123

class LoginFragment: Fragment(){

    lateinit var todoListViewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connecting viewmodel to the UI to get some data
        todoListViewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
//            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
//            AuthUI.IdpConfig.FacebookBuilder().build(),
//            AuthUI.IdpConfig.TwitterBuilder().build()
        )

// Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show()

//                todoListViewModel.addToTodoList(Item(Random().nextLong() ,"Thingie to do", "Yous gots to dos what yous gots to dos", "Sometime"))

            } else {
//                Toast.makeText(context, "requestCode failed (check loginFragment)", Toast.LENGTH_LONG).show()
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                    Toast.makeText(context, "${response?.error?.message}",Toast.LENGTH_LONG).show()
                // ...
            }
        }
    }
}