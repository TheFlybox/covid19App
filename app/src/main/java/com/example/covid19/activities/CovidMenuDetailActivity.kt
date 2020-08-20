package com.example.covid19.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.covid19.R
import com.example.covid19.data.model.CovidMenu
import kotlinx.android.synthetic.main.activity_covid_menu_detail.*

class CovidMenuDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_menu_detail)
        setSupportActionBar(toolbar2 as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val item = intent.getSerializableExtra("CovidMenu") as CovidMenu
        detail_item_name.text = item.name
        detail_item_description.text = item.description
        detail_item_category.text = item.category
        detail_item_price.text = item.price.toString()
        detail_item_rating.rating = item.rating.toFloat()
        Glide.with(this).load(item.imageURL).into(detail_item_image)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}