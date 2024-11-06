package project.c14220332.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var data = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initial data
        data.addAll(listOf("1", "2", "3", "4", "5"))

        // Set up ListView and Adapter
        val listView = findViewById<ListView>(R.id.lv1)
        val lvAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        listView.adapter = lvAdapter

        // Button to add item
        val btnTambah = findViewById<Button>(R.id.btnTambah)
        btnTambah.setOnClickListener {
            val dtAkhir = data.last().toInt() + 1
            data.add(dtAkhir.toString())
            lvAdapter.notifyDataSetChanged()
        }

        // Button to remove first item
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        btnHapus.setOnClickListener {
            if (data.isNotEmpty()) {
                data.removeFirst()
                lvAdapter.notifyDataSetChanged()
            }
        }

        // Item click listener for ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "${data[position]}", Toast.LENGTH_SHORT).show()
        }

        // Search functionality
        val searchView = findViewById<SearchView>(R.id.searchw)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lvAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lvAdapter.filter.filter(newText)
                return false
            }
        })
    }
}
