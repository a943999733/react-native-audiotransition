
package com.lk.audiotransition;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.io.File;

import javax.annotation.Nonnull;

import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.IConvertCallback;
import cafe.adriel.androidaudioconverter.model.AudioFormat;
import cafe.adriel.androidaudioconverter.callback.ILoadCallback;
public class RNAudiotransitionModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNAudiotransitionModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAudiotransition";
  }

  @ReactMethod
  public void initAudioTransition(){
      //音频格式转换
      AndroidAudioConverter.load(reactContext, new ILoadCallback() {
          @Override
          public void onSuccess() {
              // Great!
          }
          @Override
          public void onFailure(Exception error) {
              // FFmpeg is not supported by device
          }
      });
  }

  @ReactMethod
  public void audioToStart(String absolutePath,String type,final Callback callback){
    AudioFormat audioType=AudioFormat.AAC ;

    switch (type){
      case "aac":
        audioType = AudioFormat.AAC;
        break;
      case "mp3":
          audioType = AudioFormat.MP3;
        break;
      case "m4a":
          audioType = AudioFormat.M4A;
        break;
      case "wma":
          audioType = AudioFormat.WMA;
        break;
      case "wav":
          audioType = AudioFormat.WAV;
        break;
      case "falc" :
           audioType = AudioFormat.FLAC;
        break;
    }
//      String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() +"/iat.wav";

    AndroidAudioConverter.with(reactContext)
            // Your current audio file
            .setFile(new File(absolutePath))

            // Your desired audio format
            .setFormat(audioType)

            // An callback to know when conversion is finished
            .setCallback(new IConvertCallback() {
              @Override
              public void onSuccess(File convertedFile) {
                callback.invoke(200,convertedFile.getAbsolutePath());
              }

              @Override
              public void onFailure(Exception error) {
                  Toast.makeText(reactContext,error.getMessage(),Toast.LENGTH_SHORT).show();
                callback.invoke(100,error.getMessage());
              }
            })

            // Start conversion
            .convert();
  }

}