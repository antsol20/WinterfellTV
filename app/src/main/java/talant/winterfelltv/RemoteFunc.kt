package talant.winterfelltv

import android.util.Log
import java.io.DataInputStream
import java.io.IOException
import java.net.Socket
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.math.floor

object RemoteFunc {
    private val skycodes: HashMap<String?, Int?> = object : HashMap<String?, Int?>() {
        init {
            put("power", 0)
            put("select", 1)
            put("back", 2)
            put("dismiss", 2)
            put("ch-up", 6)
            put("ch-down", 7)
            put("interactive", 8)
            put("sidebar", 8)
            put("help", 9)
            put("services", 10)
            put("search", 10)
            put("tvguide", 11)
            put("home", 11)
            put("info", 14)
            put("text", 15)
            put("up", 16)
            put("down", 17)
            put("left", 18)
            put("right", 19)
            put("red", 32)
            put("green", 33)
            put("yellow", 34)
            put("blue", 35)
            put("0", 48)
            put("1", 49)
            put("2", 50)
            put("3", 51)
            put("4", 52)
            put("5", 53)
            put("6", 54)
            put("7", 55)
            put("8", 56)
            put("9", 57)
            put("play", 64)
            put("pause", 65)
            put("stop", 66)
            put("record", 67)
            put("ffwd", 69)
            put("rewind", 71)
            put("boxoffice", 240)
            put("sky", 241)
        }
    }
    private val denoncodes: HashMap<String?, String?> = object : HashMap<String?, String?>() {
        init {
            put("on", "ZMON")
            put("off", "ZMOFF")
            put("volup", "MVUP")
            put("voldown", "MVDOWN")
            put("mute", "MUON")
            put("unmute", "MUOFF")
            put("matrix", "SISAT/CBL")
            put("ps4", "SIGAME")
            put("tvaudio", "SITV")
            put("fronthdmi", "SIAUX1")
            put("sonos", "SICD")
        }
    }

    private fun hexStringToByteArray(s: String?): ByteArray {
        val len = s!!.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4)
                    + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    private val matrixcodes: HashMap<String?, String?> = object : HashMap<String?, String?>() {
        init {
            put("input1_room1", "a55b02030100010000000000f9")
            put("input1_room2", "a55b02030100020000000000f8")
            put("input1_room3", "a55b02030100030000000000f7")
            put("input1_room4", "a55b02030100040000000000f6")
            put("input1_room5", "a55b02030100050000000000f5")
            put("input1_room6", "a55b02030100060000000000f4")
            put("input1_room7", "a55b02030100070000000000f3")
            put("input1_room8", "a55b02030100080000000000f2")
            put("input2_room1", "a55b02030200010000000000f8")
            put("input2_room2", "a55b02030200020000000000f7")
            put("input2_room3", "a55b02030200030000000000f6")
            put("input2_room4", "a55b02030200040000000000f5")
            put("input2_room5", "a55b02030200050000000000f4")
            put("input2_room6", "a55b02030200060000000000f3")
            put("input2_room7", "a55b02030200070000000000f2")
            put("input2_room8", "a55b02030200080000000000f1")
            put("input3_room1", "a55b02030300010000000000f7")
            put("input3_room2", "a55b02030300020000000000f6")
            put("input3_room3", "a55b02030300030000000000f5")
            put("input3_room4", "a55b02030300040000000000f4")
            put("input3_room5", "a55b02030300050000000000f3")
            put("input3_room6", "a55b02030300060000000000f2")
            put("input3_room7", "a55b02030300070000000000f1")
            put("input3_room8", "a55b02030300080000000000f0")
            put("input4_room1", "a55b02030400010000000000f6")
            put("input4_room2", "a55b02030400020000000000f5")
            put("input4_room3", "a55b02030400030000000000f4")
            put("input4_room4", "a55b02030400040000000000f3")
            put("input4_room5", "a55b02030400050000000000f2")
            put("input4_room6", "a55b02030400060000000000f1")
            put("input4_room7", "a55b02030400070000000000f0")
            put("input4_room8", "a55b02030400080000000000ef")
            put("input5_room1", "a55b02030500010000000000f5")
            put("input5_room2", "a55b02030500020000000000f4")
            put("input5_room3", "a55b02030500030000000000f3")
            put("input5_room4", "a55b02030500040000000000f2")
            put("input5_room5", "a55b02030500050000000000f1")
            put("input5_room6", "a55b02030500060000000000f0")
            put("input5_room7", "a55b02030500070000000000ef")
            put("input5_room8", "a55b02030500080000000000ee")
            put("input6_room1", "a55b02030600010000000000f4")
            put("input6_room2", "a55b02030600020000000000f3")
            put("input6_room3", "a55b02030600030000000000f2")
            put("input6_room4", "a55b02030600040000000000f1")
            put("input6_room5", "a55b02030600050000000000f0")
            put("input6_room6", "a55b02030600060000000000ef")
            put("input6_room7", "a55b02030600070000000000ee")
            put("input6_room8", "a55b02030600080000000000ed")
            put("input7_room1", "a55b02030700010000000000f3")
            put("input7_room2", "a55b02030700020000000000f2")
            put("input7_room3", "a55b02030700030000000000f1")
            put("input7_room4", "a55b02030700040000000000f0")
            put("input7_room5", "a55b02030700050000000000ef")
            put("input7_room6", "a55b02030700060000000000ee")
            put("input7_room7", "a55b02030700070000000000ed")
            put("input7_room8", "a55b02030700080000000000ec")
            put("input8_room1", "a55b02030800010000000000f2")
            put("input8_room2", "a55b02030800020000000000f1")
            put("input8_room3", "a55b02030800030000000000f0")
            put("input8_room4", "a55b02030800040000000000ef")
            put("input8_room5", "a55b02030800050000000000ee")
            put("input8_room6", "a55b02030800060000000000ed")
            put("input8_room7", "a55b02030800070000000000ec")
            put("input8_room8", "a55b02030800080000000000eb")
        }
    }

