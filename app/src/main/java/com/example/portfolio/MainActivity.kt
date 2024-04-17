package com.example.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.theme.PortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TemperatureConverter()
                }
            }
        }
    }
}

fun celsiusToFahrenheit(celsius: Double): Double {
    return celsius * 9 / 5 + 32
}

fun fahrenheitToCelsius(fahrenheit: Double): Double {
    return (fahrenheit - 32) * 5 / 9
}

fun celsiusToKelvin(celsius: Double): Double {
    return celsius + 273.15
}

fun kelvinToCelsius(kelvin: Double): Double {
    return kelvin - 273.15
}

fun fahrenheitToKelvin(fahrenheit: Double): Double {
    return (fahrenheit + 459.67) * 5 / 9
}

fun kelvinToFahrenheit(kelvin: Double): Double {
    return kelvin * 9 / 5 - 459.67
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperatureConverter() {
    var text by remember { mutableStateOf("30°") }

    val options = listOf("Celsius", "Fahrenheit", "Kelvin")
    var expandedFromDropDownMenu by remember { mutableStateOf(false) }
    var expandedToDropDownMenu by remember { mutableStateOf(false) }
    var selectedOptionFromText by remember { mutableStateOf(options[0]) }
    var selectedOptionToText by remember { mutableStateOf(options[0]) }

    val conversionFunctions = mapOf(
        "Celsius" to mapOf(
            "Fahrenheit" to ::celsiusToFahrenheit,
            "Kelvin" to ::celsiusToKelvin
        ),
        "Fahrenheit" to mapOf(
            "Celsius" to ::fahrenheitToCelsius,
            "Kelvin" to ::fahrenheitToKelvin
        ),
        "Kelvin" to mapOf(
            "Celsius" to ::kelvinToCelsius,
            "Fahrenheit" to ::kelvinToFahrenheit
        )
    )

    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Temperature Converter", style = MaterialTheme.typography.headlineLarge)
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Temperature") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = expandedFromDropDownMenu,
                onExpandedChange = { expandedFromDropDownMenu = it },
                modifier = Modifier.weight(1f) // Add weight modifier here
            ) {
                TextField(
                    // The `menuAnchor` modifier must be passed to the text field for correctness.
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = selectedOptionFromText,
                    onValueChange = {},
                    label = { Text("From") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFromDropDownMenu) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )

                ExposedDropdownMenu(
                    expanded = expandedFromDropDownMenu,
                    onDismissRequest = { expandedFromDropDownMenu = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionFromText = selectionOption
                                expandedFromDropDownMenu = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.width(16.dp))

            ExposedDropdownMenuBox(
                expanded = expandedToDropDownMenu,
                onExpandedChange = { expandedToDropDownMenu = it },
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = selectedOptionToText,
                    onValueChange = {},
                    label = { Text("To") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedToDropDownMenu) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )

                ExposedDropdownMenu(
                    expanded = expandedToDropDownMenu,
                    onDismissRequest = { expandedToDropDownMenu = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionToText = selectionOption
                                expandedToDropDownMenu = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

        }
        Button(onClick = {
            val temperature = text.substringBefore("°").toDouble()
            val conversionFunction = conversionFunctions[selectedOptionFromText]?.get(selectedOptionToText)
            val result = conversionFunction?.invoke(temperature) ?: temperature
            text = "$result°"
        }, modifier = Modifier.padding(0.dp, 16.dp)) {
            Text("Submit")
        }

    }

}



@Preview(showBackground = true)
@Composable
fun TemperatureConverterPreview() {
    PortfolioTheme {
        TemperatureConverter()
    }
}
