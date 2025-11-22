package dev.lchang.appue.presentation.auth

import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import dev.lchang.appue.data.remote.firebase.FirebaseAuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(navController: NavController){
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var acceptTerms by remember { mutableStateOf(false) }
    var showTerms by remember { mutableStateOf(false) }



    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Text("Registro de Usuario", style = MaterialTheme.typography.titleLarge)

        //OutlinedTextField for name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            placeholder = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        //OutlinedTextField for email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            placeholder = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        //OutlinedTextField for password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            placeholder = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        //OutlinedTextField for confirmPassword
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Contraseña") },
            placeholder = { Text("Confirmar Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(16.dp))
        Row ( verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = acceptTerms,
                onCheckedChange = { acceptTerms = it }
            )
            Button(onClick = { showTerms = true }) {
                Text("Acepto los terminos y condiciones")
            }
        }

        //Button for register
        Button(onClick = {
            if(email.isNotBlank()
                && password.isNotBlank()
                && password == confirmPassword){

                CoroutineScope(Dispatchers.Main).launch {
                    val result = FirebaseAuthManager.registerUser(name, email, password)
                    if(result.isSuccess){
                        navController.navigate("login")
                    } else {
                        val error = result.exceptionOrNull()?.message ?: "Error desconocido"
                        //Toast
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }, modifier = Modifier.fillMaxWidth()) {
            Text("Registrar")
        }

        if (showTerms) {
            AlertDialog(
                onDismissRequest = { showTerms = false },
                title = { Text("Términos y Condiciones") },
                text = {
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                loadUrl("https://www.privacypolicies.com/live/85b851ef-004a-4376-9ade-849424cf8af0")
                            }
                        },
                        modifier = Modifier.height(300.dp)
                    )
                },
                confirmButton = {
                    Button(onClick = { showTerms = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }

    }
}