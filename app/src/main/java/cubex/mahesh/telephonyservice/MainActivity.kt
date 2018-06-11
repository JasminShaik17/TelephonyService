package cubex.mahesh.telephonyservice

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
        var lview:ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lview = findViewById(R.id.lview)

        var tManager : TelephonyManager =
        getSystemService(Context.TELEPHONY_SERVICE)
        as TelephonyManager

        var list = mutableListOf<String>()
        list.add("IMEI : "+ tManager.imei)
        list.add("Sim Sno : "+ tManager.simSerialNumber)
        list.add("Network Name : "+
                            tManager.simOperatorName)
        list.add("Country : "+
                                tManager.simCountryIso)
    //    list.add("Data is Enabled : "+ tManager.isDataEnabled)
        list.add("Phone Type : "+ tManager.phoneType)

        var adapter = ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_single_choice,list)
        lview?.adapter = adapter

   var cManager =     getSystemService(Context.CONNECTIVITY_SERVICE)
           as ConnectivityManager
    if(cManager.activeNetworkInfo != null)
        list.add(cManager.activeNetworkInfo.isConnected.toString())
    else
        list.add("false")
        adapter.notifyDataSetChanged()
    }
}
