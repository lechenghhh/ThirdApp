package com.lecheng.hello.thirdapp.ActivityList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lecheng.hello.thirdapp.R;

public class Aty009View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity009);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view9, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//   public static class PlaceholderFragment extends Fragment{
//
//       @Nullable
//       @Override
//       public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View v = inflater.inflate(R.layout.frgment_view2,container,false);
//
//            return v;
//       }
//   }
}
