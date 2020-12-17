package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SubFinalActivity extends AppCompatActivity {

    PieChart pieChart;

    private static final float FONT_SIZE = 15;
    private LinearLayout linearLayout;

    String[] names = new String[]{"유지민", "이지희", "기권"};
    int[] count = new int[]{14, 2, 4};  //총 20명의 투표자

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_final);

        textview();

        pieChart = (PieChart)findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        for(int i = 0; i < names.length; i++){
            yValues.add(new PieEntry(count[i],names[i]));
        }

        Description description = new Description();
        description.setText("... 투표결과"); //여기에 어떤 투표인지 보여주는걸 추가하면 좋을듯합니다
        description.setTextSize(15);
        pieChart.setDescription(description);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setCenterTextSize(FONT_SIZE);
        pieChart.setData(data);


    }
    public void textview() {
        for (int i = 0; i < names.length; i++) {
            linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
            TextView view1 = new TextView(this);
            view1.setId(i);
            //view1.setText(names[i]);
            view1.setText(names[i] + ':' + count[i]);
            view1.setTextSize(FONT_SIZE);
            view1.setTextColor(Color.BLACK);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            view1.setLayoutParams(lp);

            linearLayout.addView(view1);
        }


    }
}