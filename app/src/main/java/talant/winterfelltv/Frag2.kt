package talant.winterfelltv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import talant.winterfelltv.RemoteFunc.doCode

class Frag2 : Fragment(), View.OnClickListener {
    private var onB: Button? = null
    private var offB: Button? = null
    private var volupB: Button? = null
    private var voldownB: Button? = null
    private var muteB: Button? = null
    private var unmuteB: Button? = null
    private var matrixB: Button? = null
    private var ps4B: Button? = null
    private var tvaudioB: Button? = null
    private var fronthdmiB: Button? = null
    private var sonosB: Button? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_2, container, false)
        onB = root.findViewById(R.id.onB)
        onB?.setOnClickListener(this)
        
        offB = root.findViewById(R.id.offB)
        offB?.setOnClickListener(this)
        volupB = root.findViewById(R.id.volupB)
        volupB?.setOnClickListener(this)
        voldownB = root.findViewById(R.id.voldownB)
        voldownB?.setOnClickListener(this)
        muteB = root.findViewById(R.id.muteB)
        muteB?.setOnClickListener(this)
        unmuteB = root.findViewById(R.id.unmuteB)
        unmuteB?.setOnClickListener(this)
        matrixB = root.findViewById(R.id.matrixB)
        matrixB?.setOnClickListener(this)
        ps4B = root.findViewById(R.id.ps4B)
        ps4B?.setOnClickListener(this)
        tvaudioB = root.findViewById(R.id.tvaudioB)
        tvaudioB?.setOnClickListener(this)
        fronthdmiB = root.findViewById(R.id.fronthdmiB)
        fronthdmiB?.setOnClickListener(this)
        sonosB = root.findViewById(R.id.sonosB)
        sonosB?.setOnClickListener(this)
        return root
    }

    override fun onClick(v: View) {
        val b = v.findViewById<Button>(v.id)
        val btntag = b.tag.toString()
        doCode(2, btntag)
    }
}