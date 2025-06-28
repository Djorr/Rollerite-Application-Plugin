#!/bin/bash

# UtilityPlugin Build Script
# This script builds the plugin using Maven

set -e

echo "UtilityPlugin Build Script"
echo "=========================="

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Error: Maven is not installed or not in PATH"
    echo "Please install Maven to build the plugin"
    exit 1
fi

# Check if Java 17+ is available
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "Error: Java 17 or higher is required"
    echo "Current Java version: $(java -version 2>&1 | head -n 1)"
    exit 1
fi

echo "Java version: $(java -version 2>&1 | head -n 1)"
echo "Maven version: $(mvn -version | head -n 1)"
echo ""

# Clean previous builds
echo "Cleaning previous builds..."
mvn clean

# Run tests
echo "Running tests..."
mvn test

# Build the plugin
echo "Building plugin..."
mvn package

# Check if build was successful
if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Build completed successfully!"
    echo ""
    echo "Plugin JAR file location: target/utility-plugin-1.0.0.jar"
    echo ""
    echo "To install the plugin:"
    echo "1. Copy the JAR file to your server's plugins folder"
    echo "2. Restart your server"
    echo "3. Configure the plugin via plugins/UtilityPlugin/config.yml"
else
    echo ""
    echo "❌ Build failed!"
    exit 1
fi 