    fun doCode(tab_number: Int, str_cmd: String) {
        val bgThread: Thread = object : Thread() {
            override fun run() {
                try {
                    sendRemCode(tab_number, str_cmd)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        bgThread.start()
    }


    private fun sendRemCode(tab_number: Int, str_cmd: String) {
        if (tab_number == 1) {
            val remCode = skycodes[str_cmd]!!
            val commandbytes = byteArrayOf(4, 1, 0, 0, 0, 0, floor(224 + (remCode / 16).toDouble()).toInt().toByte(), (remCode % 16).toByte())
            val socket = Socket("10.0.0.191", 49160)
            val dis = DataInputStream(socket.getInputStream())
            val dout = socket.getOutputStream()
            var dataRec: ByteArray?
            val data = ByteArray(100)
            var count: Int
            var l = 12
            while (dis.read(data, 0, 24).also { count = it } != -1) {
                dataRec = ByteArray(count)
                for (i in 0 until count) {
                    dataRec[i] = data[i]
                }
                l = if (dataRec.size < 24) {
                    val dataSend = Arrays.copyOfRange(dataRec, 0, l)
                    dout.write(dataSend)
                    dout.flush()
                    1
                } else {
                    break
                }
            }
            dout.write(commandbytes)
            commandbytes[1] = 0
            dout.write(commandbytes)
            dout.flush()
            socket.close()
            dout.close()
            dis.close()
        }
        if (tab_number == 2) {
            val thecode = denoncodes[str_cmd].toString() + "\r"
            val b = thecode.toByteArray(StandardCharsets.US_ASCII)
            val socket = Socket("10.0.0.19", 23)
            val dis = DataInputStream(socket.getInputStream())
            val dout = socket.getOutputStream()
            dout.write(b)
            dout.flush()
            socket.close()
            dout.close()
            dis.close()
        }
        if (tab_number == 3) {
            val socket = Socket("10.0.0.18", 8000)
            val dis = DataInputStream(socket.getInputStream())
            val dout = socket.getOutputStream()
            dout.write(hexStringToByteArray(matrixcodes[str_cmd]))
            dout.flush()
            socket.close()
            dout.close()
            dis.close()
        }
    }
}