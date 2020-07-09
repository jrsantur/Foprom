package pe.juabsa.cipca.foprom.ui.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pe.juabsa.cipca.foprom.ui.home.client.detail.GeneralDataFragment
import pe.juabsa.cipca.foprom.ui.home.client.detail.HousingDataFragment
import pe.juabsa.cipca.foprom.ui.home.client.detail.LaborDataFragment
import pe.juabsa.cipca.foprom.ui.home.client.detail.SpouseInformationFragment

class FragmentAdapter(fm: FragmentManager? ) : FragmentPagerAdapter(fm!!, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var mFragmentList: MutableList<Fragment> = ArrayList()
    private var mFragmentTitleList: MutableList<String> = ArrayList()

    fun setListFragment(fragments: MutableList<Fragment>){
        this.mFragmentList = fragments
    }

    fun setListTitle(titles: MutableList<String>){
        this.mFragmentTitleList = titles
    }

    override fun getItem(position: Int): Fragment {

        var fragment = Fragment()

        when(position){
            0 -> {
                fragment = GeneralDataFragment().newInstance(position + 1, position == getCount() - 1) as Fragment
            }

            1 -> {
                fragment = HousingDataFragment().newInstance(position + 1, position == getCount() - 1) as Fragment
            }

            2 -> {
                fragment = LaborDataFragment().newInstance(position + 1, position == getCount() - 1) as Fragment
            }
            3 -> {
                fragment =  SpouseInformationFragment().newInstance(position + 1, position == getCount() - 1) as Fragment
            }
        }

        return  fragment

    }

    override fun getCount(): Int {
        return 4
    }

    fun addListFragments(framents: MutableList<Fragment>){
        mFragmentList = framents
    }


    fun addFragment(fragment: Fragment, title: String, position: Int) {
        mFragmentList.add(position, fragment)
        mFragmentTitleList.add(position, title)
    }

    fun removeFragment(fragment: Fragment?, position: Int) {
        mFragmentList.removeAt(position)
        mFragmentTitleList.removeAt(position)

    }
    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }


}