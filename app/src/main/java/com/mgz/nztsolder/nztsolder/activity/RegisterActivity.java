package com.mgz.nztsolder.nztsolder.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mgz.nztsolder.nztsolder.R;
import com.mgz.nztsolder.nztsolder.basictool.BaseActivity;
import com.mgz.nztsolder.nztsolder.network.HttpStringResponseHandler;
import com.mgz.nztsolder.nztsolder.network.reponse.CodeResponse;
import com.mgz.nztsolder.nztsolder.network.reponse.GetCityReponse;
import com.mgz.nztsolder.nztsolder.network.reponse.GetCountryReponse;
import com.mgz.nztsolder.nztsolder.network.reponse.RegisterResponse;
import com.mgz.nztsolder.nztsolder.network.request.CodeRequest;
import com.mgz.nztsolder.nztsolder.network.request.GetCityRequest;
import com.mgz.nztsolder.nztsolder.network.request.GetCountryRequest;
import com.mgz.nztsolder.nztsolder.network.request.RegisterRequest;
import com.mgz.nztsolder.nztsolder.utils.Logger;
import com.mgz.nztsolder.nztsolder.utils.MyOKHttp;
import com.mgz.nztsolder.nztsolder.utils.StorageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by john on 2017/7/15.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private EditText mobileNum;
    private EditText code;
    private TextView codeTimer;
    private EditText solderName;
    private EditText password;
    private EditText passwordConfirm;
    private CheckBox agreePro;
    private Button confirm;
    private TextView province;
    private TextView city;
    private TextView country;
    private EditText detailAddr;
    private EditText telephone;
    private EditText zhifubao;
    private EditText weixin;
    private TextView bankType;
    private EditText bankCard;
    private ListView selectList;
    private RelativeLayout listLayout;
    private CodeCount codeCount = new CodeCount(60000, 1000);


    private String[] bank = new String[] {"中国银行", "建设银行", "农业银行", "交通银行", "工商银行", "广西农村信用社"};
    private ArrayList<String> cityList;
    private ArrayList<String> countryList;
    private SimpleAdapter provinceAdapter;
    private SimpleAdapter cityAdapter;
    private SimpleAdapter countryAdapter;
    private SimpleAdapter bankAdapter;

    private int listType;
    private int selectBank;
    private int selectCity;
    private int selectCountry;

    @Override
    public int getRecoureId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mobileNum = (EditText) findViewById(R.id.et_mobile_num);
        code = (EditText) findViewById(R.id.et_code);
        codeTimer = (TextView) findViewById(R.id.tv_timer);
        solderName = (EditText) findViewById(R.id.et_name);
        password = (EditText) findViewById(R.id.et_password);
        passwordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        agreePro = (CheckBox) findViewById(R.id.cb_agree);
        confirm = (Button) findViewById(R.id.btn_confirm);
        province  = (TextView) findViewById(R.id.tv_province);
        city = (TextView) findViewById(R.id.tv_city);
        country = (TextView) findViewById(R.id.tv_country);
        detailAddr = (EditText) findViewById(R.id.et_addr_detail);
        telephone = (EditText) findViewById(R.id.et_telephone);
        zhifubao = (EditText) findViewById(R.id.et_zhifubao);
        weixin = (EditText) findViewById(R.id.et_weixin);
        bankType = (TextView) findViewById(R.id.tv_bank_type);
        bankCard = (EditText) findViewById(R.id.et_bank);
        selectList = (ListView) findViewById(R.id.lv_select);
        listLayout = (RelativeLayout) findViewById(R.id.layout_list_bg);
    }

    @Override
    public void setOnClickListner() {
        codeTimer.setOnClickListener(this);
        confirm.setOnClickListener(this);
        agreePro.setOnClickListener(this);
        View[] views = new View[] {codeTimer, confirm, agreePro, province, city, country, bankType, listLayout};
        quickSetClickListener(views, this);
        selectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (listType) {
                    case 0:
                        listLayout.setVisibility(View.GONE);
                        break;
                    case 1:
                        selectCity = position + 1;
                        city.setText(cityList.get(position));
                        listLayout.setVisibility(View.GONE);
                        break;
                    case 2:
                        selectCountry = position + 1;
                        country.setText(countryList.get(position));
                        listLayout.setVisibility(View.GONE);
                        break;
                    case 3:
                        selectBank = position + 1;
                        bankType.setText(bank[position]);
                        listLayout.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == codeTimer) {   //请求验证码
            String mobilephoneNum = mobileNum.getText().toString().trim();
            if (mobilephoneNum.length() == 11) {
                codeTimer.setEnabled(false);
                codeCount.start();
                requireCode(mobilephoneNum);
            } else {
                Toast.makeText(getApplicationContext(), "请输入11位手机号码", Toast.LENGTH_SHORT).show();
            }
        } else if (v == confirm) {  //完成注册
            Logger.e("confirm", "press");
            if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
                Toast.makeText(getApplicationContext(), "两次输入的密码不一致，请确认", Toast.LENGTH_LONG).show();
            } else {
                String phoneNum = mobileNum.getText().toString().trim();
                String codeNum = code.getText().toString().trim();
                String password = passwordConfirm.getText().toString().trim();
                registerConfirm(phoneNum, codeNum, password);
            }
        } else if (v == agreePro) {
            Logger.e("agree", "press");
            if (agreePro.isChecked()) {
                confirm.setEnabled(true);
                confirm.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                confirm.setEnabled(false);
                confirm.setBackgroundColor(getResources().getColor(R.color.login_text_color));
            }
        } else if (v == bankType) {
            bankTouchEvent();
        } else if (v == province) {
            provinceTouchEvent();
        } else if (v == city) {
            cityTouchEvent();
        } else if (v == country) {
            countryTouchEvent();
        } else if (v == listLayout) {
            listLayout.setVisibility(View.GONE);
        }
    }

    private void requireCode(String phoneNum) {
        CodeRequest codeRequest = new CodeRequest(phoneNum, 1);
        MyOKHttp.postToBase("requireCode", codeRequest, new HttpStringResponseHandler<CodeResponse>(getApplicationContext(), CodeResponse.class, true) {
            @Override
            public void onSuccess(CodeResponse response) {
                super.onSuccess(response);
                if (response.getSuccess() == 1) {
                    code.setText(response.getCodeNum());
                } else if (response.getSuccess() == -1) {
                    Toast.makeText(getApplicationContext(), "您操作过于频繁，请稍候", Toast.LENGTH_LONG).show();
                    codeTimer.setEnabled(false);
                    codeCount.start();
                }
            }
        });
    }

    private void provinceTouchEvent() {
        if (provinceAdapter == null) {
            List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
            Map<String, Object> mapTemp = new HashMap<String, Object>();
            mapTemp.put("province", "广西");
            listTemp.add(mapTemp);
            provinceAdapter = new SimpleAdapter(this, listTemp, R.layout.item_simple, new String[] {"province"}, new int[] {R.id.text});
            selectList.setAdapter(provinceAdapter);
            listType = 0;
            listLayout.setVisibility(View.VISIBLE);
        } else {
            selectList.setAdapter(provinceAdapter);
        }
    }

    private void cityTouchEvent() {
        if (cityAdapter == null) {
            GetCityRequest getCityRequest = new GetCityRequest();
            getCityRequest.setProvince(1);
            MyOKHttp.postToBase("requireCity", getCityRequest, new HttpStringResponseHandler<GetCityReponse>(getApplicationContext(),
                    GetCityReponse.class, true) {
                @Override
                public void onSuccess(GetCityReponse reponse) {
                    super.onSuccess(reponse);
                    if (reponse.getSuccess() == 1) {
                        cityList = reponse.getCitys();
                        List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
                        for (int i = 0; i < cityList.size(); i++) {
                            Map<String, Object> mapTemp = new HashMap<String, Object>();
                            mapTemp.put("city", cityList.get(i));
                            listTemp.add(mapTemp);
                        }
                        cityAdapter = new SimpleAdapter(getApplicationContext(), listTemp, R.layout.item_simple, new String[] {"city"}, new int[] {R.id.text});
                        selectList.setAdapter(cityAdapter);
                        listType = 1;
                        listLayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        } else {
            selectList.setAdapter(cityAdapter);
            listLayout.setVisibility(View.VISIBLE);
        }
    }

    private void countryTouchEvent() {
            if (selectCity != 0) {
                GetCountryRequest getCountryRequest = new GetCountryRequest();
                getCountryRequest.setCity(selectCity);
                MyOKHttp.postToBase("requireCountry", getCountryRequest, new HttpStringResponseHandler<GetCountryReponse>(getApplicationContext(),
                        GetCountryReponse.class, true) {
                    @Override
                    public void onSuccess(final GetCountryReponse reponse) {
                        super.onSuccess(reponse);
                        if (reponse.getSuccess() == 1) {
                            countryList = reponse.getCountrys();
                            List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
                            for (int i = 0; i < countryList.size(); i++) {
                                Map<String, Object> mapTemp = new HashMap<String, Object>();
                                mapTemp.put("country", countryList.get(i));
                                listTemp.add(mapTemp);
                            }
                            countryAdapter = new SimpleAdapter(getApplicationContext(), listTemp, R.layout.item_simple, new String[] {"country"}, new int[] {R.id.text});
                            selectList.setAdapter(countryAdapter);
                            listType = 2;
                            listLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "请先选择城市", Toast.LENGTH_LONG).show();
            }
    }

    private void bankTouchEvent() {
        if (bankAdapter == null) {
            List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < bank.length; i++) {
                Map<String, Object> mapTemp = new HashMap<String, Object>();
                mapTemp.put("bankName", bank[i]);
                listTemp.add(mapTemp);
            }
            bankAdapter = new SimpleAdapter(this, listTemp, R.layout.item_simple, new String[] {"bankName"}, new int[] {R.id.text});
            selectList.setAdapter(bankAdapter);
            listType = 3;
            listLayout.setVisibility(View.VISIBLE);
        } else {
            selectList.setAdapter(bankAdapter);
            listLayout.setVisibility(View.VISIBLE);
        }
    }

    private void registerConfirm(String phoneNum, String codeNum, String password) {
        final RegisterRequest registerRequest = new RegisterRequest(phoneNum, password, codeNum, solderName.getText().toString().trim(),
                selectCountry, detailAddr.getText().toString().trim(), telephone.getText().toString(), zhifubao.getText().toString().trim(),
                weixin.getText().toString().trim(), selectBank, bankCard.getText().toString());
        MyOKHttp.postToBase("solder/register", registerRequest, new HttpStringResponseHandler<RegisterResponse>(getApplicationContext(), RegisterResponse.class, true) {
            @Override
            public void onSuccess(RegisterResponse response) {
                super.onSuccess(response);
                if (response.getSuccess() == 1) {   //注册完成
                    StorageUtil.safeUserInfo(registerRequest.getPhoneNum(), registerRequest.getPassword(), response.getSolderId());
                    StorageUtil.safeState(true);
//                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                    startActivity(intent);
                }
            }
        });
    }

    public class CodeCount extends CountDownTimer {

        public CodeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            codeTimer.setText("重新发送");
            codeTimer.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            codeTimer.setText(millisUntilFinished / 1000 + "S");
        }

    }
}
