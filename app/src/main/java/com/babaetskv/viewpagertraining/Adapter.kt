package com.babaetskv.viewpagertraining

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.babaetskv.viewpagertraining.models.Item

/**
 * @author babaetskv on 27.08.19
 */
class Adapter(context: Context, private val items: List<Item>, private val visibleItemsCount: Int) : PagerAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount() = items.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.card_item, container, false)
        bindItem(view, items[position])
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private fun bindItem(view: View, item: Item) {
        val titleView = view.findViewById<TextView>(R.id.item_cluster_text)
        titleView.text = item.title
    }

    override fun getPageWidth(position: Int): Float {
        return 1f / visibleItemsCount
    }
}
