package libs.ben.sample;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import libs.ben.tvp.TabbedViewPagerAdapter;
import libs.ben.tvp.TabbedViewPagerWithAppBarActivity;

public class IconTitleActivity extends TabbedViewPagerWithAppBarActivity {

    private final CharSequence[] titles = {
            "Page 1",
            "Page 2",
            "Page 3",
            "Page 4"
    };

    @Override
    protected List<CharSequence> getTitles() {
        return Arrays.asList(titles);
    }

    private List<CharSequence> iconTitles = null;
    @Override
    protected List<CharSequence> getIconTitles() {
        if(iconTitles == null) {
            CharSequence[] icons = {
                    getIconTitleFromResource(R.mipmap.ic_action_person_normal),
                    getIconTitleFromResource(R.mipmap.ic_action_person_normal),
                    getIconTitleFromResource(R.mipmap.ic_action_person_normal),
                    getIconTitleFromResource(R.mipmap.ic_action_person_normal),
            };
            iconTitles = Arrays.asList(icons);
        }
        return iconTitles;
    }

    private List<CharSequence> selectedIconTitles = null;
    @Override
    protected List<CharSequence> getSelectedIconTitles() {
        if(selectedIconTitles == null) {
            CharSequence[] icons = {
                    getIconTitleFromResource(R.mipmap.ic_action_person),
                    getIconTitleFromResource(R.mipmap.ic_action_person),
                    getIconTitleFromResource(R.mipmap.ic_action_person),
                    getIconTitleFromResource(R.mipmap.ic_action_person),
            };
            selectedIconTitles = Arrays.asList(icons);
        }
        return selectedIconTitles;
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
}
