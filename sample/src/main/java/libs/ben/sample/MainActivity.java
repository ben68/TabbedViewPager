package libs.ben.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.w3c.dom.Text;

import java.util.Arrays;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(new String[]{"text title", "icon title"})));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        intent.setClass(MainActivity.this, TextTitleActivity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, IconTitleActivity.class);
                        break;
                    default:
                }
                startActivity(intent);
            }
        });
    }
}
