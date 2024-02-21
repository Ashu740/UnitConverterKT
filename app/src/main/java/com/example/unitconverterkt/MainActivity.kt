package com.example.unitconverterkt

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.compose.ui.text.TextStyle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterkt.ui.theme.UnitConverterKTTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterKTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConvertor(){
    var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outputUnit by remember {
        mutableStateOf("Meters")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    val conversionFactor= remember {
        mutableStateOf(1.00)
    }
    val oConversionFactor= remember {
        mutableStateOf(1.00)
    }
    val customTextStyle =TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 20.sp,
        color = Color.Black
    )

    fun convertUnit(){
        //?: elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result= (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("UnitConvertor",style =MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(Dp(16.toFloat())))
        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue=it
            convertUnit()
        },
            label = {Text(text = "Enter Value")}
            )
        Spacer(modifier = Modifier.height(Dp(16.toFloat())))
        Row {
            //Input box
            Box{
                Button(onClick = {iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="DropDown" )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false}) {
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Centimeters"
                            conversionFactor.value=0.01
                            convertUnit()

                        }
                    )
                    DropdownMenuItem(text = {Text("Meters")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Meters"
                            conversionFactor.value=1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            conversionFactor.value=0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Millimeters"
                            conversionFactor.value=0.001
                            convertUnit()
                        }
                    )

                }
            }
            Spacer(modifier = Modifier.width(Dp(16.toFloat())))
            //Output Box
            Box{
                Button(onClick = { oExpanded=true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "DropDown")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = {Text("Centimeters")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Centimeters"
                            oConversionFactor.value=0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(text = {Text("Meters")},
                        onClick = {  oExpanded=false
                            outputUnit="Meters"
                            oConversionFactor.value=1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(text = {Text("Feet")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Feet"
                            oConversionFactor.value=0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(text = {Text("Millimeters")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Millimeters"
                            oConversionFactor.value=0.001
                            convertUnit()
                        }
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(Dp(16.toFloat())))
        Text(text = "Result $outputValue $outputUnit",
            style = MaterialTheme.typography.bodyMedium
            )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview() {
    UnitConvertor()
}