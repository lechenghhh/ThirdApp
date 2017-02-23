package com.lecheng.hello.thirdapp.ActivityList;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import com.lecheng.hello.thirdapp.DB.Db;
import com.lecheng.hello.thirdapp.R;


public class Aty006SQLite extends ListActivity {
    private Button btnadd;
    private EditText etname,etsex;
    private Db db;
    private SQLiteDatabase dbwrite,dbread;
    private SimpleCursorAdapter adapter;
    private View.OnClickListener btnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                ContentValues cv = new ContentValues();
                cv.put("name",etname.getText().toString());
                cv.put("sex", etsex.getText().toString());
                dbwrite.insert("user", null, cv);
                syncListView();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity006);

        etname = (EditText) findViewById(R.id.etname);
        etsex = (EditText) findViewById(R.id.etsex);
        btnadd = (Button) findViewById(R.id.btnaddsqlite);
        btnadd.setOnClickListener(btnAddListener);

        db = new Db(this);
        dbread = db.getReadableDatabase();
        dbwrite = db.getWritableDatabase();

        adapter = new SimpleCursorAdapter(this,R.layout.cell006_sqlite,null,
                new String[]{"name","sex"},new int[]{R.id.textView4,R.id.textView5});
        setListAdapter(adapter);
        syncListView();
        getListView().setOnItemClickListener(sOTCL);

    }
    //删除列
    private AdapterView.OnItemClickListener sOTCL = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor c = adapter.getCursor();
            c.moveToPosition(i);

            int itemId = c.getInt(c.getColumnIndex("_id"));
            dbwrite.delete("user","_id=?",new String[]{itemId+""});
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            syncListView();
        }
    };
    private void syncListView(){
        Cursor c = dbread.query("user",null,null,null,null,null,null);
        adapter.changeCursor(c);
    }

}


//        修改数据：
//        ContentValues values = new ContentValues();
//        values.put("body", "my dear!");
//        DatabaseHelper dbhelper = new DatabaseHelper(SqliteActivity.this, "text_msg", null, 2);
////得到可写的SQLiteDatabase对象
//        SQLiteDatabase db = dbhelper.getWritableDatabase();
////调用insert方法，将数据插入数据库
////参数3：where 子句 "?"是占位符号，对应后面的"1",这和web开发时的语法是一样的
//        db.update("MSG", values, "id=?", new String[]{"1"});
//        System.out.println("更新了：hello-->my dear!");

//        查询数据：
//        DatabaseHelper dbhelper = new DatabaseHelper(SqliteActivity.this, "text_msg", null, 2);
////得到可读的SQLiteDatabase对象
//        SQLiteDatabase db = dbhelper.getReadableDatabase();
////参数1：表名
////参数2：要想显示的列
////参数3：where子句
////参数4：where子句对应的条件值
////参数5：分组方式
////参数6：having条件
////参数7：排序方式
//        Cursor cursor = db.query("MSG", new String[]{"id","body"}, "id=?",
//        new String[]{"1"}, null, null, null);
//        System.out.println("查到的数据为：");
//        while(cursor.moveToNext()){
//        int id = cursor.getInt(cursor.getColumnIndex("id"));
//        String name = cursor.getString(cursor.getColumnIndex("body"));
//        System.out.println("-->"+id+"::::::::::"+name);
//        }
