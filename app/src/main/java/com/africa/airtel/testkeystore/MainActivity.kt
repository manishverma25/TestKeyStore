package com.africa.airtel.testkeystore

import android.os.Bundle
import android.os.Looper
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import de.adorsys.android.securestoragelibrary.SecurePreferences

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//
//            SecurePreferences.setValue(
//                this@MainActivity,
//                "key",
//               "val"
//            )
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_store.setOnClickListener {
            when {
                edit_text_store_key.text.isNullOrEmpty() || edit_text_store_value.text.isNullOrEmpty() ->
                    Toast.makeText(
                        this@MainActivity,
                        "Fields cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                else -> {
                    SecurePreferences.setValue(
                        this@MainActivity,
                        edit_text_store_key.text.toString(),
                        edit_text_store_value.text.toString()
                    )

                    text_view_stored_data.text =
                        "Value: ${edit_text_store_value.text} successfully saved for key: ${edit_text_store_key.text}"

                    edit_text_store_key.text.clear()
                    edit_text_store_value.text.clear()

                    text_view_deleted_data.text = ""
                    text_view_retrieved_data.text = ""
                }
            }
        }

        button_get.setOnClickListener {


            for( i in  1 until 10 ){

                Runnable {

                }
                when {
                    edit_text_get_key.text.isNullOrEmpty() -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Field cannot be empty",
                            Toast.LENGTH_SHORT
                        ).show()

                        Log.d("mvv"," Field cannot be empty")
                    }
                    else -> {
                        val decryptedData =
                            SecurePreferences.getStringValue(
                                this@MainActivity,
                                edit_text_get_key.text.toString(),
                                "FAILED"
                            )
                        text_view_retrieved_data.text =
                            "Decrypted Data for key: ${edit_text_get_key.text} = $decryptedData"

                        Log.d("mvv"," key  ${ edit_text_get_key.text}   decryptedData : ${decryptedData}")
                        Log.d("mvv"," key   Looper.myLooper() ${Looper.myLooper()}   bool  ${Looper.myLooper() == Looper.getMainLooper()}")
//                    edit_text_get_key.text.clear()
//                    text_view_deleted_data.text = ""
//                    text_view_stored_data.text = ""
                    }
                }
                Thread.sleep(11000)
            }//for
        }

        button_remove.setOnClickListener {
            when {
                edit_text_remove_key.text.isNullOrEmpty() -> Toast.makeText(
                    this@MainActivity,
                    "Field cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    SecurePreferences.removeValue(this@MainActivity, edit_text_remove_key.text.toString())

                    text_view_deleted_data.text = "Value for key: ${edit_text_remove_key.text} successfully deleted"

                    edit_text_remove_key.text.clear()
                    text_view_retrieved_data.text = ""
                    text_view_stored_data.text = ""
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
