package com.death.tip.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.marginEnd
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.death.tip.R
import com.death.tip.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.list_saved_location.view.*

class LoadDialogFragment : DialogFragment() {

    interface Callback{
        fun onTipSelected(name: String)
    }

    private var loadTipCallback: LoadDialogFragment.Callback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        loadTipCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val saveDialog = context?.let{
            AlertDialog.Builder(it)
                .setView(createView(it))
                .setNegativeButton(R.string.action_cancel, null)
                .create()
        }

        return saveDialog!!
    }

    private fun createView(ctx: Context): View{
        val rv = LayoutInflater .from(ctx)
            .inflate(R.layout.list_saved_location, null)
            .saved_calculations

        rv.setHasFixedSize(true)
        //rv.layoutManager = LinearLayoutManager(ctx,RecyclerView.VERTICAL, false)
        rv.addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.HORIZONTAL))

        val adapter = TipSummaryAdapter{
            loadTipCallback?.onTipSelected(it.locationName)
            dismiss()
        }
        rv.adapter = adapter

        val vm = ViewModelProviders.of(activity!!).get(CalculatorViewModel::class.java)
        vm.loadSavedTipCalculationSummaries().observe(this, Observer {
            if(it!=null){
                adapter.updateList(it)
            }
        })
        return rv
    }

}