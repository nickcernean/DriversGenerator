
# Device Driver Generator

## Overview

The **Device Driver Generator** is a Java console application that simplifies the process of generating device drivers in XML format for RS-232 and IP internet protocols. This tool is designed to save time and effort for developers working on projects that require interfacing with devices using these communication protocols.

## Features

- **RS-232 Driver Generation**: Easily create device drivers for RS-232 communication by providing the necessary configuration parameters.

- **IP Internet Protocol Driver Generation**: Generate drivers for devices using IP-based communication protocols like TCP/IP and UDP.

- **XML Output**: The generated device drivers are formatted in XML, making them easy to integrate into your project.

## Prerequisites

Before using the Device Driver Generator, ensure that you have the following prerequisites installed on your system:

- Java Development Kit (JDK) 8 or higher
- Git (optional, for cloning the repository)

## Installation

1. Clone the repository to your local machine (optional):

   ```bash
   git clone https://github.com/yourusername/device-driver-generator.git
   ```

2. Compile the Java source code:

   ```bash
   javac DeviceDriverGenerator.java
   ```

## Usage

To generate device drivers in XML format, follow these steps:

1. Run the Java application:

   ```bash
   java CreateLevelMatrixSequences
   ```

2. Follow the prompts to provide the necessary information for the driver generation, such as device name, communication protocol (RS-232 or IP), and configuration parameters.

3. The generated XML file will be saved in the same directory as the application. You can customize the output file name during the generation process.

4. Integrate the generated XML driver into your project.

## Configuration

The Device Driver Generator supports various configuration options for both RS-232 and IP-based communication protocols. 
You can specify baud rates, data bits, stop bits, parity, IP addresses, ports, and other relevant parameters depending on the chosen protocol.

## Example

Here's a simple example of generating an RS-232 device driver:

```bash
java CreateLevelMatrixSequences
```
LevelSequence upDownMatrixLevelSequence = new LevelSequence(16,2, "BiampCount", "1",
                Enums.TypeValues.Continous, "UpCommand1", "UpCommand1", "UPCommand1",
                "DOWNCommand1","DOWNCommand1","DOWNCommand1", true, false);
Modify any of the object parameters. 

ISequencesGenerator matrixLevelGenerator = new LevelGenerator(incDecMatrixLevelSequence);
        fileWriter.writeTo(matrixLevelGenerator.generateMatrixSequence());

```bash
Device driver generated successfully!
```
The output will be generated on the Desktop.
