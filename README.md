# Android-Native-Serialport
Credit: Google's official serial port library [android-serialport-api](https://code.google.com/archive/p/android-serialport-api/)


<img src ="https://github.com/Mr-Bravestone/Android-Native-SerialPort/blob/master/art/back.png" height = 150 alt ="Android-Native-SerialPort"/>
<img src ="https://github.com/Mr-Bravestone/Android-Native-SerialPort/blob/master/art/front.png" height = 150 alt ="Android-Native-SerialPort"/>

# Usage
1. Open your root  `build.gradle` and add `mavenCentral()`: 
```
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```
2. To add a dependency to your project, specify a dependency configuration such as implementation in the dependencies block of your module's build.gradle file.
```
dependencies {
    implementation 'io.github.xmaihh:serialport:2.1.1'
}
```
## 1.List the serial port
```
serialPortFinder.getAllDevicesPath();
```
## 2.Serial port property settings
```
serialHelper.setPort(String sPort);      //set serial port
serialHelper.setBaudRate(int iBaud);     //set baud rate
serialHelper.setStopBits(int stopBits);  //set stop bit
serialHelper.setDataBits(int dataBits);  //set data bit
serialHelper.setParity(int parity);      //set parity
serialHelper.setFlowCon(int flowcon);    //set flow control
```
Serial port property settings must be set before the function 'open()' is executed.
## 3. Open the serial port
```
serialHelper.open();
```
## 4.Close the serial port
```
serialHelper.close();
```
## 5.Send
```
serialHelper.send(byte[] bOutArray); // send byte[]
serialHelper.sendHex(String sHex);  // send Hex
serialHelper.sendTxt(String sTxt);  // send ASCII
```
## 6.Receiving
```
 @Override
protected void onDataReceived(final ComBean comBean) {
       Toast.makeText(getBaseContext(), new String(comBean.bRec, "UTF-8"), Toast.LENGTH_SHORT).show();
   }
```
# Screenshots

![Screenshot showing screen](art/screen.png "Screenshot showing screen")

<a href="https://play.google.com/store/apps/details?id=com.ex.serialport"><img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" height="70"></a>

PC-side debugging tools  [Serial debugging tool for Win](https://github.com/xmaihh/Android-Serialport/raw/master/serial_port_utility_latest.exe)
