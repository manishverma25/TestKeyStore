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
import de.adorsys.android.securestoragelibrary.SecurePreferencesHelper

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
                edit_text_store_key.text.isNullOrEmpty() /*|| edit_text_store_value.text.isNullOrEmpty()*/ ->
                    Toast.makeText(
                        this@MainActivity,
                        "Fields cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                else -> {
                    SecurePreferencesHelper.setLongStringValue(
                        this@MainActivity,
                        edit_text_store_key.text.toString(),
//                        s1
                        jsonData
                        /*edit_text_store_value.text.toString()*/
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
                            SecurePreferencesHelper.getLongStringValue(
                                this@MainActivity,
                                edit_text_get_key.text.toString()
//                                "FAILED"
                            )
                        val comp = decryptedData == jsonData
//                        val always_true = s1 == s2
                        val always_true = jsonData == jsonData2
                        text_view_retrieved_data.text =
                            "Decrypted Data for key: ${edit_text_get_key.text} = $comp    always_true ${always_true} "

                        Log.d("mvv"," key  ${ edit_text_get_key.text}   comp : ${comp}")
                        Log.d("mvv"," key  ${ edit_text_get_key.text}   decryptedData : ${decryptedData}")
//                        Log.d("mvv"," key   Looper.myLooper() ${Looper.myLooper()}   bool  ${Looper.myLooper() == Looper.getMainLooper()}")
                    edit_text_get_key.text.clear()
                    text_view_deleted_data.text = ""
                    text_view_stored_data.text = ""
                    }
                }
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

    companion object{
        const val  s1 = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.Bib8izRlOtZ4k6SMLQG3Zn8Xc2KYKRPpifbOEe-xe7adt87vY_3h4sSMlnyONExddi84nWHlZ8zurJIAOOw6lzbJjOdK6JQTv9OGKY84FRR0fkxNlp0Fqxh3K7NwCZJb0tkEJTBsdVTj3oIS3xcpKhTy_xQlrEnXwPQRKl5qmsUBE6aI826n_4XcdSxxgFxvro2Enuhgok0LLs7d6rb0tsNrtnr_Y86oighaFD-68xNYDo_C6haNwm5jSO6WQciLbAVkg3JtY_E2WbWw36w82wsauEXAMSjgvh1IuuhbrmC7nkVZP8Yd9MUH3hqGG7q_tRtioxRiE1vd6gTYpAlplw.Hlqx60639y8Oinn8.Dt0vt2fwlIyM8UoinRndu4JZnlcd0t2K-pwb6FKkJ6L4Qk_wa6z18c3Twe0soLMaeByL6KvjSdQanVrsVVWGKKKyqXwf0s8nNcUasK_KC56AB5e7vHV68chccjWHnYYtLiPTTZlB_n3nbbyOOAblNYhzIoS6W4vv53m4CiNgYaIONtukSKaD4cr49Tv5nBcVGnJvXndx8KKBFl7ulKAC-TAPfRmFmLhNfMvXO_YAyI7Yn1_W0GTRdDut5jnDpXCqzSjrwsk.wkQC25CcnKr6XjW0rAyctA"

        const val  s2 = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.Bib8izRlOtZ4k6SMLQG3Zn8Xc2KYKRPpifbOEe-xe7adt87vY_3h4sSMlnyONExddi84nWHlZ8zurJIAOOw6lzbJjOdK6JQTv9OGKY84FRR0fkxNlp0Fqxh3K7NwCZJb0tkEJTBsdVTj3oIS3xcpKhTy_xQlrEnXwPQRKl5qmsUBE6aI826n_4XcdSxxgFxvro2Enuhgok0LLs7d6rb0tsNrtnr_Y86oighaFD-68xNYDo_C6haNwm5jSO6WQciLbAVkg3JtY_E2WbWw36w82wsauEXAMSjgvh1IuuhbrmC7nkVZP8Yd9MUH3hqGG7q_tRtioxRiE1vd6gTYpAlplw.Hlqx60639y8Oinn8.Dt0vt2fwlIyM8UoinRndu4JZnlcd0t2K-pwb6FKkJ6L4Qk_wa6z18c3Twe0soLMaeByL6KvjSdQanVrsVVWGKKKyqXwf0s8nNcUasK_KC56AB5e7vHV68chccjWHnYYtLiPTTZlB_n3nbbyOOAblNYhzIoS6W4vv53m4CiNgYaIONtukSKaD4cr49Tv5nBcVGnJvXndx8KKBFl7ulKAC-TAPfRmFmLhNfMvXO_YAyI7Yn1_W0GTRdDut5jnDpXCqzSjrwsk.wkQC25CcnKr6XjW0rAyctA"

    }
}
