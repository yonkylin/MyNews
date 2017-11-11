package yonky.mynews.ui.zhihu.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import yonky.mynews.R;
import yonky.mynews.base.SimpleActivity;
import yonky.mynews.component.RxBus;
import yonky.mynews.util.DateUtil;

/**
 * Created by Administrator on 2017/10/27.
 */

public class CalendarActivity extends SimpleActivity {
    @BindView(R.id.view_calendar)
    MaterialCalendarView mCalendar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    CalendarDay mDate;

    @Override
    protected int getLayout() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar,"选择日期");
        mCalendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2013,5,20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(),
                        DateUtil.getCurrentMonth(),DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });
    }

    @OnClick(R.id.tv_calendar_enter)
    void chooseDate(){
        if(mDate !=null){
            RxBus.getDefault().post(mDate);
        }
        finish();
    }
}
