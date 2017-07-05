package com.example.songye02.diasigame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.attr.path;

public class PayActivity extends AppCompatActivity {

    private ImageView ivZhifbubao;
    private ImageView ivWeixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ivWeixin = (ImageView) findViewById(R.id.iv_weixin);
        ivZhifbubao = (ImageView) findViewById(R.id.iv_zhifubao);

        ivWeixin.setOnClickListener((v) -> {
            Bitmap bitmap = ((BitmapDrawable)ivWeixin.getDrawable()).getBitmap();
            saveImageToGallery(PayActivity.this, bitmap, "用微信打赏作者");
            Toast.makeText(PayActivity.this, "已保存图片到本地根目录", Toast.LENGTH_SHORT).show();
        });

        ivZhifbubao.setOnClickListener((v) -> {
            Bitmap bitmap = ((BitmapDrawable)ivZhifbubao.getDrawable()).getBitmap();
            saveImageToGallery(PayActivity.this, bitmap, "用支付宝打赏作者");
            Toast.makeText(PayActivity.this, "已保存图片到本地根目录", Toast.LENGTH_SHORT).show();
        });
    }

    public static void saveImageToGallery(Context context, Bitmap bmp, String name) {
        // 首先保存图片
        File appDir = Environment.getExternalStorageDirectory();
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + appDir.toString())));
    }
}
