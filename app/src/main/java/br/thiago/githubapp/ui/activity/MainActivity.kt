package br.thiago.githubapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.thiago.githubapp.core.utils.delegates.viewBinding
import br.thiago.githubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(binding.root)


    }
}
