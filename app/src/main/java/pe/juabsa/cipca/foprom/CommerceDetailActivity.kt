package pe.juabsa.cipca.foprom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.shuhart.stepview.StepView
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import pe.juabsa.cipca.foprom.ui.home.commerce.detail.*
import javax.inject.Inject

class CommerceDetailActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private lateinit var viewModel: SharedViewModel

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var currentStep:Int = 0

    val listFragment = mutableListOf<Int>(
            R.id.businessInventoryFragment,
            R.id.businessInventoryFragment,
            R.id.businessSalesFragment,
            R.id.businessShoppingFragment,
            R.id.businessDebtsFragment,
            R.id.balanceSheetFragment
    )



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        viewModel = this?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        setContentView(R.layout.activity_commerce_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F


        val listFragments = mutableListOf<Fragment>(
                GeneralBusinessDataFragment.newInstance(1, 0==5 ) ,
                BusinessInventoryFragment.newInstance(2, 1==5 ),
                BusinessSalesFragment.newInstance(3, 2==5 ) ,
                BusinessShoppingFragment.newInstance(4,3==5),
                BusinessDebtsFragment.newInstance(5,4==5),
                BalanceSheetFragment.newInstance(6, 5==5)
        )

        val  stepView = findViewById<StepView>(R.id.step_view_commerce)

        var titlesFragments = mutableListOf<String>("Generales","Inventario", "Ventas", "Compras", "Deudas", "Balance" )

        stepView.setSteps(titlesFragments)
        stepView.getState()
                .selectedTextColor(ContextCompat.getColor(this, android.R.color.white))
                .animationType(StepView.ANIMATION_CIRCLE)
                .commit()

        stepView.setOnStepClickListener {
            step -> Toast.makeText(this@CommerceDetailActivity, "Step $step", Toast.LENGTH_SHORT).show()
        }

        viewModel.nextpage.observe(this, Observer {
            currentStep = it
            findNavController(R.id.content_fragments_commerce).navigate(listFragment.get(currentStep))
            stepView.go(currentStep,true)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        //currentStep--
        //if (currentStep>0){
        //    findNavController(R.id.content_fragments_commerce).navigate(listFragment.get(currentStep))
        //    step_view_commerce.go(currentStep,true)
        //}
        onBackPressed()
        return true
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
