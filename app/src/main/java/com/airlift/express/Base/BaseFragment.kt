package com.airlift.express.Base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.*


abstract class BaseFragment : Fragment() {

    private var ctx: FragmentActivity? = null

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.ctx = activity
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    protected inline fun <reified T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    fun popStack() {
        hideKeyboard((ctx as Activity?)!!)
        ctx!!.supportFragmentManager.popBackStack()
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
        beginTransaction().apply {
            action()
        }.commit()
    }

    fun Show( fragment: DialogFragment){
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
    /*    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)*/
        fragment.show(transaction, null)
    }

    fun  removeFragment(fragment: Fragment){
        if (fragment!=null) {
            for (i in 0 until ctx!!.supportFragmentManager.backStackEntryCount) {
                ctx!!.supportFragmentManager.popBackStack()
            }
            ctx!!.supportFragmentManager.beginTransaction().remove(fragment).commit()
            fragment.onDestroy()
        }
        }

    fun replaceFragment(
        fragment: Fragment,
        frameId: Int,
        addBackStack: Boolean,
        clearBackStack: Boolean
    ) {
        ctx!!.supportFragmentManager.transact {
            if (addBackStack)
                addToBackStack(fragment::class.java.name)
            if (clearBackStack) {
                for (i in 0 until ctx!!.supportFragmentManager.backStackEntryCount) {
                    ctx!!.supportFragmentManager.popBackStack()
                }
            }
            replace(frameId, fragment)
        }
    }


    fun replaceFragmentWithData(
        fragment: Fragment,
        frameId: Int,
        addBackStack: Boolean,
        clearBackStack: Boolean,
        bundle : Bundle
    ) {

        fragment.arguments = bundle

        ctx!!.supportFragmentManager.transact {

            if (addBackStack)
                addToBackStack(fragment::class.java.name)
            if (clearBackStack) {
                for (i in 0 until ctx!!.supportFragmentManager.backStackEntryCount) {
                    ctx!!.supportFragmentManager.popBackStack()
                }
            }
            replace(frameId, fragment)

        }
    }

    fun replaceFragmentWithAnimation(
        fragment: Fragment,
        frameId: Int,
        addBackStack: Boolean,
        clearBackStack: Boolean,
        enter: Int,
        exit: Int,
        popEnter: Int,
        popExit: Int
    ) {
        ctx!!.supportFragmentManager.transact {
            setCustomAnimations(enter, exit, popEnter, popExit)
            if (addBackStack)
                addToBackStack(fragment::class.java.name)
            if (clearBackStack) {
                for (i in 0 until ctx!!.supportFragmentManager.backStackEntryCount) {
                    ctx!!.supportFragmentManager.popBackStack()
                }
            }
            replace(frameId, fragment)
        }
    }


}