package com.vova.mobiledevelopertask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vova.mobiledevelopertask.utils.DateUtils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class CustomAlertDialog extends AlertDialog {

    public CustomAlertDialog(@NonNull Context context) {
        super(context, R.style.MyDialogTheme);

        View title = View.inflate(context, R.layout.alert_dialog_title, null);
        setCustomTitle(title);

        View alert = View.inflate(context, R.layout.custom_alert_dialog, null);
        final TextView timeTV = (TextView) alert.findViewById(R.id.txtTime);
        setCurrentTime(timeTV);
        Button buttonOk = (Button) alert.findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(view -> dismiss());

        setView(alert);

        Observable.interval(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> setCurrentTime(timeTV));
    }

    private void setCurrentTime(TextView tv) {
        tv.setText(getContext().getString(R.string.current_time, getTime()));
    }

    private String getTime() {
        Date currentDate = GregorianCalendar.getInstance().getTime();
        return DateUtils.dateToString(currentDate);
    }


}
