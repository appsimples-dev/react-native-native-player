
# react-native-native-player

## Getting started

`$ npm install react-native-native-player --save`

### Mostly automatic installation

`$ react-native link react-native-native-player`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-native-player` and add `RNNativeVideoPlayer.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNNativeVideoPlayer.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNNativeVideoPlayerPackage;` to the imports at the top of the file
  - Add `new RNNativeVideoPlayerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-native-player'
  	project(':react-native-native-player').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-native-player/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-native-player')
  	```


## Usage
```javascript
import RNNativeVideoPlayer from 'react-native-native-player';

// TODO: What to do with the module?
RNNativeVideoPlayer;
```
  