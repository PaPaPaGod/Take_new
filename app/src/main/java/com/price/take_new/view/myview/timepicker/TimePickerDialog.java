package com.price.take_new.view.myview.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.price.take_new.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TimePickerDialog extends Dialog {

    public interface OnTimeSelectedListener {
        void onTimeSelected(int[] times);
    }

    private ChangeMinListener listener;

    public void setListener(ChangeMinListener listener) {
        this.listener = listener;
    }

    private Params params;

    public TimePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void setParams(TimePickerDialog.Params params) {
        this.params = params;
    }


    private static final class Params {
//        loopDay
        private boolean shadow = true;
        private boolean canCancel = true;
        private LoopView loopHour, loopMin;
        private OnTimeSelectedListener callback;
    }

    public static class Builder {
        private final Context context;
        private final TimePickerDialog.Params params;

        public Builder(Context context) {
            this.context = context;
            params = new TimePickerDialog.Params();
        }

        /**
         * 获取当前选择的时间
         *
         * @return int[]数组形式返回。例[12,30]
         */
        private final int[] getCurrDateValues() {
//            int currDay = Integer.parseInt(params.loopDay.getCurrentItemValue());
            int currHour = Integer.parseInt(params.loopHour.getCurrentItemValue());
            int currMin = Integer.parseInt(params.loopMin.getCurrentItemValue());
            return new int[]{currHour, currMin};
        }

        public TimePickerDialog create() {
            final TimePickerDialog dialog = new TimePickerDialog(context, params.shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_picker_time, null);

//            final LoopView loopDay = (LoopView) view.findViewById(R.id.loop_day_test);
//            loopDay.setCyclic(false);
//            loopDay.setArrayList(d(1, 31));
//            loopDay.setCurrentItem(0);

            final LoopView loopHour = (LoopView) view.findViewById(R.id.loop_hour);

            Calendar calendar = Calendar.getInstance();
            GregorianCalendar ca = new GregorianCalendar();

            int h = calendar.get(Calendar.HOUR);

            //修改优化边界值 by lmt 16/ 9 /12.禁用循环滑动,循环滑动有bug
            loopHour.setCyclic(false);
            loopHour.setArrayList(d(0, 24));
            loopHour.setCurrentItem(0);

            final LoopView loopMin = (LoopView) view.findViewById(R.id.loop_min);
            loopMin.setCyclic(false);
            loopMin.setArrayList(d(0, 24));
            loopMin.setCurrentItem(0);

            int am_pm = ca.get(GregorianCalendar.AM_PM);

            if(am_pm == 0){
                loopHour.setCurrentItem(h);
            }else if (am_pm == 1){
                if(h<12){
                    loopHour.setCurrentItem(12+h);
                }else {
                    loopHour.setCurrentItem(h);
                }
            }

            final LoopListener setMin = new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    int i = loopHour.getCurrentItem();
                    Log.e("currentItem",i+"");
                    if(i<23){
                        Log.e("currentItem","is Run");
                        loopMin.setCurrentItem(i+1);
                    }else if(i == 23){
                        loopMin.setCurrentItem(23);
                    }
                }
            };

            view.findViewById(R.id.tx_finish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    params.callback.onTimeSelected(getCurrDateValues());
                }
            });

            loopHour.setListener(setMin);

            Window win = dialog.getWindow();
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
            win.setGravity(Gravity.BOTTOM);
            win.setWindowAnimations(R.style.Animation_Bottom_Rising);

            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(params.canCancel);
            dialog.setCancelable(params.canCancel);

//            params.loopDay = loopDay;
            params.loopHour = loopHour;
            params.loopMin = loopMin;
            dialog.setParams(params);

            return dialog;
        }


        public Builder setOnTimeSelectedListener(OnTimeSelectedListener onTimeSelectedListener) {
            params.callback = onTimeSelectedListener;
            return this;
        }


        /**
         * 将数字传化为集合，并且补充0
         *
         * @param startNum 数字起点
         * @param count    数字个数
         * @return
         */
        private static List<String> d(int startNum, int count) {
            String[] values = new String[count];
            for (int i = startNum; i < startNum + count; i++) {
                String tempValue = (i < 10 ? "0" : "") + i;
                values[i - startNum] = tempValue;
            }
            return Arrays.asList(values);
        }
    }

    private interface ChangeMinListener{
        void changeMin(int item);
    }
}
