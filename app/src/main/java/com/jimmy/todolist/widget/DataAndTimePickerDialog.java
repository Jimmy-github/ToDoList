package com.jimmy.todolist.widget;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.jimmy.todolist.activity.AddToDoActivity;

import java.util.Calendar;

/**
 * Created by zhangtianjie on 2017/8/7.
 */

public class DataAndTimePickerDialog {

    public static void showDataAndTimePickerDialog(Context context,OnDataAndTimePickerListener onDataAndTimePickerListener) {

        mOnDataAndTimePickerListener=onDataAndTimePickerListener;
        showDatePickerDialog(context);

    }


    static int pickerYear, pickerMonth, pickerDay;
    static int pickerHour, pickerMinute;

    private static void showDatePickerDialog(final Context context) {
        Calendar mCalendar = Calendar.getInstance();
        int nowYear = mCalendar.get(Calendar.YEAR);
        int nowMonth = mCalendar.get(Calendar.MONTH);
        int nowDay = mCalendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog mDatePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Toast.makeText(AddToDoActivity.this,year+"/"+month+"/"+dayOfMonth,Toast.LENGTH_SHORT).show();
                showTimePickerDialog(context);
                pickerYear = year;
                pickerMonth = month + 1;
                pickerDay = dayOfMonth;
            }
        }, nowYear, nowMonth, nowDay);
        mDatePickerDialog.show();
    }

    private static void showTimePickerDialog(Context context) {

        TimePickerDialog mTimePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                pickerHour = hourOfDay;
                pickerMinute = minute;

                String textTime = pickerYear + "/" + pickerMonth + "/" + pickerDay + " " + pickerHour + ":" + pickerMinute;

                if (mOnDataAndTimePickerListener != null) {
                    mOnDataAndTimePickerListener.onDataAndTimePicker(textTime);
                }
            }
        }, 0, 0, true);

        mTimePickerDialog.show();
    }

    public static OnDataAndTimePickerListener mOnDataAndTimePickerListener;

    public void setOnDataAndTimePickerListener(OnDataAndTimePickerListener mOnDataAndTimePickerListener) {
        this.mOnDataAndTimePickerListener = mOnDataAndTimePickerListener;
    }

    public interface OnDataAndTimePickerListener {
        void onDataAndTimePicker(String time);
    }
}
