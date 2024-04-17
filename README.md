# Temperature convertor 

## Overview
The app is a temperature conversion application developed in Kotlin using the Android Studio IDE. It allows users to convert temperatures between Celsius, Fahrenheit, and Kelvin.

## Application Structure
The application is structured into a single activity, `MainActivity.kt`, which hosts the `TemperatureConverter` composable function. This function is responsible for rendering the user interface and handling user interactions.

## User Interface
The user interface consists of a text field for inputting the temperature to be converted, two dropdown menus for selecting the input and output temperature scales, and a submit button for performing the conversion.

## Temperature Conversion
The application supports six types of temperature conversions:
- Celsius to Fahrenheit
- Fahrenheit to Celsius
- Celsius to Kelvin
- Kelvin to Celsius
- Fahrenheit to Kelvin
- Kelvin to Fahrenheit

These conversions are implemented as separate functions in `MainActivity.kt`.

## Themes and Resources
The application uses the Material 3 theme, defined in `themes.xml`. The app's name and launcher icon are defined in `strings.xml` and `ic_launcher.xml`, respectively.

## Preview
A preview of the `TemperatureConverter` composable function is available for visualization during development.

## Development Environment
The application is developed in Android Studio Hedgehog | 2023.1.1 using Kotlin and Gradle. The developer's username on GitHub is alex1092.
