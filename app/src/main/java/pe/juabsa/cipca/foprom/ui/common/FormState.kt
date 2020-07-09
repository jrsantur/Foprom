package pe.juabsa.cipca.foprom.ui.common

import com.google.android.material.textfield.TextInputLayout

class FormState (val view: TextInputLayout?= null,
                 val isEmpty: Int? = null,
                 val isDataValid: Boolean = false)