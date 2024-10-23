package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: UserDataBase
    var list = mutableListOf<User>()
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { result ->
        intent = result.data
        if(result.resultCode == Activity.RESULT_OK){
            //Xu ly
        }
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = UserDataBase(this)

        binding.btnSubmit.setOnClickListener {
            themDuLieu()
        }
        binding.btnOK.setOnClickListener {
            binding.edtName.setText("")
            binding.edtDiemLy.setText("")
            binding.edtDiemHoa.setText("")
            binding.edtDiemToan.setText("")
            binding.rdbUuTien.isChecked = true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun themDuLieu() {
        val name = binding.edtName.text.toString()
        val toan = binding.edtDiemToan.text.toString()
        val hoa = binding.edtDiemHoa.text.toString()
        val ly = binding.edtDiemLy.text.toString()
        var  uutien = ""
        if(binding.rdbUuTien.isChecked){
             uutien = "Ưu tiên"
        }else if(binding.rdbKhongUuTien.isChecked){
            uutien = "Không ưu tiên"
        }
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(toan)||TextUtils.isEmpty(hoa)||TextUtils.isEmpty(ly)||TextUtils.isEmpty(uutien)){
            Toast.makeText(this, "NHAP DU THONG TIN !", Toast.LENGTH_SHORT).show()
            return
        }
        if(!checkChu(name)){
            Toast.makeText(this, "TEN KHONG DUOC NHAP SO", Toast.LENGTH_SHORT).show()
            return
        }
        if(!checkSo(toan)||!checkSo(hoa)||!checkSo(ly)){
            Toast.makeText(this, "TEN KHONG DUOC NHAP SO", Toast.LENGTH_SHORT).show()
            return
        }

        val toanInt = toan.toInt()
        val hoaInt = hoa.toInt()
        val lyInt = ly.toInt()

        val user = User(name = name, toan = toanInt, hoa = hoaInt, ly = lyInt, uutien = uutien)
        db.insertUser(user)

        intent = Intent(this@MainActivity, InforActivity::class.java)
        intent.putExtra("key", user)
        resultLauncher.launch(intent)

    }
    private fun checkChu(str: String): Boolean{
        return str == str.uppercase()
    }
    private fun checkSo(str: String): Boolean{
        return str.matches("\\d+".toRegex())
    }
}