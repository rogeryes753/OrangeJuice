package com.example.perona

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.perona.adapter.ItemData

class SecondPageDialogFragment(private val item: ItemData?) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.second_page, null, false)

        val tempData = view.findViewById<TextView>(R.id.tempData)

        tempData.text = item!!.startTime + "\n" + item.endTime + "\n" + item.temperature

        mBehaviorSet(view,dialog)
        return dialog
    }


    private fun mBehaviorSet(view : View, dialog:Dialog){
        dialog.setContentView(view)

        val layoutParams = view.layoutParams
        val width = (dialog.context.resources.displayMetrics.widthPixels )
        val height = (dialog.context.resources.displayMetrics.heightPixels )

        layoutParams.width = width
        layoutParams.height = height
        view.layoutParams = layoutParams

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}