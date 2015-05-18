package libs.ben.sample;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import libs.ben.tvp.TabbedViewPagerAdapter;
import libs.ben.tvp.TabbedViewPagerWithAppBarActivity;


public class MainActivity extends TabbedViewPagerWithAppBarActivity {

    private final CharSequence[] titles = {
            "首頁",
            "熱門文章",
            "分類瀏覽",
            "設定"
    };

    @Override
    protected List<CharSequence> getTitles() {
        return Arrays.asList(titles);
    }

    @Override
    protected List<CharSequence> getIconTitles() {
        final CharSequence[] iconTitles = {
                getIconTitleFromResource(android.R.drawable.ic_menu_add),
                getIconTitleFromResource(android.R.drawable.ic_menu_agenda),
                getIconTitleFromResource(android.R.drawable.ic_menu_always_landscape_portrait),
                getIconTitleFromResource(android.R.drawable.ic_menu_call),
        };
        return Arrays.asList(iconTitles);
    }

    @Override
    protected List<CharSequence> getSelectedIconTitles() {
        final CharSequence[] selectedIconTitles = {
                getIconTitleFromResource(android.R.drawable.ic_menu_delete),
                getIconTitleFromResource(android.R.drawable.ic_menu_camera),
                getIconTitleFromResource(android.R.drawable.ic_menu_close_clear_cancel),
                getIconTitleFromResource(android.R.drawable.ic_menu_compass),
        };
        return Arrays.asList(selectedIconTitles);
    }

    @Override
    protected PagerAdapter getAdapter() {
        List<CharSequence> titles = getIconTitles();
        if(titles == null)
            titles = getTitles();
        PagerAdapter adapter = new TabbedViewPagerAdapter(titles){
            /**
             * Instantiate the {@link View} which should be displayed at {@code position}. Here we
             * inflate a layout from the apps resources and then change the text view to signify the position.
             */
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // Inflate a new layout from our resources
                View view = getLayoutInflater().inflate(R.layout.pager_item,
                        container, false);
                // Add the newly created View to the ViewPager
                container.addView(view);

                // Retrieve a TextView from the inflated View, and update it's text
                TextView title = (TextView) view.findViewById(R.id.item_title);
                title.setText(String.valueOf(position + 1));

//            Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");

                // Return the View
                return view;
            }
        };
        return adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
