package com.death.tip.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.EditText
import androidx.core.view.marginEnd
import androidx.fragment.app.DialogFragment
import com.death.tip.R

class SaveDialogFragment : DialogFragment() {

    interface Callback{
        fun onSaveTip(name: String)
    }

    private var saveTipCallback: SaveDialogFragment.Callback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        saveTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val saveDialog = context?.let{
            val editText =  EditText(it)
            editText.id = viewId
            editText.hint = "Enter Location"
            AlertDialog.Builder(it)
                .setView(editText)
                .setNegativeButton(R.string.action_cancel,null)
                .setPositiveButton(R.string.action_save) { _, _ -> onSave(editText)}
                .create()
        }

        return saveDialog!!
    }

    private fun onSave(editText: EditText) {
        val text = editText.text.toString()
        if(text.isNotEmpty()){
            saveTipCallback?.onSaveTip(text)
        }
    }

    companion object {
        val viewId = View.generateViewId()
    }
}