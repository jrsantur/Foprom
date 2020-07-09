package pe.juabsa.cipca.foprom.ui.common

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

class Validation(val view: TextInputLayout,
                 @StringRes val resource: Int,
                 val validator: (TextInputLayout.() -> Boolean)) {


    fun text(value: String? = null) = value?.also {
            view.editText?.setText(it)
    } ?: "${view.editText?.text}"

}



