package com.handheld.exp.utils

import android.content.pm.PackageManager
import rikka.shizuku.Shizuku
import rikka.shizuku.ShizukuRemoteProcess

class ShizukuUtils {
    companion object{
        fun runCommands(vararg commands: String): String {

            // TODO: This might not work in the future anymore
            // but it right now the easiest way to spawn a ADB shell.
            // Should be replaced in the future with a proper solution
            val method = Shizuku::class.java.getDeclaredMethod(
                "newProcess",
                Array<String>::class.java,
                Array<String>::class.java,
                String::class.java
            )
            method.isAccessible = true

            val shCommand = CommonShellRunner.commandsToShCommand(*commands)

            val process = method.invoke(null, shCommand, null, "/") as ShizukuRemoteProcess

            return CommonShellRunner.runProcess(process)
        }

        fun isAvailable(): Boolean{
            if(!hasPermission()){
                return false
            }

            return Shizuku.pingBinder()
        }

        fun hasPermission(): Boolean{
            if (!isCorrectVersion()){
                return false
            }

            return Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
        }

        private fun isCorrectVersion(): Boolean{
            return !Shizuku.isPreV11() && Shizuku.getVersion() >= 11
        }
    }

}