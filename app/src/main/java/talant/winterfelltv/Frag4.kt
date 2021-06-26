package talant.winterfelltv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import java.io.IOException
import java.net.URI
import java.util.*


class Frag4 : Fragment(), View.OnClickListener {

    private var cmdpayload = "{\"id\": \"register_0\", \"type\": \"request\", \"uri\": \"CMDGOESHERE\"}"
    private var cmdpayloadmute = "{\"id\": \"register_0\", \"type\": \"request\", \"uri\": \"CMDGOESHERE\", \"payload\": {\"mute\": MUTEGOESHERE}}"
    private var cmdpayloadapps = "{\"id\": \"register_0\", \"type\": \"request\", \"uri\": \"CMDGOESHERE\", \"payload\": {\"id\": \"APPNAMEGOESHERE\"}}"
    private var cmdpayloadup = "{\"id\": \"register_0\", \"type\": \"request\", \"uri\": \"CMDGOESHERE\", \"payload\": {\"type\": \"button\",\"name\": \"UP\"}}"

    private var onButton: Button? = null
    private var offButton: Button? = null
    private var volupButton: Button? = null
    private var voldownButton: Button? = null
    private var muteButton: Button? = null
    private var unmuteButton: Button? = null
    private var netflixButton: Button? = null
    private var youtubeButton: Button? = null
    private var upButton: Button? = null
    private var downButton: Button? = null
    private var leftButton: Button? = null
    private var rightButton: Button? = null
    private var playButton: Button? = null
    private var ffwdButton: Button? = null
    private var rewButton: Button? = null
    private var okButton: Button? = null

    private var radioGroup: RadioGroup? = null
    private var radioButton1: RadioButton? = null
    private var radioButton2: RadioButton? = null
    private var radioButton3: RadioButton? = null


    private var root: View? = null

    private var roomtag: String = "1"   // default set to room 1
    private var w: WebSock? = null

    val masterMap: HashMap<String, List<String>> = HashMap<String, List<String>>()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        root = inflater.inflate(R.layout.fragment_4, container, false)

        masterMap["1"] = listOf("10.0.0.20", "04:A2:22:10:6A:F4", "register_0", "e794add8ced98b8b05c3c55255662b78") // lounge
        masterMap["2"] = listOf("10.0.0.23", "7C:1C:4E:66:A0:DA", "register_1", "4bd77130672228acda4cc4818078decc") // bedroom
        masterMap["3"] = listOf("10.0.0.27", "E8:F2:E2:45:9E:C8", "register_2", "71cdb0ccf4497ceca68e4bf4992fc2dd") // cinema

        createNewWebsocket()

        onButton = root?.findViewById(R.id.onB)
        onButton?.setOnClickListener(this)

        offButton = root?.findViewById(R.id.offB)
        offButton?.setOnClickListener(this)

        volupButton = root?.findViewById(R.id.volupB)
        volupButton?.setOnClickListener(this)

        voldownButton = root?.findViewById(R.id.voldownB)
        voldownButton?.setOnClickListener(this)

        muteButton = root?.findViewById(R.id.muteB)
        muteButton?.setOnClickListener(this)

        unmuteButton = root?.findViewById(R.id.unmuteB)
        unmuteButton?.setOnClickListener(this)

        netflixButton = root?.findViewById(R.id.netflixB)
        netflixButton?.setOnClickListener(this)

        youtubeButton = root?.findViewById(R.id.youtubeB)
        youtubeButton?.setOnClickListener(this)

        upButton = root?.findViewById(R.id.upB)
        upButton?.setOnClickListener(this)

        downButton = root?.findViewById(R.id.downB)
        downButton?.setOnClickListener(this)

        leftButton = root?.findViewById(R.id.leftB)
        leftButton?.setOnClickListener(this)

        rightButton = root?.findViewById(R.id.rightB)
        rightButton?.setOnClickListener(this)

        playButton = root?.findViewById(R.id.playB)
        playButton?.setOnClickListener(this)

        ffwdButton = root?.findViewById(R.id.ffB)
        ffwdButton?.setOnClickListener(this)

