package com.cookingapp

import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.Timer


class Timer : AppCompatActivity(), View.OnClickListener {
    private var inputh: EditText? = null
    private var inputm: EditText? = null
    private var inputs: EditText? = null
    private var get: Button? = null
    private var startTime: Button? = null
    private var stopTime: Button? = null
    private var time: TextView? = null
    private var i = 0
    private var j = 0
    private var k = 0
    private var total = 0
    private var hour = 0
    private var min = 0
    private var sec = 0
    private var timer: Timer? = null
    private var task: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        initView()
    }
    private fun initView() {
        inputh = findViewById<View>(R.id.inputh) as EditText
        inputm = findViewById<View>(R.id.inputm) as EditText
        inputs = findViewById<View>(R.id.inputs) as EditText
        get = findViewById<View>(R.id.gettime) as Button
        startTime = findViewById<View>(R.id.starttime) as Button
        stopTime = findViewById<View>(R.id.stoptime) as Button
        time = findViewById<View>(R.id.time) as TextView
        get!!.setOnClickListener(this)
        stopTime!!.setOnClickListener(this)
        startTime!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.gettime -> {
                i = if(inputh!!.text.toString() != ""){
                    inputh!!.text.toString().toInt()
                }else {
                    0
                }
                j = if(inputm!!.text.toString() != ""){
                    inputm!!.text.toString().toInt()
                }else {
                    0
                }
                k = if(inputs!!.text.toString() != ""){
                    inputs!!.text.toString().toInt()
                }else {
                    0
                }
                total = (i * 60 * 60) + (j * 60) + k
                time!!.text = "$i:$j:$k"
            }
            R.id.starttime -> StartTime()
            R.id.stoptime -> StopTime()
        }
    }
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val rest = msg.arg1

            hour = if(rest>=3600){
                rest/3600
            }else {
                0
            }
            min = if(rest >= 3600){
                (rest % 3600)/60
            }else if(rest >= 60){
                rest/60
            }else{
                0
            }
            sec = (rest % 3600)%60
            time!!.text = "$hour:$min:$sec"
            StartTime()
        }
    }
    fun StartTime() {
        timer = Timer()
        task = object : TimerTask() {
            override fun run() {
                if(total>0) {
                    total--
                    val message = mHandler.obtainMessage()
                    message.arg1 = total
                    mHandler.sendMessage(message)
                }
                if (total == 0){
                    timer!!.cancel()
                    val notification: Uri =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val r = RingtoneManager.getRingtone(applicationContext, notification)
                    r.play()
                }
            }
        }
        timer!!.schedule(task, 1000)
    }

    fun StopTime() {
        timer!!.cancel()
    }
}


