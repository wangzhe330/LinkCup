package com.example.wz.linkcup;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Map;

import library.FloatingActionButton;
import library.FloatingActionMenu;
import library.SubActionButton;

//import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

//ActionBarActivity
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private int fragment_rec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();



        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //wz add fragment
        /*
        FragmentManager fm = getSupportFragmentManager();
        FloatingLinkFragment fm_float_link = new FloatingLinkFragment();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add( fm_float_link , "1st").commit();*/


        //wz add floatingButton
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable( getResources().getDrawable(R.drawable.floating_main_128px) );

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(getResources().getDrawable(R.drawable.floating_link_72px) );
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageDrawable(getResources().getDrawable(R.drawable.floating_state_72px));
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageDrawable(getResources().getDrawable(R.drawable.floating_line_72px));
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                        // ...
                .attachTo(actionButton)
                .build();

        button1.setOnClickListener(new FloatingLinkButtonClickListener());
        button2.setOnClickListener(new FloatingStateButtonClickListener());
        button3.setOnClickListener(new FloatingRecordButtonClickListener());
    }
    //wz the listener of the floating buttons(name:link)
    class FloatingLinkButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View arg0){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                  .replace(R.id.container, new FloatingLinkFragment())
                  .commit();
            fragment_rec=1;
        }
    }
    class FloatingStateButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View arg0){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container,new FloatingStateFragment() )
                    .commit();
            fragment_rec=2;
        }
    }
    class FloatingRecordButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View arg0){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container,new FloatingRecordFragment() )
                    .commit();
            fragment_rec=3;
        }
    }
    /*
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        if(fragment_rec!=0){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container,new PlaceholderFragment() )
                    .commit();
            fragment_rec=0;
        }
    }*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("onKeyDown()");
            if(fragment_rec!=0) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new FloatingLinkFragment())
                        .commit();

                fragment_rec = 0;
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy()");
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
            //wz
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            //Button bn = (Button) rootView.findViewById(R.id.button_test);
            //bn.setText("ok");
            return rootView;
        }




        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public static class FloatingLinkFragment extends Fragment{
        public FloatingLinkFragment(){

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_floating_link, container, false);

            return rootView;
        }
    }
    public static class FloatingStateFragment extends Fragment{
        public FloatingStateFragment(){

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_floating_state, container, false);
            return rootView;
        }
    }
    public static class FloatingRecordFragment extends Fragment{
        public FloatingRecordFragment(){

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_floating_record, container, false);

            //wz line chart
            LineChart chart = (LineChart)rootView.findViewById(R.id.chart);

            //chart.setDescription("water of today");
            //chart.setDrawBorders( true );
            //chart.invalidate();
            //LineData water_data = getLineData(36,100);
            LineData mLineData = getLineData(24, 100);
            showChart(chart, mLineData,Color.rgb(52, 73, 94));//rgb(26, 188, 156)rgb(114, 188, 223)

            BarChart mBarChart = (BarChart)rootView.findViewById(R.id.chart_bar);
            BarData mBarData =getBarData(24,100);
            showBarChart(mBarChart,mBarData,Color.rgb(52, 73, 94));
            return rootView;
        }
        //set style
        private void showChart(LineChart lineChart, LineData lineData, int color) {
            lineChart.setDrawBorders(true);

            // no description text
            lineChart.setDescription("");

            lineChart.setNoDataTextDescription("You need to provide data for the chart.");

            // enable / disable grid background
            lineChart.setDrawGridBackground(false); //
            lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF);

            // enable touch gestures
            lineChart.setTouchEnabled(false);

            // enable scaling and dragging
            lineChart.setDragEnabled(true);
            lineChart.setScaleEnabled(false);
            // if disabled, scaling can be done on x- and y-axis separately
            lineChart.setPinchZoom(false);//
            lineChart.setBackgroundColor(color);

            // add data
            lineChart.setData(lineData);

            // get the legend (only possible after setting data)
            Legend mLegend = lineChart.getLegend();

            // modify the legend ...
            // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
            //mLegend.setForm(Legend.LegendForm.CIRCLE);
            mLegend.setForm(Legend.LegendForm.CIRCLE);
            mLegend.setFormSize(6f);
            mLegend.setTextColor(Color.WHITE);
//      mLegend.setTypeface(mTf);

            lineChart.animateX(2500);
        }
        private LineData getLineData(int count, float range) {
            ArrayList<String> xValues = new ArrayList<String>();
            for (int i = 0; i < count; i++) {

                xValues.add("" + i);
            }


            ArrayList<Entry> yValues = new ArrayList<Entry>();

            for (int i = 0; i < count; i++) {
                float value = (float) (i*20);//(float) (Math.random() * range) + 3;
                yValues.add(new Entry(value, i));
            }

            // create a dataset and give it a type

            LineDataSet lineDataSet = new LineDataSet(yValues, "test line chart" );
            // mLineDataSet.setFillAlpha(110);
            // mLineDataSet.setFillColor(Color.RED);


            lineDataSet.setLineWidth(1.75f);
            lineDataSet.setCircleSize(3f);
            lineDataSet.setColor(Color.WHITE);
            lineDataSet.setCircleColor(Color.WHITE);
            lineDataSet.setHighLightColor(Color.WHITE);

            ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
            lineDataSets.add(lineDataSet); // add the datasets

            // create a data object with the datasets
            LineData lineData = new LineData(xValues, lineDataSets);

            return lineData;
        }
        private void showBarChart(BarChart barChart,BarData barData,int color){
            barChart.setDrawBorders(false);
            barChart.setDescription("");
            barChart.setNoDataText("there is no data");
            barChart.setTouchEnabled(false);
            barChart.setDragEnabled(false);
            barChart.setScaleEnabled(false);
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(false);
            barChart.setBackgroundColor(color);

            //barChart.setBackground();
            barChart.setDrawBarShadow(false);
            barChart.setData(barData);

            Legend mLegend = barChart.getLegend();
            mLegend.setForm(Legend.LegendForm.CIRCLE);
            mLegend.setFormSize(6f);
            mLegend.setTextColor(Color.WHITE);

            barChart.animateX(2500);
        }
        private BarData getBarData(int count,float range){
            ArrayList<String> xValues = new ArrayList<String>();
            for (int i = 0; i < count; i++) {
                xValues.add("" + i);
            }

            ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();

            for (int i = 0; i < count; i++) {
                float value = (float) (Math.random() * range) + 3;
                yValues.add(new BarEntry(value, i));
            }


            BarDataSet barDataSet = new BarDataSet(yValues, "bar chart test");

            barDataSet.setColor(Color.rgb(114, 188, 223));

            ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
            barDataSets.add(barDataSet); // add the datasets

            BarData barData = new BarData(xValues, barDataSets);

            return barData;
        }
    }
}
