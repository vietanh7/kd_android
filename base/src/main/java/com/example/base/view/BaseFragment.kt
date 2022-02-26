package com.example.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.base.view.widget.dialog.ProgressViewDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment() : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Any>

    private val parentView: View?
        get() = activity?.findViewById(android.R.id.content)

    private var dialogLoading: ProgressViewDialog? = null

    private var resourceLayout: Int? = null

    constructor(@LayoutRes resourceLayout: Int) : this() {
        this.resourceLayout = resourceLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return childFragmentInjector
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        resourceLayout?.let {
            return LayoutInflater.from(context).inflate(it, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun showDialogLoading() {
        if (dialogLoading == null) {
            dialogLoading = ProgressViewDialog()
        }
        dialogLoading?.showAlert(context)
    }

    protected fun hideDialogLoading() {
        dialogLoading?.dismiss()
    }

    private fun getFragmentName(): String = this.javaClass.simpleName
}