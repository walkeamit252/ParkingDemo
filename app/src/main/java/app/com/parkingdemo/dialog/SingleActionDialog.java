package app.com.parkingdemo.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.util.Objects;
import app.com.parkingdemo.R;


public class SingleActionDialog extends Dialog implements View.OnClickListener {
    @SuppressLint("StaticFieldLeak")
    private static SingleActionDialog singleActionDialog;
    private String message;
    private String title;
    private String textPositive;
    private Button btnDialogPositive;
    private DialogButtonListener dialogButtonListener;

    private SingleActionDialog(Context context, String title, String message, String textPositive) {

        super(context);

        this.title = title;

        this.message = message;

        this.textPositive = textPositive;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_single_action);

        setCanceledOnTouchOutside(true);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        initViews();

        initListeners();
    }

    private void initViews() {
        TextView txtDialogTitle;
        TextView txtDialogMessage;
        txtDialogTitle =  findViewById(R.id.txt_dialog_title);
        txtDialogMessage =  findViewById(R.id.txt_dialog_message);

        btnDialogPositive =  findViewById(R.id.btn_dialog_positive);

        if (!TextUtils.isEmpty(title))
            txtDialogTitle.setText(title);
        if (!TextUtils.isEmpty(message))
            txtDialogMessage.setText(message);
        if (!TextUtils.isEmpty(textPositive))
            btnDialogPositive.setText(textPositive);

    }

    private void initListeners() {
        btnDialogPositive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_dialog_positive){
            dismiss();
            if (dialogButtonListener != null) {
                dialogButtonListener.onPositiveButtonClicked();
            }
        }
    }

    public void setDialogButtonListener(DialogButtonListener dialogButtonListener) {
        this.dialogButtonListener = dialogButtonListener;
    }

    public static SingleActionDialog showAlert(final AppCompatActivity abstractActivity, final String title, final String message, final String textPositive) {

        singleActionDialog = new SingleActionDialog(abstractActivity, title, message, textPositive);

        if (abstractActivity != null && !abstractActivity.isFinishing()) {
            singleActionDialog.show();
        }

        return singleActionDialog;
    }

    public static void dismissAlert() {
        if (singleActionDialog != null && singleActionDialog.isShowing()) {
            singleActionDialog.dismiss();
        }
    }

    public static void showAlert( final Activity activity, final String message){
        if(singleActionDialog != null && singleActionDialog.isShowing()){
            singleActionDialog.dismiss();
        }
        if(!activity.isFinishing()){
            singleActionDialog = new SingleActionDialog(activity, activity.getString(R.string.alert_title), message, activity.getString(R.string.btn_ok));
            singleActionDialog.show();
        }
    }

    public interface DialogButtonListener {
        void onPositiveButtonClicked();
    }


}
