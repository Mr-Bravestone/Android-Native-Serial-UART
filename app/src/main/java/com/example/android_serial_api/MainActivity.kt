package com.example.android_serial_api

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android_serialport_api.SerialPortFinder
import androidx.appcompat.app.AppCompatActivity
import mr.bravestone.serialport.Communication.ComBean
import mr.bravestone.serialport.SerialTool
import java.io.File
import java.nio.charset.StandardCharsets


class MainActivity : AppCompatActivity() {
    var serialTool = object: SerialTool() {}
    private val editText:TextView get() = findViewById(R.id.editTextText)
    private val textView:TextView get() = findViewById(R.id.textView)
    private val SelectPort:Spinner get() = findViewById(R.id.selectport)
    private val SelectBaud:Spinner get() = findViewById(R.id.selectbaud)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.movementMethod = ScrollingMovementMethod()

        var serialPortFinder =SerialPortFinder()
        val ports: Array<String> = serialPortFinder.getAllDevicesPath()
        val BaudRates = resources.getStringArray(R.array.BaudRate)
        if (SelectPort != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, ports
            )
            SelectPort.adapter = adapter
        }
        if (SelectBaud != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, BaudRates
            )
            SelectBaud.adapter = adapter
        }




        serialTool = object: SerialTool()
        {
            override fun onDataReceived(paramComBean: ComBean?) {
                super.onDataReceived(paramComBean)
                runOnUiThread {
                    run {
                        if (paramComBean != null) {
                            val result =String(paramComBean.bRec, StandardCharsets.UTF_8)
                            textView.append(result + "\n")

                        }
                    }

                }
            }
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        serialTool.close()
    }

    fun OpenPort(view: View)
    {
        CheckPermit(SelectPort.selectedItem.toString(),SelectBaud.selectedItem.toString())
    }

    fun ClosePort(view: View)
    {
        try {
            serialTool.close()
            Toast.makeText(this,"Port Closed Successfully",Toast.LENGTH_SHORT).show()
        }catch (e: Exception)
        {
            Toast.makeText(this,"Port Not Opened yet!",Toast.LENGTH_SHORT).show()
        }

    }

    fun SendData(view: View)
    {
        val value = editText.text.toString()
        try {
            serialTool.sendTxt(value + "\n")
            editText.text=""
        }catch (e: Exception)
        {
            Toast.makeText(this,"Port Not Opened yet!",Toast.LENGTH_SHORT).show()
        }


    }

    fun ClearText(view: View)
    {
        textView.setText("")
        println(SelectPort.selectedItem)
    }

    fun CheckPermit(port: String,baud: String)
    {
        var device = File(port)
        if (!device.canRead() and !device.canWrite()) {
            try
            {
                ShellCodeExec("su -c chmod 666 $port")
                if (device.canRead() and device.canWrite())
                {
                    serialTool.setPort(port)
                    serialTool.setBaudRate(baud)
                    serialTool.open()
                    if(serialTool.isOpen)
                    {
                        Toast.makeText(this,"Port Open Success",Toast.LENGTH_SHORT).show()
                    }

                }
                else
                {
                    if (!device.canRead() and !device.canWrite()) {
                        Toast.makeText(this, "Root Permission Not Available", Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (_: Exception)
            {

            }
        }
        else
        {
            serialTool.setPort(port)
            serialTool.setBaudRate(baud)
            serialTool.open()
            if(serialTool.isOpen)
            {
                Toast.makeText(this,"Port Open Success Without Root",Toast.LENGTH_SHORT).show()
            }

        }

    }


}