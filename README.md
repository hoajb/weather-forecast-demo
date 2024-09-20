# Mini-Project: Weather Forecast Application

This is a Weather App built with Android Kotlin using Jetpack Compose, following Clean Architecture
principles.
The app fetches weather data and displays it in a user-friendly manner.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Configuration](#configuration)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [License](#license)

## Features

- Fetches weather data using a public API.
- Displays weather information in a clean and modern UI.
- Utilizes clean architecture for maintainability and testability.

## Technologies Used

- Kotlin
- Hilt
- Jetpack Compose
- Clean Architecture
- Realm Database
- Retrofit for API calls
- MockK, JUnit 5, kotest, turbin for testing

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/weather-app.git
   cd weatherforcast
   ```
2. Open the project in Android Studio.
3. Install dependencies:
   Ensure that you have the necessary SDKs and libraries installed. You can do this by syncing the
   Gradle files in Android Studio.
4. Build the project:
   Use the following command to build the project:

 ```bash
  ./gradlew build
   ```

5. Run the app:
   Connect an Android device or start an emulator, and run the app from Android Studio.

## Configuration

**appkey.properties and appkey_default.properties**

To configure the app for accessing the weather API, you need to provide your API key.

1. Register account and generate API_KEY on https://openweathermap.org

2. Create appkey.properties:
   In the app/ directory, create a file named appkey.properties. This file should contain the
   following line:

```
WEATHER_API_KEY=[put-your-key-here-without-quote-or-bracket]
```

Replace [put-your-key-here-without-quote-or-bracket] with your actual API key for the weather API.
The project includes appkey_default.properties, which serves as a template. You can refer to this
file for the structure and required properties.

## Usage

Once the app is set up and running, you can start using it to fetch and view weather data. The app’s
UI will allow you to enter a city name and fetch the corresponding weather details.

## Screenshots
<table>
  <tr>
    <td><img src="https://github.com/hoajb/weather-forecast-demo/blob/master/screenshots/home_light.jpg" alt="home_light" width="200"></td>
    <td><img src="https://github.com/hoajb/weather-forecast-demo/blob/master/screenshots/details_light.jpg" alt="details_light" width="200"></td>
    <td><img src="https://github.com/hoajb/weather-forecast-demo/blob/master/screenshots/favorite_light2.jpg" alt="favorite_light_2" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/hoajb/weather-forecast-demo/blob/master/screenshots/home_dark.jpg" alt="home_dark" width="200"></td>
    <td><img src="https://github.com/hoajb/weather-forecast-demo/blob/master/screenshots/unknown.jpg" alt="unknown" width="200"></td>
    <td><img src="https://github.com/hoajb/weather-forecast-demo/blob/master/screenshots/empty.jpg" alt="empty" width="200"></td>
  </tr>
</table>

## License

MIT License

Copyright (c) [2024] [Hoa Nguyen]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

1. The above copyright notice and this permission notice shall be included in
   all copies or substantial portions of the Software.

2. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   SOFTWARE.