package com.babaetskv.viewpagertraining

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.babaetskv.viewpagertraining.models.Item

class MainActivity : AppCompatActivity() {
    private var visibleItemsCount: Int = 1
    private var containerHeight: Int = 0

    private lateinit var itemViewPager: ViewPager
    private var adapter: Adapter? = null

    private val items = listOf(Item("title1"), Item("title2"), Item("title3"), Item("title4"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculateDims()

        val container = findViewById<RelativeLayout>(R.id.items_container)
        container.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, containerHeight)

        itemViewPager = findViewById(R.id.view_pager)
        adapter = Adapter(this, items, visibleItemsCount)
        itemViewPager.adapter = adapter
        val paddingH = resources.getDimensionPixelOffset(R.dimen.viewpager_padding_horizontal)
        itemViewPager.setPadding(paddingH, 0, paddingH, 0)
        if (items.size < visibleItemsCount)
            itemViewPager.setOnTouchListener { v, event -> true }
        else
            itemViewPager.setOnTouchListener(null)
    }

    private fun calculateDims() {
        val screenWidth = Utils.getDisplaySize(this).x
        val containerPadding = resources.getDimensionPixelOffset(R.dimen.viewpager_padding_horizontal)
        var itemWidth = resources.getDimensionPixelSize(R.dimen.item_width)

        visibleItemsCount = Math.round((screenWidth.toDouble() - 2*containerPadding) / itemWidth).toInt()
        itemWidth = Math.ceil((screenWidth.toDouble() - 2*containerPadding) / visibleItemsCount).toInt()
        containerHeight = (itemWidth.toDouble() / 1.23).toInt()
    }
}
