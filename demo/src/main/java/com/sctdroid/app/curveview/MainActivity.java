package com.sctdroid.app.curveview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sctdroid.app.uikit.CurveView;
import com.sctdroid.app.uikit.CurveView.Gravity;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CurveView curveView = (CurveView) findViewById(R.id.curve_view);
        curveView.setAdapter(mAdapter);


        TextView button = (TextView) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    CurveView.Adapter mAdapter = new CurveView.Adapter() {

        String text = "吾生也有涯，而知也无涯";

        /**
         * @return 点的数量
         */
        @Override
        public int getCount() {
            return 7;
        }

        /**
         * level 是 y 轴高度，在 minLevel 和 maxLevel 之间
         * @param position
         * @return 返回当前 position 的 level
         */
        @Override
        public int getLevel(int position) {
            return (int) (15 + (Math.random() * 20));
        }

        /**
         * @return y 轴下限
         */
        @Override
        public int getMinLevel() {
            return 15;
        }

        /**
         * @return y 轴上限
         */
        @Override
        public int getMaxLevel() {
            return 35;
        }

        /**
         * 设置点上的文字，每个mark是一个，可同时设置点的 8 个方向的文字
         * 注意: Gravity 应使用 CurveView.Gravity 类
         *
         * @param position
         * @return
         */
        @Override
        public Set<CurveView.Mark> onCreateMarks(int position) {
            Set<CurveView.Mark> marks = new HashSet<CurveView.Mark>();
            CurveView.Mark mark = new CurveView.Mark(getLevel(position) + "°", Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 8, 0, 0);
            CurveView.Mark mark1 = new CurveView.Mark(getLevel(position) + "°", Gravity.START | Gravity.CENTER_HORIZONTAL, 0, 0, 0, 8);
            marks.add(mark);
            marks.add(mark1);
            return marks;
        }

        /**
         * 获取第 i 个点 x 轴上的文字
         * @param i
         * @return
         */
        @Override
        public String getXAxisText(int i) {
            return text.substring(i, i + 1);
        }
    };

}
