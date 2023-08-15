package com.example.duyanhapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextA: EditText
    private lateinit var editTextB: EditText
    private lateinit var labelC: TextView
    private lateinit var labelD: TextView
    private lateinit var buttonE: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextA = findViewById(R.id.editTextA)
        editTextB = findViewById(R.id.editTextB)
        labelC = findViewById(R.id.labelC)
        labelD = findViewById(R.id.labelD)
        buttonE = findViewById(R.id.buttonE)

        buttonE.setOnClickListener {
            // Kiểm tra điều kiện nhập:
            if (validateInput()) {
                val inputArray = editTextA.text.toString().split(",").map { it.toInt() }
                val searchNumber = editTextB.text.toString().toInt()

                // Tìm kiếm số trong dãy:
                val startTime = SystemClock.elapsedRealtime()
                val result = searchNumberInArray(inputArray, searchNumber)
                val endTime = SystemClock.elapsedRealtime()

                // Hiển thị kết quả và thời gian xử lý:
                labelC.text = result
                labelD.text = "Thời gian xử lý: ${endTime - startTime} milliseconds"
            }
        }
    }

    private fun validateInput(): Boolean {
        val inputArray = editTextA.text.toString().split(",")
        val searchNumber = editTextB.text.toString()

        // Kiểm tra dãy số:
        for (number in inputArray) {
            if (!number.matches(Regex("-?\\d+"))) {
                editTextA.error = "Dãy số không hợp lệ"
                return false
            }
        }

        // Kiểm tra số kiểm tra:
        if (!searchNumber.matches(Regex("-?\\d+"))) {
            editTextB.error = "Số kiểm tra không hợp lệ"
            return false
        }

        return true
    }

    private fun searchNumberInArray(array: List<Int>, number: Int): String {
        val index = array.indexOf(number)
        return if (index >= 0) {
            "Tìm thấy số $number ở vị trí: $index"
        } else {
            "Không tìm thấy số $number trong dãy"
        }
    }
}