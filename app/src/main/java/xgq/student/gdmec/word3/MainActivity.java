package xgq.student.gdmec.word3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private CheckBox checkBox;
    private CheckBox checkBox2;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        mTextView = (TextView)findViewById(R.id.textView4);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().trim().equals("")){
                    if(checkBox.isChecked()||checkBox2.isChecked()){
                        double weight = 0;
                        weight = Double.parseDouble(editText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("-----------评估结果-----------\n");
                        if(checkBox.isChecked()){
                            sb.append("男性标准身高:");
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result+"(厘米)\n");
                        }
                        if(checkBox2.isChecked()){
                            sb.append("女性标准身高:");
                            double result = evaluateHeight(weight,"女");
                            sb.append((int)result+"(厘米)\n");
                        }
                       String result = sb.toString();
                        mTextView.setText(result);
                    }else{
                        showMessage("请选择性别!");
                    }
                }else{
                    showMessage("请输入体重!");
                }
            }
        });

    }
    private void showMessage(String message){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统消息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    private double evaluateHeight(double weight,String sex) {
        double height;
        if (sex == "男") {
            height = 170 - (62 - weight) / 0.6;
        } else {
            height = 158 - (52 - weight) / 0.5;
        }
        return height;
    }
}
