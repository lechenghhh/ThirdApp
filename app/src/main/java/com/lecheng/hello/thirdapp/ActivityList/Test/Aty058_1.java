package com.lecheng.hello.thirdapp.ActivityList.Test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class Aty058_1 extends Activity implements View.OnClickListener {

    private File[] files;
    private List<String> paths = new ArrayList<String>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty058_a);

        // 遍历 SD 卡下 .png 文件通过微信分享，保证SD卡根目录下有.png的文件
        File root = Environment.getExternalStorageDirectory();
        files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".JPEG"))
                    return true;
                return false;
            }
        });
        for (File file : files) {
            paths.add(file.getAbsolutePath());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_btn1:
                if (paths == null || paths.size() == 0) {
                    Toast.makeText(this, "SD卡根目录下无.png格式照片", Toast.LENGTH_SHORT).show();
                } else {
                    ShareUtils.share9PicsToWXCircle(this, "你好，成功的分享了多张照片到微信", paths);
                }
                break;
        }
    }
}
