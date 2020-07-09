package pe.juabsa.cipca.foprom

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.*
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.*
import com.google.android.material.navigation.NavigationView
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import pe.juabsa.cipca.foprom.services.DetectedActivitiesIntentService
import pe.juabsa.cipca.foprom.util.Constants
import pe.juabsa.cipca.foprom.util.SharedPrefsHelper
import pe.juabsa.cipca.foprom.vo.User
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector, NavigationView.OnNavigationItemSelectedListener  {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  private lateinit var appBarConfiguration: AppBarConfiguration

  var userId = 0

  private val TAG: String = pe.juabsa.cipca.foprom.HomeActivity::class.java.getSimpleName()

  // Location API
  private var mGoogleApiClient: GoogleApiClient? = null
  private var mLocationRequest: LocationRequest? = null
  private var mLocationSettingsRequest: LocationSettingsRequest? = null
  private var mLastLocation: Location? = null
  private val LOCATION_KEY = "location-key"
  private val ACTIVITY_KEY = "activity-key"
  val BROADCAST_ACTION = "broadcast-action"
  val ACTIVITY_RECOGNITION_INTERVAL: Long = 0
  val UPDATE_INTERVAL: Long = 1000
  val UPDATE_FASTEST_INTERVAL = UPDATE_INTERVAL / 2
  // Activity Recognition API
  var mBroadcastReceiver: ActivityDetectionBroadcastReceiver? = null

  // Códigos de petición
  val REQUEST_LOCATION = 1
  val REQUEST_CHECK_SETTINGS = 2


  val bundle = Bundle()

  override fun onSaveInstanceState(outState: Bundle) {
    // Protegemos la ubicación actual antes del cambio de configuración
    outState.putParcelable(LOCATION_KEY, mLastLocation)
    //outState.putInt(ACTIVITY_KEY, mImageResource)
    super.onSaveInstanceState(outState)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)

    userId  = SharedPrefsHelper.get<User>("KEY_USER")?.userId!!

    val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
    val navView: NavigationView = findViewById(R.id.nav_view)
    val navController = findNavController(R.id.nav_host_fragment)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_solicitude, R.id.nav_client, R.id.nav_commerce, R.id.nav_sync), drawerLayout)

    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

  }

  override fun onNavigationItemSelected(menu: MenuItem): Boolean {

    when(menu.itemId){
      R.id.nav_client ->
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_client , bundleOf("userId" to userId))
    }
    val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
    drawerLayout.closeDrawer(GravityCompat.START)

    return true
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.home, menu)
    return true
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }


  override fun supportFragmentInjector() = dispatchingAndroidInjector





  class ActivityDetectionBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
      val type = intent.getIntExtra(Constants.ACTIVITY_KEY, -1)
      //mImageResource = Constants.getActivityIcon(type)
      //updateRecognitionUI()
    }
  }



  private fun createLocationRequest() {
    mLocationRequest = LocationRequest()
            .setInterval(Constants.UPDATE_INTERVAL)
            .setFastestInterval(Constants.UPDATE_FASTEST_INTERVAL)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
  }

  private fun buildLocationSettingsRequest() {
    val builder = LocationSettingsRequest.Builder()
    builder.addLocationRequest(mLocationRequest!!)
            .setAlwaysShow(true)
    mLocationSettingsRequest = builder.build()
  }


  private fun stopLocationUpdates() {
    //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,getActivityDetectionPendingIntent())
  }

  private fun getLastLocation() {
    if (isLocationPermissionGranted()) {
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return
      }
      mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
    } else {
      manageDeniedPermission()
    }
  }


  private fun manageDeniedPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
      // Aquí muestras confirmación explicativa al usuario
      // por si rechazó los permisos anteriormente
    } else {
      ActivityCompat.requestPermissions(
              this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
              REQUEST_LOCATION)
    }
  }

  private fun isLocationPermissionGranted(): Boolean {
    val permission: Int = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION)
    return permission == PackageManager.PERMISSION_GRANTED
  }


  private fun getActivityDetectionPendingIntent(): PendingIntent? {
    val intent = Intent(this, DetectedActivitiesIntentService::class.java)
    return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
  }


}
