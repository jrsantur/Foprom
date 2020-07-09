package pe.juabsa.cipca.foprom

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shuhart.stepview.StepView
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import pe.juabsa.cipca.foprom.di.Injectable
import pe.juabsa.cipca.foprom.ui.home.client.detail.*
import javax.inject.Inject


class ClientDetailActivity : AppCompatActivity() , HasSupportFragmentInjector  {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  //@Inject
  //lateinit var viewModelFactory: ViewModelProvider.Factory
  //@Inject
  //lateinit var appExecutors: AppExecutors

  //val clientViewModel: ClientViewModel by viewModels {
  //    viewModelFactory
  //}

  var menu: Menu? = null
  var currentStep:Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //viewModel = this?.run {
    //    ViewModelProviders.of(this)[SharedViewModel::class.java]
    //} ?: throw Exception("Invalid Activity")

    setContentView(R.layout.activity_client_detail)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.elevation = 0F

    val listFragments = mutableListOf<Fragment>(
            GeneralDataFragment().newInstance(1,0 == 4) as Fragment,
            LaborDataFragment().newInstance(2,1 == 4) as Fragment,
            SpouseInformationFragment().newInstance(3,2 == 4) as Fragment,
            InventoryInformationFragment.newInstance(4, 3 == 4) as Fragment,
            FinancialDebtsFragment.newInstance(1, 5, 4 == 4) as Fragment
    )
    var titlesFragments = mutableListOf<String>("Generales","Laborales", "Conyugue", "Bienes", "Deudas" )


    if (currentStep == 0 ) {
      supportFragmentManager.beginTransaction()
              .add(R.id.content_fragments, listFragments.get(currentStep), "dogList")
              .commit()
    }

    val  stepView: StepView = findViewById(R.id.step_view);
    stepView.setSteps(titlesFragments)
    stepView.getState()
            .selectedTextColor(ContextCompat.getColor(this, android.R.color.white))
            .animationType(StepView.ANIMATION_CIRCLE)
            .commit()

    stepView.setOnStepClickListener {
      step -> Toast.makeText(this@ClientDetailActivity, "Step $step", Toast.LENGTH_SHORT).show()
    }

    /*
    clientViewModel.nextpage.observe(this, Observer {
        currentStep = it
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_fragments, listFragments.get(currentStep))
                .commit()
        stepView.go(currentStep,true)

        if (currentStep==4){
            menu!!.findItem(R.id.action_done).isVisible = true
        }

    })

     */
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.client, menu);
    this.menu = menu
    this.menu!!.findItem(R.id.action_done).isVisible = false
    this.menu!!.findItem(R.id.action_search).isVisible = false
    return true
  }


  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  override fun supportFragmentInjector() = dispatchingAndroidInjector


  fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(editable: Editable?) {
        afterTextChanged.invoke(editable.toString())
      }

      override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
  }
}
