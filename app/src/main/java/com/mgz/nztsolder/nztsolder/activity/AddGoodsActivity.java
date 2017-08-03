package com.mgz.nztsolder.nztsolder.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mgz.nztsolder.nztsolder.R;
import com.mgz.nztsolder.nztsolder.basictool.BaseActivity;
import com.mgz.nztsolder.nztsolder.network.HttpStringResponseHandler;
import com.mgz.nztsolder.nztsolder.network.reponse.AddGoodsResponse;
import com.mgz.nztsolder.nztsolder.network.reponse.BrandResponse;
import com.mgz.nztsolder.nztsolder.network.reponse.CategoryResponse;
import com.mgz.nztsolder.nztsolder.network.request.AddGoodsRequest;
import com.mgz.nztsolder.nztsolder.network.request.CategoryRequest;
import com.mgz.nztsolder.nztsolder.utils.MyOKHttp;
import com.mgz.nztsolder.nztsolder.utils.StorageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2017/7/25.
 */

public class AddGoodsActivity extends BaseActivity implements View.OnClickListener {

    private Spinner type;
    private EditText name;
    private EditText price;
    private EditText weight;
    private EditText avgPrice;
    private EditText height;
    private EditText width;
    private EditText length;
    private TextView category;
    private TextView brand;
    private CheckBox chooseOther;
    private EditText otherBrand;
    private EditText scanId;
    private TextView scan;
    private EditText describe;
    private TextView pic;
    private Button confrim;
    private ListView listView;

    private String[] types = new String[] {"农药", "化肥", "机械", "种子"};
    private int listType = -1;
    private int typeSelect = 0;
    private int categorySelect = -1;
    private int brandSelect = -1;
    private SimpleAdapter categoryAdapter;
    private SimpleAdapter brandAdapter;
    private String newBrand = "";
    private String des = "";
    private ArrayList<String> categoryList = new ArrayList<String>();
    private ArrayList<String> brandList = new ArrayList<String>();

    @Override
    public int getRecoureId() {
        return R.layout.activity_add;
    }

    @Override
    public void initView() {
        type = (Spinner) findViewById(R.id.spinner);
        name = (EditText) findViewById(R.id.et_name);
        price = (EditText) findViewById(R.id.et_price);
        weight = (EditText) findViewById(R.id.et_weight);
        avgPrice = (EditText) findViewById(R.id.et_avg_pri);
        height = (EditText) findViewById(R.id.et_height);
        length = (EditText) findViewById(R.id.et_length);
        width = (EditText) findViewById(R.id.et_width);
        category = (TextView) findViewById(R.id.tv_category);
        brand = (TextView) findViewById(R.id.tv_brand);
        chooseOther = (CheckBox) findViewById(R.id.radio_other);
        otherBrand = (EditText) findViewById(R.id.et_brand_other);
        scanId = (EditText) findViewById(R.id.et_scan);
        scan = (TextView) findViewById(R.id.btn_scan);
        describe = (EditText) findViewById(R.id.et_describe);
        pic = (TextView) findViewById(R.id.tv_pic);
        confrim = (Button) findViewById(R.id.btn_confirm);
        listView = (ListView) findViewById(R.id.lv_select);
    }

