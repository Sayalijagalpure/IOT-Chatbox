#include <ESP8266WiFi.h>
#include <LedControl.h>

const char* ssid = "your_ssid";
const char* password = "yourWifiPassword";

const char* serverIP = "your_serverIP";   
const int serverPort = 5000;

WiFiClient client;

LedControl lc = LedControl(D7, D5, D8, 4);

String message = "hello";


byte getChar(char c, int col) {

  switch(c) {

    case 'h': { byte f[5]={0x7F,0x08,0x08,0x08,0x70}; return f[col]; }
    case 'e': { byte f[5]={0x38,0x54,0x54,0x54,0x18}; return f[col]; }
    case 'l': { byte f[5]={0x00,0x41,0x7F,0x40,0x00}; return f[col]; }
    case 'o': { byte f[5]={0x38,0x44,0x44,0x44,0x38}; return f[col]; }
    case ' ': { return 0x00; }

    default: return 0x00;
  }
}


void setup() {

  Serial.begin(9600);

  for (int i = 0; i < 4; i++) {
    lc.shutdown(i,false);
    lc.setIntensity(i,8);
    lc.clearDisplay(i);
  }

  WiFi.begin(ssid, password);
  Serial.print("Connecting WiFi");

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("\nWiFi Connected!");

  if (client.connect(serverIP, serverPort)) {
    Serial.println("Connected to Server");
  } else {
    Serial.println("Server Connection Failed");
  }
}


void loop() {

  if (client.connected() && client.available()) {
    message = client.readStringUntil('\n');
    message.toLowerCase();
  }

  scrollMessage(message);
}


void scrollMessage(String text) {

  int totalWidth = 32;
  int messageWidth = text.length() * 6;

  for (int shift = totalWidth; shift > -messageWidth; shift--) {

    clearAll();

    for (int i = 0; i < text.length(); i++) {

      for (int col = 0; col < 5; col++) {

        int x = shift + (i * 6) + col;

        if (x >= 0 && x < totalWidth) {

          int module = x / 8;
          int column = x % 8;

          byte colData = getChar(text[i], col);

          for (int row = 0; row < 7; row++) {

            if (bitRead(colData, row)) {
              lc.setLed(3 - module, row, column, true);
            }
          }
        }
      }
    }

    delay(40);
    yield();
  }
}


void clearAll() {
  for (int i = 0; i < 4; i++) {
    lc.clearDisplay(i);
  }
}