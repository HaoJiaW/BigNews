package com.bignews.bignews;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

public class HandleMessage {

    Context mcontext;
    String message;
    private ProgressDialog pdialog;
    private AlertDialog.Builder ddialog;

    public HandleMessage(Context context, String message){
        mcontext=context;
        this.message=message;
    }

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                //弹出进度对话框
                case 1:
                    pdialog=new ProgressDialog(mcontext);
                    pdialog.setTitle("提示");
                    pdialog.setMessage("数据加载中,请稍候......");
                    pdialog.setCancelable(false);
                    pdialog.setIndeterminate(true);
                    pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pdialog.show();

                    break;

                    //结束对话框
                case 2:
                    if (pdialog!=null && pdialog.isShowing()){
                        pdialog.dismiss();
                    }
                    break;

                case 3:
                    ddialog=new AlertDialog.Builder(mcontext);
                    ddialog.setTitle("提示");
                    ddialog.setMessage(message);
                    ddialog.setCancelable(false);
                    ddialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

                    break;
                    default:
                        break;
            }
        }

    };

}