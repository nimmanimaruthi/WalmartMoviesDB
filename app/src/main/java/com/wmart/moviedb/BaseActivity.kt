package com.wmart.moviedb

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    var alertDialog: AlertDialog? = null

    fun showProgressDialog() {
        val builder = AlertDialog.Builder(this)
            .setView(R.layout.progress_layout)
        builder?.setMessage(getString(R.string.pls_wait))
        alertDialog = builder.create()
        alertDialog?.show()
    }

    fun hideProgressDialog() {
        alertDialog?.dismiss()
    }
}