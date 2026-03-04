# 📡 Two-Way IoT Chatbot System using Advanced Java & ESP8266

## 📌 Overview

This project was developed as part of a 3-day Advanced Java Workshop.
It demonstrates real-time communication between multiple systems using Java Socket Programming and ESP8266 WiFi modules, with message output displayed on MAX7219 LED matrix displays.

The system integrates software networking concepts with IoT hardware to build a functional two-way communication model.

## 🚀 Project Architecture

### ● Java Server

Manages multiple client connections using TCP socket programming.

### ● Java Client
Sends messages to the server over the same WiFi network.

### ● ESP8266 Modules (2 Devices)
Connect to the Java server via WiFi and receive messages in real time.

### ● MAX7219 LED Matrix Displays
Display received messages as scrolling text.

## 🧠 Key Concepts Implemented

● TCP/IP Communication

● Java Socket Programming

● Multi-client Server Handling

● Threading in Java

● ESP8266 WiFi Connectivity

● Real-time Message Broadcasting

● Hardware-Software Integration

## 🛠 Technologies Used

● Java (Advanced Java – Networking & Sockets)

● ESP8266 (NodeMCU)

● Arduino IDE

● MAX7219 LED Matrix (4-Module Display)

● WiFi Network Communication

## 📂 Project Files

● GUI_TCPServer.java → Java server application

● Simple_TCPClient.java → Java client application

● ESP8266_MAX7219.ino → Arduino code for ESP8266

● README.md → Project documentation

## ⚙ How to Run
1️⃣ Start the Java Server

● Compile and run GUI_TCPServer.java

● The server listens on port 5000

2️⃣ Run the Java Client

● Update the server IP address

● Compile and run Simple_TCPClient.java

● Send messages through the console

3️⃣ Upload ESP8266 Code

● Update:

i. WiFi SSID

ii. WiFi Password

iii. Server IP

 ● Upload the code using Arduino IDE

 ● Connect MAX7219 module

 ● Power the ESP8266

Messages sent from the client are displayed in real time on both LED matrices.

## 🔌 Hardware Connections (ESP8266 → MAX7219)
MAX7219 Pin   →     	ESP8266 Pin

DIN          →       D7

CLK	         →         D5

CS	         →          D8

VCC	          →         5V

GND	          →         GND

## 🎯 Learning Outcomes

● Practical implementation of Java networking concepts

● Understanding of multi-device communication

● Real-time data transmission using TCP

● Integration of embedded systems with desktop applications
