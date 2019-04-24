
package com.reactlibrary;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNNativeVideoPlayerModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNNativeVideoPlayerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "NativeVideoPlayer";
  }

  @ReactMethod
  public void openPlayer(@NonNull String url) {
    ReactApplicationContext context = getReactApplicationContext();
    Intent intent = new Intent(context, VideoPlayerActivity.class);
    intent.putExtra("videoUrl", url);
    context.startActivity(intent);
  }
}