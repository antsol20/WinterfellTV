package talant.winterfelltv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import talant.winterfelltv.RemoteFunc.doCode

class Frag1 : Fragment(), View.OnClickListener {
    private var homeB: Button? = null
    private var searchB: Button? = null
    private var backB: Button? = null
    private var infoB: Button? = null
    private var rewindB: Button? = null
    private var playB: Button? = null
    private var pauseB: Button? = null
    private var stopB: Button? = null
    private var recB: Button? = null
    private var ffwdB: Button? = null
    private var upB: Button? = null
    private var downB: Button? = null
    private var leftB: Button? = null
    private var rightB: Button? = null
    private var selectB: Button? = null
    private var chup: Button? = null
    private var chdown: Button? = null
    private var oneB: Button? = null
    private var twoB: Button? = null
    private var threeB: Button? = null
    private var fourB: Button? = null
    private var fiveB: Button? = null
    private var sixB: Button? = null
    private var sevenB: Button? = null
    private var eightB: Button? = null
    private var nineB: Button? = null
    private var zeroB: Button? = null
    private var redB: Button? = null
    private var greenB: Button? = null
    private var yellowB: Button? = null
    private var blueB: Button? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_1, container, false)
        
        searchB = root.findViewById(R.id.searchB)
        searchB?.setOnClickListener(this)
        homeB = root.findViewById(R.id.homeB)
        homeB?.setOnClickListener(this)
        backB = root.findViewById(R.id.backB)
        backB?.setOnClickListener(this)
        infoB = root.findViewById(R.id.infoB)
        infoB?.setOnClickListener(this)
        rewindB = root.findViewById(R.id.rewindB)
        rewindB?.setOnClickListener(this)
        playB = root.findViewById(R.id.playB)
        playB?.setOnClickListener(this)
        pauseB = root.findViewById(R.id.pauseB)
        pauseB?.setOnClickListener(this)
        stopB = root.findViewById(R.id.stopB)
        stopB?.setOnClickListener(this)
        recB = root.findViewById(R.id.recordB)
        recB?.setOnClickListener(this)
        ffwdB = root.findViewById(R.id.fastforwardB)
        ffwdB?.setOnClickListener(this)
        upB = root.findViewById(R.id.upB)
        upB?.setOnClickListener(this)
        downB = root.findViewById(R.id.downB)
        downB?.setOnClickListener(this)
        leftB = root.findViewById(R.id.leftB)
        leftB?.setOnClickListener(this)
        rightB = root.findViewById(R.id.rightB)
        rightB?.setOnClickListener(this)
        selectB = root.findViewById(R.id.selectB)
        selectB?.setOnClickListener(this)
        chup = root.findViewById(R.id.chupB)
        chup?.setOnClickListener(this)
        chdown = root.findViewById(R.id.chdownB)
        chdown?.setOnClickListener(this)
        oneB = root.findViewById(R.id.oneB)
        oneB?.setOnClickListener(this)
        twoB = root.findViewById(R.id.twoB)
        twoB?.setOnClickListener(this)
        threeB = root.findViewById(R.id.threeB)
        threeB?.setOnClickListener(this)
        fourB = root.findViewById(R.id.fourB)
        fourB?.setOnClickListener(this)
        fiveB = root.findViewById(R.id.fiveB)
        fiveB?.setOnClickListener(this)
        sixB = root.findViewById(R.id.sixB)
        sixB?.setOnClickListener(this)
        sevenB = root.findViewById(R.id.sevenB)
        sevenB?.setOnClickListener(this)
        eightB = root.findViewById(R.id.eightB)
        eightB?.setOnClickListener(this)
        nineB = root.findViewById(R.id.nineB)
        nineB?.setOnClickListener(this)
        zeroB = root.findViewById(R.id.zeroB)
        zeroB?.setOnClickListener(this)
        redB = root.findViewById(R.id.redB)
        redB?.setOnClickListener(this)
        greenB = root.findViewById(R.id.greenB)
        greenB?.setOnClickListener(this)
        yellowB = root.findViewById(R.id.yellowB)
        yellowB?.setOnClickListener(this)
        blueB = root.findViewById(R.id.blueB)
        blueB?.setOnClickListener(this)
        return root
    }

    override fun onClick(v: View) {
        val b = v.findViewById<Button>(v.id)
        val btntag = b.tag.toString()
        doCode(1, btntag)
    }
}