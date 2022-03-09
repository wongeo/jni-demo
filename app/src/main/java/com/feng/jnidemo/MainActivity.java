package com.feng.jnidemo;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.feng.cpplib.JNI;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JNI jni = new JNI();
        String str = jni.stringFromJNI();

        Bitmap bitmap = loadBitmap();

        bitmap = processBitmap(bitmap);

        ImageView imageView = findViewById(R.id.imageView);

        imageView.setImageBitmap(bitmap);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(str);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    /**
     * 从assets中加载图片
     */
    private Bitmap loadBitmap() {
        Bitmap bmp = null;
        AssetManager am = getResources().getAssets();
        try {
            InputStream is = am.open("dog.jpg");
            BitmapFactory.Options options = new BitmapFactory.Options();
            bmp = BitmapFactory.decodeStream(is, null, options);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    /**
     * 处理图片，此方法中会调用nativeProcessBitmap
     *
     * @param bitmap
     * @return
     */
    private Bitmap processBitmap(Bitmap bitmap) {
        Bitmap bmp = null;
        if (bitmap != null) {
            bmp = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            new JNI().nativeProcessBitmap(bmp);
        }
        return bmp;
    }
}