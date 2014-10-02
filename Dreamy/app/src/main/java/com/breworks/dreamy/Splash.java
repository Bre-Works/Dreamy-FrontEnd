package com.breworks.dreamy;

        import android.app.Activity;
        import android.app.DownloadManager;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.MotionEvent;
        import android.view.Window;

public class Splash extends Activity {

    private Thread mSplashThread;
    /**
      Duration of wait in seconds
     */
    private final int SPLASH_DISPLAY_LENGTH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        IntentLauncher launch = new IntentLauncher();
        launch.start();
    }

    private class IntentLauncher extends Thread {
        public void run(){
            try{
                Thread.sleep(SPLASH_DISPLAY_LENGTH*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Start activity
            Intent intent = new Intent(Splash.this, logIn.class);
            Splash.this.startActivity(intent);
            Splash.this.finish();

        }
    }
}