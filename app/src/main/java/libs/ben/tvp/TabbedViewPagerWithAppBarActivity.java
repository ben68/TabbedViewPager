package libs.ben.tvp;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.List;

import libs.ben.tvp.SlidingTabLayout.TabColorizer;

public abstract class TabbedViewPagerWithAppBarActivity
        extends AppCompatActivity
        implements OnPageChangeListener {

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;
    abstract protected List<CharSequence> getTitles();
    abstract protected PagerAdapter getAdapter();
    abstract protected List<CharSequence> getIconTitles();
    protected List<CharSequence> getSelectedIconTitles(){
        return getIconTitles();
    }

    protected CharSequence getIconTitleFromResource(int resourceId){
        Drawable image;
        if(Build.VERSION.SDK_INT<21)
            image=getResources().getDrawable(resourceId);
         else image=getResources().getDrawable(resourceId, getTheme());
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    protected TabColorizer getTabColorizer(){
        return new TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tab_bar_indicator_color);
            }

            @Override
            public int getDividerColor(int position) {
                if (getIconTitles() != null)
                    return getResources().getColor(R.color.tab_bar_color);
                else return getResources().getColor(R.color.tab_bar_indicator_color);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_view_pager_with_app_bar);

        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
//        bar.setNavigationIcon(R.mipmap.ic_launcher);
//        bar.setLogo(R.mipmap.ic_launcher);
        if(getIconTitles()!=null)
            bar.setTitle(getTitles().get(0));
        setSupportActionBar(bar);
        if(getSupportParentActivityIntent()!=null)
            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);

        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(getAdapter());
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab, 0);
        mSlidingTabLayout.setCustomTabColorizer(getTabColorizer());
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setOnPageChangeListener(this);
        if(getSelectedIconTitles()!=null)
            mSlidingTabLayout.setTabTitle(0, getSelectedIconTitles().get(0));
        // END_INCLUDE (setup_slidingtablayout)
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    private int selectedPostion=0;
    @Override
    public void onPageSelected(int position) {
        if (getIconTitles() == null || getSelectedIconTitles() == null)
            return;

        mSlidingTabLayout
                .setTabTitle(position, getSelectedIconTitles().get(position));
        mSlidingTabLayout
                .setTabTitle(selectedPostion, getIconTitles().get(selectedPostion));
        selectedPostion = position;
        getSupportActionBar()
                .setTitle(getTitles().get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
