@echo off
REM UtilityPlugin Build Script for Windows
REM This script builds the plugin using Maven

echo UtilityPlugin Build Script
echo ==========================
echo.

REM Check if Maven is installed
mvn -version >nul 2>&1
if errorlevel 1 (
    echo Error: Maven is not installed or not in PATH
    echo Please install Maven to build the plugin
    pause
    exit /b 1
)

REM Check if Java 17+ is available
java -version >nul 2>&1
if errorlevel 1 (
    echo Error: Java is not installed or not in PATH
    pause
    exit /b 1
)

echo Java version:
java -version
echo.
echo Maven version:
mvn -version
echo.

REM Clean previous builds
echo Cleaning previous builds...
call mvn clean
if errorlevel 1 (
    echo Error: Clean failed
    pause
    exit /b 1
)

REM Run tests
echo Running tests...
call mvn test
if errorlevel 1 (
    echo Error: Tests failed
    pause
    exit /b 1
)

REM Build the plugin
echo Building plugin...
call mvn package
if errorlevel 1 (
    echo Error: Build failed
    pause
    exit /b 1
)

echo.
echo ✅ Build completed successfully!
echo.
echo Plugin JAR file location: target\utility-plugin-1.0.0.jar
echo.
echo To install the plugin:
echo 1. Copy the JAR file to your server's plugins folder
echo 2. Restart your server
echo 3. Configure the plugin via plugins\UtilityPlugin\config.yml
echo.
pause 