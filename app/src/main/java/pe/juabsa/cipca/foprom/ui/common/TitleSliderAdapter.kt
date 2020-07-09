package pe.juabsa.cipca.foprom.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.title_slider.view.*
import pe.juabsa.cipca.foprom.R

class TitleSliderAdapter (private val context: Context) : PagerAdapter() {

    private var inflater: LayoutInflater? = null
    private val titles = arrayOf(R.string.general_data, R.string.housing_data, R.string.labor_data, R.string.spouse_data)


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.title_slider, null)
        view.btnTitle.text = context.resources.getText(titles[position])
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}