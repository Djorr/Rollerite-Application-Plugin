#!/bin/bash

echo "========================================"
echo "Rollerite Plugin Build Script"
echo "========================================"
echo

# Check if Java is installed
echo "Checking Java version..."
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    exit 1
fi
java -version

# Check if Maven is installed
echo
echo "Checking Maven version..."
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    exit 1
fi
mvn -version

echo
echo "Cleaning previous builds..."
mvn clean

echo
echo "Running tests..."
if ! mvn test; then
    echo "ERROR: Tests failed"
    exit 1
fi

echo
echo "Building plugin..."
if ! mvn package; then
    echo "ERROR: Build failed"
    exit 1
fi

echo
echo "========================================"
echo "Build completed successfully!"
echo "========================================"
echo
echo "Plugin JAR file location: target/rollerite-plugin-1.0.0.jar"
echo
echo "To install on your server:"
echo "1. Copy the JAR file to your server's plugins/ folder"
echo "2. Restart your server"
echo "3. The plugin will generate default configuration files"
echo 