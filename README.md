# Android-Native-Serialport
Credit: Google's official serial port library [android-serialport-api](https://code.google.com/archive/p/android-serialport-api/)


<img src ="https://github.com/Mr-Bravestone/Android-Native-Serial-UART/blob/master/art/back.png" height = 150 alt ="Android-Native-SerialPort"/>
<img src ="https://github.com/Mr-Bravestone/Android-Native-Serial-UART/blob/master/art/front.png" height = 150 alt ="Android-Native-SerialPort"/>

# Usage
1. Add it in your root build.gradle/buil.gradle.kts at the end of repositories:
```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
2. To add a dependency to your project, specify a dependency configuration such as implementation in the dependencies block of your module's build.gradle file.
```
dependencies {
    //For Java.
    implementation 'com.github.Mr-Bravestone:Android-Native-SerialPort:Tag'
    //For Kotlin.
    implementation("com.github.Mr-Bravestone:Android-Native-SerialPort:Tag")
}
```
## 1.List the serial port and creating object
```
var serialTool = object: SerialTool() {}
var serialPortFinder =SerialPortFinder.getAllDevicesPath();
serialTool = object: SerialTool()
{
    override fun onDataReceived(paramComBean: ComBean?) {
        super.onDataReceived(paramComBean)
        runOnUiThread {
            run {
                if (paramComBean != null) {
                    val result =String(paramComBean.bRec, StandardCharsets.UTF_8)
                    textView.append(result + "\n")
                }
            }

        }
    }
}
```
## 2.Serial port settings
```
serialTool.setPort(String sPort);      //serial port
serialTool.setBaudRate(int iBaud);     //baud rate
```
Serial port property settings must be set before the function 'open()' is executed.
## 3. Opening serial port
```
serialTool.open();
```
## 4.Closing serial port
```
serialTool.close();
```
## 5.Sending
```
serialTool.send(byte[] bOutArray); // sending byte[]
serialTool.sendHex(String sHex);  // sending Hex
serialTool.sendTxt(String sTxt);  // sending ASCII
```
