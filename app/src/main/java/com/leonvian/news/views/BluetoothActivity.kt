package com.leonvian.news.views

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.ParcelUuid
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.lvc.newsapp.R
import kotlinx.android.synthetic.main.ble_layouts.*


/**
 * private BluetoothAdapter mBluetoothAdapter;
...
// Initializes Bluetooth adapter.
final BluetoothManager bluetoothManager =
(BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
mBluetoothAdapter = bluetoothManager.getAdapter();
 */

class BluetoothActivity : Activity(){

    companion object {
        const val REQUEST_ENABLE_BT = 10
        const val FILTER_SERVICE = "41690000-7276-697A-466C-747253656E73"
        const val MY_PERMISSIONS_REQUEST_LOCATIONS = 10
    }

    private val scanCallback : ScanCallback by lazy {
        object : ScanCallback() {
            override fun onScanFailed(errorCode: Int) {
                Log.i("MARCH", "onScanFailed = ${errorCode}")
            }

            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                result?.let {
                    val bleDevice = it.device
                    Log.i("MARCH", "onScanResult - Device FOUND - name = ${bleDevice.name} + Adress = ${bleDevice.address} + bondState = ${bleDevice.bondState} + UUIDS = ${bleDevice.uuids} ")
                }
            }

            override fun onBatchScanResults(results: MutableList<ScanResult>?) {
                Log.i("MARCH", "onBatchScanResults")
            }
        }
    }

    private val bluetoothAdapter : BluetoothAdapter by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ble_layouts)

        grantMyPermissions()

        buttonGo.setOnClickListener { view -> scanLeDevice() }
        buttonCancel.setOnClickListener { view -> stopScanDevice()}
    }

    fun stopScanDevice() {
        bluetoothAdapter.bluetoothLeScanner.stopScan(scanCallback)
    }

    fun scanLeDevice() {
        Log.i("MARCH", "scanLeDevice")
        val filter = ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(FILTER_SERVICE)).build()
        val settings = ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build()

        bluetoothAdapter.bluetoothLeScanner.startScan(listOf(filter), settings, scanCallback)
        //bluetoothAdapter.bluetoothLeScanner.startScan(scanCallback)
    }

    /*
    //416900007276697A466C747253656E73
    val filter = ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(BluetoothLeHandler.FILTER_SERVICE)).build()
val settings = ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build()
     */


    fun showMessageToEnableBlueetothIfNecessary() {
        if (bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
    }

    fun grantMyPermissions() {
        when (checkPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)) {
            false -> askPermission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    fun checkPermissions(vararg permissions: String) : Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
              return false
            }
        }

        return true
    }

    fun askPermission(vararg permissions: String) {
        ActivityCompat.requestPermissions(this,
                permissions,
                MY_PERMISSIONS_REQUEST_LOCATIONS)
    }

}

