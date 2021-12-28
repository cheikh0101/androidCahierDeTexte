package com.example.mescours1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.mescours1.api.ApiClient
import com.example.mescours1.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {
    lateinit var emailView: TextView;
    lateinit var passwordView: TextView;
    lateinit var loginBtn: AppCompatButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener{
            if (emailView.text.toString() == "" || passwordView.text.toString() == "" ){
                Toast.makeText(
                    this@LoginActivity,
                    "Champs obligatoires ",
                    Toast.LENGTH_LONG
                ).show()
            }else if(passwordView.text.toString().length < 6){
                Toast.makeText(
                    this@LoginActivity,
                    "Login ou mot de passe incorrecte ",
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                this.login(User(emailView.text.toString(), passwordView.text.toString()))
            }
        }
    }

    private fun login(user: User){
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.login(user);
            withContext(Dispatchers.Main) {
                try {
                    if("${response.body()?.message}" == "connexion réussie"){
                        val sharedPreferences: SharedPreferences = getSharedPreferences("user_information", MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()

                        editor.apply{
                            putString("token", response.body()?.access_token.toString())
                            putString("email", response.body()?.email.toString())
                            putString("nom", response.body()?.nom.toString())
                            putString("prenom", response.body()?.prenom.toString())
                            putString("matricule", response.body()?.matricule.toString())
                            putString("telephone", response.body()?.telephone.toString())
                            response.body()?.user_id?.let { putInt("user_id", it.toInt()) }
                        }.apply()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    }else{
                        Toast.makeText(
                            this@LoginActivity,
                            "Login ou mot de passe incorrecte ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: HttpException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }
}