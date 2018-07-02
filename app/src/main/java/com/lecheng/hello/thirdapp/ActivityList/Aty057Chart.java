package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.lecheng.hello.thirdapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//MVAndroidChart
//https://blog.csdn.net/fuxiaoyuqing/article/details/53000545
//https://blog.csdn.net/ww897532167/article/details/74129478
public class Aty057Chart extends Activity {

    @Bind(R.id.lChart)
    LineChart mChart;
    private ArrayList<Entry> foldLineValues = new ArrayList<Entry>();
    private ArrayList<ILineDataSet> iLineDataSets = new ArrayList<ILineDataSet>();
    private LineDataSet lineDataSet;
    private LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty057_chart);
        ButterKnife.bind(this);
        setChart();
        setData(1);
    }

    private void setChart() {
//        mChart.setOnChartGestureListener(this);
//        mChart.setOnChartValueSelectedListener(this);
//        mChart.setDrawGridBackground(true);
        mChart.setTouchEnabled(false);//定位
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);//启用缩放拖动
        mChart.setDescription("");
        //横向x轴线
        mChart.getXAxis().setDrawGridLines(false);//x轴垂直竖线
        mChart.getXAxis().setDrawAxisLine(true);//x轴线是否显示
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//x轴线显示位置
        mChart.getXAxis().setTextColor(Color.parseColor("#dc9813"));
        mChart.getXAxis().setAxisLineColor(Color.parseColor("#886123"));

//        mChart.getAxisLeft().enableGridDashedLine(10f, 10f, 0f);
        mChart.getAxisLeft().setDrawGridLines(false);        //横向grid轴线 是否开启
        mChart.getAxisLeft().setAxisLineColor(Color.parseColor("#886123"));
        mChart.getAxisLeft().setTextColor(Color.parseColor("#dc9813"));
        mChart.getAxisLeft().setMaxWidth(200);
        mChart.getAxisLeft().setMinWidth(0);
        //竖向右侧y轴线
        mChart.getAxisRight().setAxisLineColor(Color.parseColor("#131313"));
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisRight().setTextColor(Color.parseColor("#131313"));

        mChart.setDrawBorders(false);
        //限制线
        LimitLine limitLine = new LimitLine(170, "170'c"); //得到限制线
        limitLine.setLineWidth(1f); //限制线宽度
        limitLine.enableDashedLine(5f, 5f, 0f);
        limitLine.setLineColor(Color.parseColor("#dc9813"));
        limitLine.setTextColor(Color.parseColor("#dc9813"));
        limitLine.setTextSize(18f);
        mChart.getAxisLeft().addLimitLine(limitLine); //Y轴添加限制线
    }

    private void setData(int iff) {

        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random()) * 170;
            foldLineValues.add(new Entry(i, val));
        }
//        for (int i = 0; i < tempList.size(); i++) {
//            foldLineValues.add(new Entry(i, tempList.get(i)));
//        }
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(foldLineValues);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
            mChart.invalidate();
        } else {
            lineDataSet = new LineDataSet(foldLineValues, "");            // create a dataset and give it a type
            lineDataSet.setDrawFilled(false);
            lineDataSet.enableDashedLine(10f, 0, 0f);            // set the line to be drawn like this "- - - - - -"
            lineDataSet.enableDashedHighlightLine(10f, 0, 0f);
            lineDataSet.setColor(Color.parseColor("#dc9813"));
            lineDataSet.setValueTextColor(Color.parseColor("#dc9813"));

            lineDataSet.setDrawCircles(false);
            lineDataSet.setLineWidth(1f);
            lineDataSet.setCircleRadius(1f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextSize(0f);
            lineDataSet.setDrawFilled(false);

            iLineDataSets.add(lineDataSet); // add the datasets

            lineData = new LineData(iLineDataSets);
            mChart.setData(lineData);
        }
    }

    @OnClick({R.id.btnAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                break;
        }
    }
}