        rewButton = root?.findViewById(R.id.rrB)
        rewButton?.setOnClickListener(this)

        okButton = root?.findViewById(R.id.okB)
        okButton?.setOnClickListener(this)

        radioGroup = root?.findViewById(R.id.RG)

        radioButton1 = root?.findViewById(R.id.room1B)
        radioButton1?.setOnClickListener(this)

        radioButton2 = root?.findViewById(R.id.room2B)
        radioButton2?.setOnClickListener(this)

        radioButton3 = root?.findViewById(R.id.room3B)
        radioButton3?.setOnClickListener(this)



        return root
    }


    override fun onClick(v: View) {

        if (v.id == R.id.room1B || v.id == R.id.room2B || v.id == R.id.room3B) {

            val r = v.findViewById<RadioButton>(v.id)
            roomtag = r?.tag.toString()
            Log.d("websocket", "$roomtag pressed")
            createNewWebsocket()
            return

        } else {


            val b = v.findViewById<Button>(v.id)
            when (val btntag = b.tag.toString()) {
                "on" -> {
                    doWOLCode()
                }
                "up" -> {
                    val cmd = lgcodes[btntag]
                    var payload: String = cmdpayloadup.replace("CMDGOESHERE", cmd.toString())
                    w?.send(payload)
                }
                "mute" -> {
                    val cmd = lgcodes[btntag]
                    var payload: String = cmdpayloadmute.replace("CMDGOESHERE", cmd.toString())
                    payload = payload.replace("MUTEGOESHERE", "true")
                    w?.send(payload)
                }
                "unmute" -> {
                    val cmd = lgcodes[btntag]
                    var payload: String = cmdpayloadmute.replace("CMDGOESHERE", cmd.toString())
                    payload = payload.replace("MUTEGOESHERE", "false")
                    w?.send(payload)
                }
                "netflix" -> {
                    val cmd = lgcodes[btntag]
                    var payload: String = cmdpayloadapps.replace("CMDGOESHERE", cmd.toString())
                    payload = payload.replace("APPNAMEGOESHERE", "netflix")
                    w?.send(payload)
                }
                "youtube" -> {
                    val cmd = lgcodes[btntag]
                    var payload: String = cmdpayloadapps.replace("CMDGOESHERE", cmd.toString())
                    payload = payload.replace("APPNAMEGOESHERE", "youtube.leanback.v4")
                    w?.send(payload)
                }
                else -> {
                    val cmd = lgcodes[btntag]
                    val payload: String = cmdpayload.replace("CMDGOESHERE", cmd.toString())
                    w?.send(payload)
                }
            }
        }

    }

    private fun doWOLCode() {
        val bgThread: Thread = object : Thread() {
            override fun run() {
                try {
                    //WakeOnLan.sendpacket(masterMap[roomtag]!![0], masterMap[roomtag]!![1])
                    WakeOnLan2.send(masterMap[roomtag]!![1], masterMap[roomtag]!![0])
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        bgThread.start()
    }

    private fun createNewWebsocket() {
        val theuri = "ws://" + masterMap[roomtag]!![0] + ":3000"
        w = WebSock(URI(theuri), masterMap[roomtag]!![2], masterMap[roomtag]!![3])
        w!!.connect()
    }


    private val lgcodes: HashMap<String?, String?> = object : HashMap<String?, String?>() {
        init {
            put("off", "ssap://system/turnOff")
            put("volup", "ssap://audio/volumeUp")
            put("voldown", "ssap://audio/volumeDown")
            put("mute", "ssap://audio/setMute")
            put("unmute", "ssap://audio/setMute")
            put("netflix", "ssap://system.launcher/launch")
            put("youtube", "ssap://system.launcher/launch")
            put("up", "ssap://com.webos.service.ime/insertText")
            put("down", "")
            put("left", "")
            put("right", "")
            put("ok", "")
            put("play", "ssap://media.controls/play")
            put("rewind", "ssap://media.controls/rewind")
            put("fastforward", "ssap://media.controls/fastForward")
        }
    }


}



