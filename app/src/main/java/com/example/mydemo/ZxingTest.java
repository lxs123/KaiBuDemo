package com.example.mydemo;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.zhy.autolayout.utils.AutoUtils;

import org.w3c.dom.Comment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ZxingTest extends BaseActivity {

    private CheckBox cbQR;
    private CheckBox cbDM;
    private CheckBox cbBar;
    private EditText editContent, editContent1;
    private ImageView imgv;
    private TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.zxing_test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("打印");
        init();

    }

    private void init() {
        cbQR = (CheckBox) findViewById(R.id.qr_code_click);
        cbDM = (CheckBox) findViewById(R.id.dm_code_click);
        cbBar = (CheckBox) findViewById(R.id.bar_code_click);
        editContent = (EditText) findViewById(R.id.code_content);
        editContent1 = (EditText) findViewById(R.id.code_content1);
        imgv = (ImageView) findViewById(R.id.imgv);
        tvContent = (TextView) findViewById(R.id.code_print_content);
        cbQR.setChecked(true);
        editContent.setText("");
        editContent.setHint("请输入二维码（QR）内容");
        if (cbQR.isChecked())
            cbQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!cbQR.isChecked()) {
                        cbQR.setChecked(true);
                    } else {
                        editContent.setText("");
                        editContent.setHint("请输入二维码（QR）内容");
                        imgv.setImageResource(R.color.white);
                    }
                    cbDM.setChecked(false);
                    cbBar.setChecked(false);
                    editContent.setVisibility(View.VISIBLE);
                    editContent1.setVisibility(View.GONE);
                    editContent.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            });
        cbDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cbDM.isChecked()) {
                    cbDM.setChecked(true);
                } else {
                    editContent.setText("");
                    editContent.setHint("请输入二维码（DM）内容");
                    imgv.setImageResource(R.color.white);
                }
                cbQR.setChecked(false);
                cbBar.setChecked(false);
                editContent.setVisibility(View.GONE);
                editContent1.setVisibility(View.VISIBLE);
                editContent1.setInputType(InputType.TYPE_TEXT_VARIATION_PHONETIC);
//                editContent1.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        String editable = editContent1.getText().toString();
//                        String str = stringFilter(editable.toString());
//                        if (!editable.equals(str)) {
//                            editContent1.setText(str);
//                            //设置新的光标所在位置
//                            editContent1.setSelection(str.length());
//                        }
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//
//                    }
//                });

            }
        });
        cbBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cbBar.isChecked()) {
                    cbBar.setChecked(true);
                } else {
                    editContent.setText("");
                    editContent.setHint("请输入条形码内容");
                    imgv.setImageResource(R.color.white);
                }
                cbDM.setChecked(false);
                cbQR.setChecked(false);
                editContent.setVisibility(View.VISIBLE);
                editContent1.setVisibility(View.GONE);
                editContent.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        });
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、数字
//        \u4E00-\u9FA5 汉字
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public void onPrintClick(View view) {
        if (TextUtils.isEmpty(editContent.getText().toString()) && editContent.getVisibility() == View.VISIBLE) {
            Toast.makeText(ZxingTest.this, "请输入编码内容", Toast.LENGTH_SHORT).show();
            imgv.setImageResource(R.color.white);
            return;
        }
        if (TextUtils.isEmpty(editContent1.getText().toString()) && editContent1.getVisibility() == View.VISIBLE) {
            Toast.makeText(ZxingTest.this, "请输入编码内容", Toast.LENGTH_SHORT).show();
            imgv.setImageResource(R.color.white);
            return;
        }
        ViewGroup.LayoutParams params = imgv.getLayoutParams();
        Bitmap bitmap = null;
        if (cbQR.isChecked()) {
//            bitmap = DMCodeUtils.createDMCodeBitmap(editContent.getText().toString(), 580, 580);
            bitmap = DMCodeUtils.createCodeBitmap(editContent.getText().toString(), 580, 580, BarcodeFormat.QR_CODE);
            tvContent.setText(editContent.getText().toString());
        } else if (cbDM.isChecked()) {
            Log.e("######",params.width+"//////"+AutoUtils.getPercentHeightSize(params.width));
            Log.e("######",params.height+"//////"+AutoUtils.getPercentHeightSize(params.height));
            bitmap = DMCodeUtils.createDMCodeBitmap(editContent1.getText().toString(), AutoUtils.getPercentHeightSize(params.width),AutoUtils.getPercentHeightSize(params.height));
            tvContent.setText(editContent1.getText().toString());
        } else if (cbBar.isChecked()) {
            bitmap = DMCodeUtils.createBarCodeBitmap(editContent.getText().toString(), 580, 400, false);
            tvContent.setText(editContent.getText().toString());
        }
        if (bitmap == null) {
            Toast.makeText(ZxingTest.this, "请选择编码类型", Toast.LENGTH_SHORT).show();
            return;
        }
        imgv.setImageBitmap(bitmap);
    }
}
