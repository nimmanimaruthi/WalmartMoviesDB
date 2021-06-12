package com.wmart.moviedb

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog

    fun showProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(getString(R.string.pls_wait))
        progressDialog?.show()
    }

    fun hideProgressDialog() {
        progressDialog?.dismiss()
    }
}