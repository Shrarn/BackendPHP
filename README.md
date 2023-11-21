# Android Application with PHP-MySQL Backend Documentation

## Project Overview

This project is an Android application that utilizes a PHP-MySQL backend to manage user registration, authentication, and profile information. The application consists of three main screens: Register, Login, and Profile. The communication between the Android app and the server is handled using the Volley library, while user authentication data is stored locally using SharedPreferences.

## Table of Contents

1. [Introduction](#introduction)
2. [Requirements](#requirements)
3. [Setup](#setup)
4. [Project Structure](#project-structure)
5. [Implementation Details](#implementation-details)
   - [Register Screen](#register-screen)
   - [Login Screen](#login-screen)
   - [Profile Screen](#profile-screen)
   - [Backend Communication](#backend-communication)
   - [SharedPreferences](#sharedpreferences)
6. [How to Run](#how-to-run)
7. [Contributing](#contributing)
8. [License](#license)

## Introduction

This Android application demonstrates the integration of a PHP-MySQL backend to handle user registration, login, and profile management. The backend is responsible for securely storing user data, and the Volley library is used for efficient communication between the Android app and the server.

## Requirements

- Android Studio (latest version)
- PHP server with MySQL support
- Volley library for Android (can be included via Gradle)
- Internet connection on the Android device/emulator

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/your-repository.git
   ```

2. Open the project in Android Studio.

3. Set up the PHP-MySQL backend on your server.

4. Update the server URL in the Android app (`Constants.java`) to point to your backend server.

## Project Structure

```
|-- app
|   |-- src
|       |-- main
|           |-- java
|               |-- com.backcoffer.androidphpmysql
|                   |-- Constants.java
|                   |-- SharedPrefManager.java
|                   |-- MainActivity.java
|                   |-- LoginActivity.java
|                   |-- ProfileActivity.java
|                   |-- RequestHandler.java
|           |-- res
|-- backend
|   |-- includes
|       |-- Constants.php
|       |-- DbConnect.php
|       |-- DbOpertations.php
|   |-- v1
|       |-- registerUser.php
|       |-- userLogin.php
```

## Implementation Details

### Register Screen

The `MainActivity` allows users to create a new account by entering their username, email, and password. The user's data is sent to the server using a POST request, and the server responds with a success or error message.

### Login Screen

The `LoginActivity` enables users to log in with their credentials. The entered data is sent to the server for authentication. Upon successful authentication, the user is directed to the Profile screen.

### Profile Screen

The `ProfileActivity` displays the user's information retrieved from the server. Users can log out from this screen.

### Backend Communication

- The `Volley` library is used for network requests. Requests to the server for registration and login are handled in the respective activities.

- The PHP backend (`backend/DbOperations.php`) processes the requests, interacts with the MySQL database, and returns the appropriate response.

### SharedPreferences

User login data is stored locally using SharedPreferences. This allows users to stay logged in between app sessions.

## How to Run

1. Install the app on an Android device/emulator through Android Studio.

2. Ensure that your PHP-MySQL backend is set up and accessible.

3. Open the app, register a new account, and log in to explore the functionality.

## Contributing

Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.
