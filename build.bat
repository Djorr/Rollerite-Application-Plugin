@echo off
echo ========================================
echo Rollerite Plugin Build Script
echo ========================================
echo.

echo Checking Java version...
java -version
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    pause
    exit /b 1
)

echo.
echo Checking Maven version...
mvn -version
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    pause
    exit /b 1
)

echo.
echo Cleaning previous builds...
mvn clean

echo.
echo Running tests...
mvn test
if %errorlevel% neq 0 (
    echo ERROR: Tests failed
    pause
    exit /b 1
)

echo.
echo Building plugin...
mvn package
if %errorlevel% neq 0 (
    echo ERROR: Build failed
    pause
    exit /b 1
)

echo.
echo ========================================
echo Build completed successfully!
echo ========================================
echo.
echo Plugin JAR file location: target/rollerite-plugin-1.0.0.jar
echo.
echo To install on your server:
echo 1. Copy the JAR file to your server's plugins/ folder
echo 2. Restart your server
echo 3. The plugin will generate default configuration files
echo.
pause 