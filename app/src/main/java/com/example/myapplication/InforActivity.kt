package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityInforBinding
import com.example.myapplication.databinding.ActivityMainBinding

class InforActivity : AppCompatActivity() {
    lateinit var binding: ActivityInforBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInforBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>("key")
        binding.tvName.text = user?.name
        binding.tvDiemLy.text = user?.ly.toString()
        binding.tvDiemHoa.text = user?.hoa.toString()
        binding.tvDiemToan.text = user?.toan.toString()
        binding.uutien.text = user?.uutien

        binding.btnBACK.setOnClickListener {
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}