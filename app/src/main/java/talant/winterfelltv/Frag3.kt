package talant.winterfelltv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import talant.winterfelltv.RemoteFunc.doCode

class Frag3 : Fragment(), View.OnClickListener {
    
    private var radioGroup: RadioGroup? = null
    private var input1: Button? = null
    private var input2: Button? = null
    private var input3: Button? = null
    private var input4: Button? = null
    private var input5: Button? = null
    private var input6: Button? = null
    private var input7: Button? = null
    private var input8: Button? = null
    private var root: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        root = inflater.inflate(R.layout.fragment_3, container, false)
        
        
        input1 = root?.findViewById(R.id.input1B)
        input1?.setOnClickListener(this)
        input2 = root?.findViewById(R.id.input2B)
        input2?.setOnClickListener(this)
        input3 = root?.findViewById(R.id.input3B)
        input3?.setOnClickListener(this)
        input4 = root?.findViewById(R.id.input4B)
        input4?.setOnClickListener(this)
        input5 = root?.findViewById(R.id.input5B)
        input5?.setOnClickListener(this)
        input6 = root?.findViewById(R.id.input6B)
        input6?.setOnClickListener(this)
        input7 = root?.findViewById(R.id.input7B)
        input7?.setOnClickListener(this)
        input8 = root?.findViewById(R.id.input8B)
        input8?.setOnClickListener(this)

        radioGroup = root?.findViewById(R.id.RG)

        return root
    }


    override fun onClick(v: View) {
        val b = v.findViewById<Button>(v.id)
        val btntag = b.tag.toString()
        val selectedButton: Int? = radioGroup?.checkedRadioButtonId
        val thebutton = root?.findViewById<RadioButton>(selectedButton!!)
        val r = btntag + "_room" + thebutton?.tag.toString()
        doCode(3, r)
    }
}