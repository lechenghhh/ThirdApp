package com.lecheng.hello.thirdapp.ActivityListItem;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class Aty012TestFileExplore extends ListActivity {
    private ArrayAdapter<File> a;
    private File[] filelist;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String route = getIntent().getStringExtra("route");
        if (route==null){
            file = new File("/mnt/sdcard/");
        }else {
            file = new File(route);
        }

         filelist = file.listFiles();

        a = new ArrayAdapter<File>(this,android.R.layout.simple_list_item_1);
        for (int i=0;i<filelist.length;i++){
            a.add(filelist[i]);
        }
        setListAdapter(a);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File f = filelist[position];

        Toast.makeText(this,getfilemimetype(f),Toast.LENGTH_SHORT).show();

        if (f.isDirectory()==true){
            Intent i = new Intent(this,Aty012TestFileExplore.class);
            i.putExtra("route", f.toString());
            startActivity(i);

        }
        else{
            Intent i2 = new Intent();
            i2.setAction(Intent.ACTION_VIEW);
            i2.setDataAndType(Uri.fromFile(f), "image/*");
            startActivity(i2);


        }
        super.onListItemClick(l, v, position, id);
    }


//获取mime文件类型返回string类型
    public String getfilemimetype(File f){
        String type = null;
        String e = MimeTypeMap.getFileExtensionFromUrl(f.toString());
        if (e != null) {
            MimeTypeMap m = MimeTypeMap.getSingleton();
            type = m.getMimeTypeFromExtension(e);
        }
        return type;
    }

}
