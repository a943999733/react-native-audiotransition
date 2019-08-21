


# react-native-audiotransition
This is an audio conversion format tool, supporting the conversion of AAC, MP3, M4A, WMA, WAV, FLAC.
## Getting started

`$ npm install react-native-audiotransition --save`

### Mostly automatic installation

`$ react-native link react-native-audiotransition`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-audiotransition` and add `RNAudiotransition.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAudiotransition.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android
AndroidManifest:
	```
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	```
	add permission 
	

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.lk.audiotransition.RNAudiotransitionPackage;` to the imports at the top of the file
  - Add `new RNAudiotransitionPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-audiotransition'
  	project(':react-native-audiotransition').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-audiotransition/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-audiotransition')
  	```


## Usage
```javascript
import RNAudiotransition from 'react-native-audiotransition';

// TODO: What to do with the module?

  AndroidManifest.initAudioTransition();
 
RNAudiotransition. audioToStart(String audioUrl,String type, Callback callback)
```

##demo
```javascript
  import RNAudiotransition from 'react-native-audiotransition';

  componentDidMount(){
    RNAudiotransition.initAudioTransition();
    
    this.timer = setTimeout(() => {
      RNAudiotransition.audioToStart('/sdcard/audio.mp3', 'aac',  (res)=>{
        alert(res)
      })
    }, 1000);
    
  }
  ```