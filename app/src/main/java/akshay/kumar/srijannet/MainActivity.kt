package akshay.kumar.srijannet

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var flow: Flow<Int>
    var _passwordText: EditText? = null
    var _loginButton: Button? = null
    var _usernameText:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFlow()
        setupClicks()
        var _loginButton = findViewById(R.id.button) as Button
        var _usernameText = findViewById(R.id.et_user_name) as EditText
        var  _passwordText = findViewById(R.id.et_password) as EditText

        _loginButton!!.setOnClickListener { login() }

    }

    private fun login() {
        Log.d(TAG, "Login")

        if (!validate()) {
            onLoginFailed()
            return

        }
        _loginButton!!.isEnabled = false
        val progressDialog = ProgressDialog(this@MainActivity,
            R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Login...")
        progressDialog.show()

        val email = _usernameText!!.text.toString()
        val password = _passwordText!!.text.toString()
        android.os.Handler().postDelayed(
            {
                // On complete call either onLoginSuccess or onLoginFailed
                onLoginSuccess()
                // onLoginFailed();
                progressDialog.dismiss()
            }, 3000)
    }

    private fun onLoginSuccess() {
        _loginButton!!.isEnabled = true
        finish()
        startActivity(Intent(this, StartActivity::class.java))
    }
    fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()

        _loginButton!!.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true

        val email = _usernameText!!.text.toString()
        val password = _passwordText!!.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _usernameText!!.error = "Enter proper user name"
            valid = false
        } else {
            _usernameText!!.error = null
        }

        if (password.isEmpty() || password.length < 5 ||!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ) {
            _passwordText!!.error = "Password not meeting the criteria"
            valid = false
        } else {
            _passwordText!!.error = null
        }

        return valid
    }
    private fun setupClicks() {

    }

    private fun setupFlow() {
        flow = flow {
            Log.d(TAG, "Start flow")
            (0..10).forEach {
                // Emit items with 500 milliseconds delay
                delay(500)
                Log.d(TAG, "Emitting $it")
                emit(it)
            }
        }.map {
            it * it
        }.flowOn(Dispatchers.Default)
    }
    companion object {
        private val TAG = "LoginActivity"

    }
}



