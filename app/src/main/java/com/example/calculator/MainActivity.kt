package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? =null
    private var btnOne : Button?=null
    var lastNumeric:Boolean =false
    var lastDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput= findViewById(R.id.tvInput)
        btnOne=findViewById(R.id.btnone)

    }
    fun ondigit(view:View){
       tvInput?.append((view as Button).text)// appends means it will add to the exsiting test in the view and nullabel meanns it can be nothing added
           // we cannot use views text directly as it is not supported so to do that we need to convert vies as a button and then do it
        lastNumeric=true
        lastDot=false// the input we had last was a number so the last Dot has to be false and the last numeric has to true
        7
    }
    fun onCLear(view: View){
        tvInput?.text=""
    }
    fun onDecimalPoint(view:View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        tvInput?.text?.let { //it is the value recived
            if(lastNumeric&&!isOperatorAdded(it.toString())){ // if last input is not a num we cant do any operation
                //do we have any other operator like /and * both at the same line does not exixsts
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
        }// to checks wether the tvinput exsists



        }
    }

        fun onEqual(view: View){
            if(lastNumeric){
                var tvValue = tvInput?.text.toString()// whatever is the textview input will becoeom a strign and get saved in tvValue
                var prefix=""


                try{
                    if(tvValue.startsWith("-")){
                        prefix="-"
                        tvValue=tvValue.substring(1)// get ride of the 1st entry of the string like -99 will be taken as 99 - is index 0 and 99 is index 1
                    } // this part just fixes the bug that if the string was -33-66 then it makes it 33-99
                    if(tvValue.contains("-")){ // if there is 33-66 so it has -
                        var splitValue = tvValue.split("-")
                        var one =splitValue[0] // one will be of type string //stores 33
                        var two = splitValue[1] // same will be of two //stores 66

                        if(prefix.isNotEmpty()){
                            one = prefix+one // convets one to// -33 again
                        }
                        var result = one.toDouble() - two.toDouble()// now as we parse it to double the result if of type double//  becomes -33-66
                        tvInput?.text=remzeroafterdot(result.toString())// converted to string form // output is -99


                    } else if(tvValue.contains("+")){
                        var splitValue = tvValue.split("+")
                        var one =splitValue[0]
                        var two = splitValue[1]

                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }
                        var result = one.toDouble() + two.toDouble()
                        tvInput?.text=remzeroafterdot(result.toString())


                    }else if(tvValue.contains("*")){
                        var splitValue = tvValue.split("*")
                        var one =splitValue[0]
                        var two = splitValue[1]

                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }
                        var result = one.toDouble() * two.toDouble()
                        tvInput?.text=remzeroafterdot(result.toString())


                    }
                    else if (tvValue.contains("/")){
                        var splitValue = tvValue.split("/")
                        var one =splitValue[0]
                        var two = splitValue[1]

                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }
                        var result = one.toDouble() / two.toDouble()
                        tvInput?.text=remzeroafterdot(result.toString())


                    }

                }catch (e:ArithmeticException){
                    e.printStackTrace()
                }

            }
        }
        private fun remzeroafterdot(result: String):String{ // this function removes .0 and makes it an integer for the output to look better
            var value = result
            if (result.contains("0")){
                value=result.substring(0,result.length-2)// starts at 0 and leaves last 2 entrise as .0
            }
            return value
        }

        private fun isOperatorAdded(value: String):Boolean{
            return if (value.startsWith("-")){ // if - comes ahead then this block gets exicuted
                false // ignoring the minus infront for furthur calculatins
            }
            else{
                value.contains("/")||value.contains("*")||value.contains("+")||value.contains("-")
                }
            }
    // we are allowing only 2 negatives in our text view as happens in a caluclator other than 2 - there can not be any 2 opeartors in textview
        }
