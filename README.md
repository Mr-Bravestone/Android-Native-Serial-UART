# Android-Native-Serialport
Credit: Google's official serial port library [android-serialport-api](https://code.google.com/archive/p/android-serialport-api/)


<img src ="https://github.com/Mr-Bravestone/Android-Native-SerialPort/blob/master/art/back.png" height = 150 alt ="Android-Native-SerialPort"/>
<img src ="https://github.com/Mr-Bravestone/Android-Native-SerialPort/blob/master/art/front.png" height = 150 alt ="Android-Native-SerialPort"/>

# Usage
1. To add a dependency to your project, specify a dependency configuration such as implementation in the dependencies block of your module's build.gradle file.
```
dependencies {
    //For Java.
    implementation ''
    //For Kotlin.
    implementation("")
}
```
## 1.List the serial port
```
serialPortFinder.getAllDevicesPath();
```
## 2.Serial port settings
```
serialHelper.setPort(String sPort);      //serial port
serialHelper.setBaudRate(int iBaud);     //baud rate
```
Serial port property settings must be set before the function 'open()' is executed.
## 3. Opening serial port
```
serialHelper.open();
```
## 4.Closing serial port
```
serialHelper.close();
```
## 5.Sending
```
serialHelper.send(byte[] bOutArray); // sending byte[]
serialHelper.sendHex(String sHex);  // sending Hex
serialHelper.sendTxt(String sTxt);  // sending ASCII
```
## 6.Receiving
```
 @Override
protected void onDataReceived(final ComBean comBean) {
       Log.d("Received - ",comBean.toString())
   }
```
