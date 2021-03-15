# Debuggerman
Make debugging your application easier. 

## Requirements
- Android API 21+

## Setup
Add a dependency:
```groovy
implementation("com.rosberry.android:debuggerman:${version}")
```

## Usage
### Initialize
The library's entry point is DebugAgent class.

First of all you should call a static method `DebugAgent.start(Activity)`, then you will see a notification. You can open 
debug screen by tap on a notification. You should call `DebugAgent.stop` method when you close your app.

###  Add Elements.
To add an element on the debug screen, you should use `DebugAgent.place` method, and send Debug entity as a parameter.

You can use the next implementations or write your own:
 - ToggleDebug
 - ButtonDebug
 - TextDebug

## About
<img src="https://github.com/rosberry/Foundation/blob/master/Assets/full_logo.png?raw=true" height="100" />

This project is owned and maintained by [Rosberry](http://rosberry.com). We build mobile apps for users worldwide 🌏.

Check out our [open source projects](https://github.com/rosberry), read [our blog](https://medium.com/@Rosberry) or give us a high-five on 🐦 [@rosberryapps](http://twitter.com/RosberryApps).

## License
Debuggerman is available under the MIT license. See the LICENSE file for more info.