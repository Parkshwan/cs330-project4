package com.example.pj4test

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.pj4test.fragment.CameraFragment
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    // permissions
    private val permissions = arrayOf(RECORD_AUDIO, CAMERA)
    private val PERMISSIONS_REQUEST = 0x0000001;

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions() // check permissions
    }

    private fun checkPermissions() {
        if (permissions.all{ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED}){
            Log.d(TAG, "All Permission Granted")
        }
        else{
            requestPermissions(permissions, PERMISSIONS_REQUEST)
        }
    }

    fun changeCameraFragment(index: Int){
        when(index){
            1 -> {
                val camera = supportFragmentManager.findFragmentByTag("Camera")
                if(camera != null) {
                    Log.d("ChangeFragment", "REMOVING START")
                    supportFragmentManager
                        .beginTransaction()
                        .hide(camera)
                        .commit()
                }
            }

            2 -> {
                val camera = supportFragmentManager.findFragmentByTag("Camera")
                if(camera != null) {
                    Log.d("ChangeFragment", "Show")
                    supportFragmentManager
                        .beginTransaction()
                        .show(camera)
                        .commit()
                }
                else{
                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.cameraFragmentContainerView, CameraFragment(), "Camera")
                        .commit()
                }

            }
        }
    }
}