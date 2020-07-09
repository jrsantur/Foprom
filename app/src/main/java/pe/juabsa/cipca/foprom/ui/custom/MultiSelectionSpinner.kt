package pe.juabsa.cipca.foprom.ui.custom

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.core.app.NotificationCompat

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


class MultiSelectionSpinner : androidx.appcompat.widget.AppCompatSpinner, OnMultiChoiceClickListener {
    interface OnMultipleItemsSelectedListener {
        fun selectedIndices(indices: MutableList<Int?>?)
        fun selectedStrings(strings: MutableList<String?>?)
    }

    private var listener: OnMultipleItemsSelectedListener? = null
    var _items: Array<String>? = null
    var mSelection: BooleanArray? = null
    var mSelectionAtStart: BooleanArray? = null
    var _itemsAtStart: String? = null
    var simple_adapter: ArrayAdapter<String>

    constructor(context: Context) : super(context) {
        simple_adapter = ArrayAdapter(context,
                pe.juabsa.cipca.foprom.R.layout.dropdown_menu_popup_item)
        super.setAdapter(simple_adapter)

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        simple_adapter = ArrayAdapter(context,
                pe.juabsa.cipca.foprom.R.layout.dropdown_menu_popup_item)
        super.setAdapter(simple_adapter)
    }



    fun setListener(listener: OnMultipleItemsSelectedListener?) {
        this.listener = listener
    }

    override fun onClick(dialog: DialogInterface, which: Int, isChecked: Boolean) {
        if (mSelection != null && which < mSelection!!.size) {
            mSelection!![which] = isChecked
            simple_adapter.clear()
            simple_adapter.add(buildSelectedItemString())
        } else {
            throw IllegalArgumentException(
                    "Argument 'which' is out of bounds.")
        }
    }

    override fun performClick(): Boolean {
        val builder =   AlertDialog.Builder(context)
        builder.setTitle("Por favor seleccione!!!")
        builder.setMultiChoiceItems(_items, mSelection, this)
        _itemsAtStart = selectedItemsAsString
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
            System.arraycopy(mSelection!!, 0, mSelectionAtStart, 0, mSelection!!.size)
            listener!!.selectedIndices(selectedIndices)
            listener!!.selectedStrings(selectedStrings)
        })
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, which ->
            simple_adapter.clear()
            simple_adapter.add(_itemsAtStart)
            System.arraycopy(mSelectionAtStart!!, 0, mSelection, 0, mSelectionAtStart!!.size)
        })
        builder.show()
        return true
    }

    override fun setAdapter(adapter: SpinnerAdapter) {
        throw RuntimeException(
                "setAdapter is not supported by MultiSelectSpinner.")
    }

    fun setItems(items: Array<String>?) {
        _items = items
        mSelection = BooleanArray(_items!!.size)
        mSelectionAtStart = BooleanArray(_items!!.size)
        simple_adapter.clear()
        simple_adapter.add(_items!![0])
        Arrays.fill(mSelection, false)
        mSelection!![0] = true
        mSelectionAtStart!![0] = true
    }

    fun setItems(items: List<String>) {
        _items = items.toTypedArray()
        mSelection = BooleanArray(_items!!.size)
        mSelectionAtStart = BooleanArray(_items!!.size)
        simple_adapter.clear()
        simple_adapter.add(_items!![0])
        Arrays.fill(mSelection, false)
        mSelection!![0] = true
    }

    fun setSelection(selection: Array<String>) {
        for (i in mSelection!!.indices) {
            mSelection!![i] = false
            mSelectionAtStart!![i] = false
        }
        for (cell in selection) {
            for (j in _items!!.indices) {
                if (_items!![j] == cell) {
                    mSelection!![j] = true
                    mSelectionAtStart!![j] = true
                }
            }
        }
        simple_adapter.clear()
        simple_adapter.add(buildSelectedItemString())
    }

    fun setSelection(selection: List<String>) {
        for (i in mSelection!!.indices) {
            mSelection!![i] = false
            mSelectionAtStart!![i] = false
        }
        for (sel in selection) {
            for (j in _items!!.indices) {
                if (_items!![j] == sel) {
                    mSelection!![j] = true
                    mSelectionAtStart!![j] = true
                }
            }
        }
        simple_adapter.clear()
        simple_adapter.add(buildSelectedItemString())
    }

    override fun setSelection(index: Int) {
        for (i in mSelection!!.indices) {
            mSelection!![i] = false
            mSelectionAtStart!![i] = false
        }
        if (index >= 0 && index < mSelection!!.size) {
            mSelection!![index] = true
            mSelectionAtStart!![index] = true
        } else {
            throw IllegalArgumentException("Index " + index
                    + " is out of bounds.")
        }
        simple_adapter.clear()
        simple_adapter.add(buildSelectedItemString())
    }

    fun setSelection(selectedIndices: IntArray) {
        for (i in mSelection!!.indices) {
            mSelection!![i] = false
            mSelectionAtStart!![i] = false
        }
        for (index in selectedIndices) {
            if (index >= 0 && index < mSelection!!.size) {
                mSelection!![index] = true
                mSelectionAtStart!![index] = true
            } else {
                throw IllegalArgumentException("Index " + index
                        + " is out of bounds.")
            }
        }
        simple_adapter.clear()
        simple_adapter.add(buildSelectedItemString())
    }

    val selectedStrings: MutableList<String?>
        get() {
            val selection: MutableList<String?> = LinkedList()
            for (i in _items!!.indices) {
                if (mSelection!![i]) {
                    selection.add(_items!![i])
                }
            }
            return selection
        }

    val selectedIndices: MutableList<Int?>
        get() {
            val selection: MutableList<Int?> = LinkedList()
            for (i in _items!!.indices) {
                if (mSelection!![i]) {
                    selection.add(i)
                }
            }
            return selection
        }

    private fun buildSelectedItemString(): String {
        val sb = StringBuilder()
        var foundOne = false
        for (i in _items!!.indices) {
            if (mSelection!![i]) {
                if (foundOne) {
                    sb.append(", ")
                }
                foundOne = true
                sb.append(_items!![i])
            }
        }
        return sb.toString()
    }

    val selectedItemsAsString: String
        get() {
            val sb = StringBuilder()
            var foundOne = false
            for (i in _items!!.indices) {
                if (mSelection!![i]) {
                    if (foundOne) {
                        sb.append(", ")
                    }
                    foundOne = true
                    sb.append(_items!![i])
                }
            }
            return sb.toString()
        }
}