    @Override
    public void setOnClickListner() {
        View[] views = new View[] {category, brand, chooseOther, scan, pic, confrim};
        quickSetClickListener(views, this);
        type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeSelect = position;
            }
        });
        price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if ((!weight.getText().toString().equals("")) && (!price.getText().toString().equals(""))) {
                        avgPrice.setText((Double.parseDouble(price.getText().toString().trim()) /
                                Double.parseDouble(weight.getText().toString().trim())) + "");
                    }
                }
            }
        });
        weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if ((!weight.getText().toString().equals("")) && (!price.getText().toString().equals(""))) {
                        avgPrice.setText((Double.parseDouble(price.getText().toString().trim()) /
                                Double.parseDouble(weight.getText().toString().trim())) + "");
                    }
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewSelectEvent(listType, position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == chooseOther) {
            if (chooseOther.isChecked()) {
                otherBrand.setFocusable(true);
                otherBrand.setFocusableInTouchMode(true);
                otherBrand.requestFocus();
            } else {
                otherBrand.setText("");
                otherBrand.setFocusable(false);
                otherBrand.setFocusableInTouchMode(false);
            }
        } else if (v == category) {
            categoryEvent();
        } else if (v == brand) {
            brandEvent();
        } else if (v == scan) {

        } else if (v == confrim) {
            AddGoodsRequest addGoodsRequest = new AddGoodsRequest(StorageUtil.getSolderId(), typeSelect, name.getText().toString().trim(), Double.parseDouble(price.getText().toString().trim()),
                    Double.parseDouble(weight.getText().toString().trim()), Double.parseDouble(avgPrice.getText().toString().trim()), Integer.parseInt(length.getText().toString()),
                    Integer.parseInt(height.getText().toString().trim()), Integer.parseInt(width.getText().toString().trim()), categorySelect, brandSelect,
                    otherBrand.getText().toString(), scanId.getText().toString().trim(), describe.getText().toString(), null);
            MyOKHttp.postToBase("solder/addGood", addGoodsRequest, new HttpStringResponseHandler<AddGoodsResponse>(getApplicationContext(),
                    AddGoodsResponse.class, true) {
                @Override
                public void onSuccess(AddGoodsResponse response) {
                    super.onSuccess(response);
                    if (response.getSuccess() == 1) {
                        Toast.makeText(getApplicationContext(), "添加商品成功", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });
        }
    }

    private void categoryEvent() {
        final CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setType(typeSelect);
        listType = 0;
        MyOKHttp.postToBase("requireCategory", categoryRequest, new HttpStringResponseHandler<CategoryResponse>(getApplicationContext(),
                CategoryResponse.class, true) {
            @Override
            public void onSuccess(CategoryResponse response) {
                super.onSuccess(response);
                categoryList = response.getCategoryList();
                List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < categoryList.size(); i++) {
                    Map<String, Object> mapTemp = new HashMap<String, Object>();
                    mapTemp.put("category", categoryList.get(i));
                    listTemp.add(mapTemp);
                }
                categoryAdapter = new SimpleAdapter(getApplicationContext(), listTemp, R.layout.item_simple, new String[]{"category"},
                        new int[]{ R.id.text });
                listView.setAdapter(categoryAdapter);
                listView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void brandEvent() {
        if (brandAdapter == null) {
            listType = 1;
            MyOKHttp.postToBase("requireBrand", null, new HttpStringResponseHandler<BrandResponse>(getApplicationContext(),
                    BrandResponse.class, true) {
                @Override
                public void onSuccess(BrandResponse response) {
                    super.onSuccess(response);
                    brandList = response.getBrandList();
                    List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
                    for (int i = 0; i < brandList.size(); i++) {
                        Map<String, Object> mapTemp = new HashMap<String, Object>();
                        mapTemp.put("brand", brandList.get(i));
                        listTemp.add(mapTemp);
                    }
                    categoryAdapter = new SimpleAdapter(getApplicationContext(), listTemp, R.layout.item_simple, new String[]{"brand"},
                            new int[]{ R.id.text });
                    listView.setAdapter(brandAdapter);
                    listView.setVisibility(View.VISIBLE);
                }
            });
        } else {
            listView.setAdapter(brandAdapter);
            listView.setVisibility(View.VISIBLE);
        }
    }

    private void listViewSelectEvent(int mode, int position) {
        switch (mode) {
            case 0:
                categorySelect = position;
                break;
            case 1:
                brandSelect = position;
                break;
            default:
                break;
        }
        listView.setVisibility(View.GONE);
    }
}
