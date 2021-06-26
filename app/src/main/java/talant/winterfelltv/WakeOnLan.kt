package talant.winterfelltv

import java.net.*

object WakeOnLan {
    private const val PORT = 9

    fun sendpacket(ipsent: String, macsent: String) {

        val macBytes = getMacBytes(macsent)
        val bytes = ByteArray(6 + 16 * macBytes.size)
        for (i in 0..5) {
            bytes[i] = 0xff.toByte()
        }
        var i = 6
        while (i < bytes.size) {
            System.arraycopy(macBytes, 0, bytes, i, macBytes.size)
            i += macBytes.size
        }
        val address: InetAddress = InetAddress.getByName(ipsent)
        val packet = DatagramPacket(bytes, bytes.size, address, PORT)
        val socket = DatagramSocket()
        socket.send(packet)
        socket.close()

    }


    private fun getMacBytes(macStr: String): ByteArray {
        val bytes = ByteArray(6)
        val hex = macStr.split(":")
        require(hex.size == 6) { "Invalid MAC address." }
        try {
            for (i in 0..5) {
                bytes[i] = hex[i].toInt(16).toByte()
            }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Invalid hex digit in MAC address.")
        }
        return bytes
    }
}