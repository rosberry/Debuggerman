## Debuggerman

Make debugging your application easier. 

### How to Install

Add to your app/build.gradle
```groovy
implementation("com.rosberry.android:debuggerman:${version}")
```

### How to use

#### Initialize

The library's entry point is DebugAgent class.

First of all you should call a static method `DebugAgent.start(Activity)`, then you will see a notification. You can open 
debug screen by tap on a notification. You should call `DebugAgent.stop` method when you close your app.

####  Add Elements.

To add an element on the debug screen, you should use `DebugAgent.place` method, and send Debug entity as a parameter.

You can use the next implementations or write your own:

 - ToggleDebug
 - ButtonDebug
 - TextDebug