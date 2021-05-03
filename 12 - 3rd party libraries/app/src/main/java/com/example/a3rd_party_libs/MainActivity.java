package com.example.a3rd_party_libs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private GraphicalView mChart;
    private XYMultipleSeriesDataset mDataSet = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentDataSet;
    private XYSeriesRenderer mCurrentRenderer;

    @BindView(R.id.chart)
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void initChart() {
        mCurrentDataSet = new XYSeries("Sample DataSet");
        mDataSet.addSeries(mCurrentDataSet);
        mCurrentDataSet.add(1,1);
        mCurrentDataSet.add(2,2);
        mCurrentDataSet.add(3,1);
        mCurrentDataSet.add(4,2);
        mCurrentDataSet.add(5,5);

        mCurrentRenderer = new XYSeriesRenderer();
        mCurrentRenderer.setLineWidth(5);
        mCurrentRenderer.setColor(Color.RED);
        mRenderer.addSeriesRenderer(mCurrentRenderer);
    }

    protected void onResume(){
        super.onResume();
        // LinearLayout layout = findViewById(R.id.chart);
        if(mChart == null){
            initChart();
            mChart = ChartFactory.getLineChartView(this, mDataSet, mRenderer);
            layout.addView(mChart);
        } else {
            mChart.repaint();
        }
    }
}