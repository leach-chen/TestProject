package com.leachchen.testmarkdown;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zzhoujay.markdown.MarkDown;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Markdown2Activity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown2);

        textView = (TextView)this.findViewById(R.id.textView);


        final InputStream stream = getResources().openRawResource(R.raw.hello);

        textView.post(new Runnable() {
            @Override
            public void run() {
                long time = System.nanoTime();
                Spanned spanned = MarkDown.fromMarkdown(stream, new Html.ImageGetter() {
                    public static final String TAG = "Markdown";

                    @Override
                    public Drawable getDrawable(String source) {
                        Log.d(TAG, "getDrawable() called with: source = [" + source + "]");

                        Drawable drawable;
                        try {
                            drawable = drawableFromUrl(source);
                            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        } catch (IOException e) {
                            Log.w(TAG, "can't get image", e);
                            drawable = new ColorDrawable(Color.LTGRAY);
                            drawable.setBounds(0, 0, textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight(), 400);
                        }
                        return drawable;
                    }
                }, textView);
                long useTime = System.nanoTime() - time;
                Toast.makeText(getApplicationContext(), "use time: " + useTime + "ns", Toast.LENGTH_LONG).show();
                textView.setText(spanned);
            }
        });
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }
}
