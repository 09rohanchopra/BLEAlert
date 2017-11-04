//Code For Arduino Nano
//BLE Beacon

#include <SoftwareSerial.h>

SoftwareSerial BTSerial(A4,A5); //RX|TX


void setup(){
  Serial.begin(9600);
  BTSerial.begin(9600); // default baud rate
  while(!Serial); 

  //AT Commands to set up BLE module
  
  Serial.println("AT commands: ");
  BTSerial.write("AT+MARJ0x1234");
  delay(100);
  BTSerial.write("AT+MINO0x6789");
  delay(100);
  BTSerial.write("AT+ADVI5");
  delay(100);
  BTSerial.write("AT+NAMEtEST");
  delay(100);
  BTSerial.write("AT+ADTY3");
  delay(100);
  BTSerial.write("AT+IBEA1");
  delay(100);
  BTSerial.write("AT+DELO2");
  delay(100);
  BTSerial.write("AT+RESET");
  delay(100);
}

int i = 234;

void loop(){
  String num = String(i);
  //Set major bit as the variable
  BTSerial.print("AT+MARJ0xA"+num);
  delay(100);
  //read from the HM-10 and print in the Serial
  if(BTSerial.available())
    Serial.write(BTSerial.read());
    
  //read from the Serial and print to the HM-10 for debugging
  if(Serial.available())
    BTSerial.write(Serial.read());
  i++;
  if(i==999)
    i=234;
}
