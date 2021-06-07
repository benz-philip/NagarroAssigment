# NagarroAssigment
QA Automation assigment

# Pre requisits to execute the project on any machine/server
Execution Machine/Server should have JRE 1.7 or greater
Execution Machine/Server should have maven
Execution Machine/Server should have node installed. Guide to install node.js: https://nodejs.org/en/download/package-manager/
Execution Machine/Server should have command line appium installed. To install check link: https://appium.io/docs/en/about-appium/getting-started/?lang=en
Execution Machine/Server should have android environment. Steps to setup android environment: https://developer.android.com/studio/command-line/variables
A real android device or an android emulator

# To execute the Project:
Connect an android device or start and emulator. On terminal run 'adb devices'. This will give the device Id of your device.
Open file 'NagarroAssigment/src/main/resources/setup/mobileConfig.properties'.
Set the path for appium.js, node.js, ANDROID_HOME in the keys appiumJsPath, nodeJsPath, androidHome rescpectively
If seledroid app is installed on the device then set value for key 'isAppInstalled' as true
If app is not installed set the value for key 'isAppInstalled' as false and assign the path to the selendroid apk to key 'appBuildPath' and name of the apk to the key 'appBuildName'.
Assign the device ID fetched from command 'adb devices' to the key 'deviceId'. And the name of the device to the key 'deviceName'
Open file 'pathToProjectRootFolder/NagarroAssigment/src/test/resources/features' and tag the scenarios with tag '@Regression' which are required to be executed
Once all of the above is done, under the project root folder execute the commmand 'mvn install' for first time and then 'mvn clean test' for every subsequent test
The reports after every execution can be viewed at URL: 'pathToProjectRootFolder/NagarroAssigment/target/cucumber-reports/report.html'
