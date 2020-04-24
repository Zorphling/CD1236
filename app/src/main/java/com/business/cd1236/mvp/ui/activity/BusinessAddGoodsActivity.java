package com.business.cd1236.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessGoodsShowBean;
import com.business.cd1236.di.component.DaggerBusinessAddGoodsComponent;
import com.business.cd1236.mvp.contract.BusinessAddGoodsContract;
import com.business.cd1236.mvp.presenter.BusinessAddGoodsPresenter;
import com.business.cd1236.utils.GlideEngine;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SoftKeyboardUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.dialog.SetHeaderDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/22/2020 16:58
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessAddGoodsActivity extends MyBaseActivity<BusinessAddGoodsPresenter> implements BusinessAddGoodsContract.View, SetHeaderDialog.SelectPicListener {
    @BindView(R.id.et_goods_type)
    EditText etGoodsType;
    @BindView(R.id.et_goods_name)
    EditText etGoodsName;
    @BindView(R.id.iv_goods)
    ImageView ivGoods;
    @BindView(R.id.iv_goods_detail)
    ImageView ivGoodsDetail;
    @BindView(R.id.et_goods_cost_price)
    EditText etGoodsCostPrice;
    @BindView(R.id.et_goods_now_price)
    EditText etGoodsNowPrice;
    @BindView(R.id.et_goods_wholesale_price)
    EditText etGoodsWholesalePrice;
    @BindView(R.id.et_sell_num)
    EditText etSellNum;
    @BindView(R.id.et_sell_unit)
    EditText etSellUnit;
    @BindView(R.id.et_wholesale_num)
    EditText etWholesaleNum;
    @BindView(R.id.et_wholesale_unit)
    EditText etWholesaleUnit;
    @BindView(R.id.et_goods_stock)
    EditText etGoodsStock;
    @BindView(R.id.et_goods_brand)
    EditText etGoodsBrand;
    @BindView(R.id.et_goods_standard_number)
    EditText etGoodsStandardNumber;
    @BindView(R.id.et_goods_bar_code)
    EditText etGoodsBarCode;
    @BindView(R.id.tv_goods_delete)
    TextView tvGoodsDelete;
    @BindView(R.id.et_Goods_spec)
    EditText etGoodsSpec;
    private OptionsPickerView opvType, opvUnit, opvGoodsSpec, opvGoodsBrand;
    private String typeId;
    private int whichUnit;
    private String IV_GOODS;
    private String IV_GOODS_DETAIL;
    private Map<String, LocalMedia> files = new HashMap<>();
    private ArrayList<LocalMedia> fileList = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessAddGoodsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_add_goods; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("编辑商品");
        setRightColor(getResources().getColor(R.color.black));
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);
        setRightBtn("保存", 0, v -> {
            checkAdd();
        });
        mPresenter.getGoodsTypes(mActivity);
    }

    private void checkAdd() {
        if (check(etGoodsType, "请选择商品类型"))
            if (check(etGoodsName, "请输入商品名称")) {
                if (ivGoods.getDrawable().getCurrent().getConstantState() == null || ivGoods.getDrawable().getCurrent().getConstantState().equals(getResources().getDrawable(R.mipmap.upload_img).getConstantState())) {
                    ArmsUtils.snackbarText("请上传商品图片");
                    return;
                } else {
                    if (ivGoodsDetail.getDrawable().getCurrent().getConstantState() == null || ivGoodsDetail.getDrawable().getCurrent().getConstantState().equals(getResources().getDrawable(R.mipmap.upload_img).getConstantState())) {
                        ArmsUtils.snackbarText("请上传商品详情图片");
                        return;
                    } else {
                        if (check(etGoodsNowPrice, "请输入商品现价"))
                            if (check(etGoodsWholesalePrice, "请输入商品批发价"))
                                if (check(etSellNum, "请输入零售数量"))
                                    if (check(etSellUnit, "请选择零售单位"))
                                        if (check(etWholesaleNum, "请输入批发数量"))
                                            if (check(etWholesaleUnit, "请选择批发单位"))
                                                if (check(etGoodsSpec, "请选额商品规格"))
                                                    if (check(etGoodsStock, "请输入商品库存"))
                                                        if (check(etGoodsBrand, "请选择商品品牌")) {
                                                            /**
                                                             * 多张图片上传的两种方式
                                                             */
                                                            Map<String, RequestBody> maps = new HashMap<>();
                                                            for (Map.Entry<String, LocalMedia> localMediaStringEntry : files.entrySet()) {
                                                                maps.put("files\";filename=\"" +
                                                                                localMediaStringEntry.getValue().getFileName(),
                                                                        RequestBody.create(MediaType.parse(localMediaStringEntry.getValue().getMimeType()),
                                                                                new File(localMediaStringEntry.getKey())));
                                                            }
                                                            mPresenter.uploadImgs(maps, mActivity);

//                                                            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//                                                            for (Map.Entry<String, LocalMedia> localMediaStringEntry : files.entrySet()) {
//                                                                File file = new File(localMediaStringEntry.getKey());
//                                                                RequestBody requestBody = RequestBody.create(MediaType.parse(localMediaStringEntry.getValue().getMimeType()), file);
//                                                                builder.addFormDataPart("file", file.getName(), requestBody);
//                                                            }
//                                                            List<MultipartBody.Part> parts = builder.build().parts();
//                                                            mPresenter.uploadImgs(parts, mActivity);
                                                        }
                    }
                }
            }
    }

    private boolean check(EditText ed, String toast) {
        if (!StringUtils.checkString(StringUtils.getEditText(ed))) {
            ArmsUtils.snackbarText(toast);
            return false;
        }
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void getGoodsTypesSucc(BusinessGoodsShowBean businessGoodsShowBean) {
        initTypesDialog(businessGoodsShowBean);
        initUnitDialog(businessGoodsShowBean);
    }

    @Override
    public void uploadImgSucc(String jsonString) {
//        mPresenter.addGoods(typeId,,mActivity);
    }

    private void initTypesDialog(BusinessGoodsShowBean businessGoodsShowBean) {
        if (businessGoodsShowBean == null) return;
        opvType = new OptionsPickerBuilder(this, (options1, option2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String tx = businessGoodsShowBean.category.get(options1).getPickerViewText();
            typeId = businessGoodsShowBean.category.get(options1).id;
            etGoodsType.setText(tx);
            initOtherDialog(businessGoodsShowBean);
        }).setLayoutRes(R.layout.dialog_business_enter, v -> {
            final TextView tvConfirm = v.findViewById(R.id.tv_confirm);
            TextView tvTitle = v.findViewById(R.id.tv_dialog_title);
            tvTitle.setText("请选择商品类型");
            TextView tvCancel = v.findViewById(R.id.tv_cancel);
            tvConfirm.setOnClickListener(v1 -> {
                opvType.returnData();
                opvType.dismiss();
            });
            tvCancel.setOnClickListener(v12 -> opvType.dismiss());
        }).build();
        opvType.setPicker(businessGoodsShowBean.category);//添加数据
    }

    private void initOtherDialog(BusinessGoodsShowBean businessGoodsShowBean) {
        List<BusinessGoodsShowBean.FormatBeanX.FormatBean> formatBean = new ArrayList<>();
        for (BusinessGoodsShowBean.FormatBeanX formatBeanX : businessGoodsShowBean.format) {
            if (StringUtils.equals(typeId, formatBeanX.id)) {
                formatBean.addAll(formatBeanX.format);
            }
        }
        opvGoodsSpec = new OptionsPickerBuilder(this, (options1, option2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            BusinessGoodsShowBean.FormatBeanX.FormatBean bean = formatBean.get(options1);
            etGoodsSpec.setText(bean.name);
        }).setLayoutRes(R.layout.dialog_business_enter, v -> {
            final TextView tvConfirm = v.findViewById(R.id.tv_confirm);
            TextView tvTitle = v.findViewById(R.id.tv_dialog_title);
            tvTitle.setText("请选择商品规格");
            TextView tvCancel = v.findViewById(R.id.tv_cancel);
            tvConfirm.setOnClickListener(v1 -> {
                opvGoodsSpec.returnData();
                opvGoodsSpec.dismiss();
            });
            tvCancel.setOnClickListener(v12 -> opvGoodsSpec.dismiss());
        }).build();
        opvGoodsSpec.setPicker(formatBean);//添加数据

        List<BusinessGoodsShowBean.CategoryBean.SonBean> sonBean = new ArrayList<>();
        for (BusinessGoodsShowBean.CategoryBean categoryBean : businessGoodsShowBean.category) {
            if (StringUtils.equals(typeId, categoryBean.id)) {
                sonBean.addAll(categoryBean.son);
            }
        }
        opvGoodsBrand = new OptionsPickerBuilder(this, (options1, option2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            BusinessGoodsShowBean.CategoryBean.SonBean bean = sonBean.get(options1);
            etGoodsBrand.setText(bean.name);
        }).setLayoutRes(R.layout.dialog_business_enter, v -> {
            final TextView tvConfirm = v.findViewById(R.id.tv_confirm);
            TextView tvTitle = v.findViewById(R.id.tv_dialog_title);
            tvTitle.setText("请选择品牌");
            TextView tvCancel = v.findViewById(R.id.tv_cancel);
            tvConfirm.setOnClickListener(v1 -> {
                opvGoodsBrand.returnData();
                opvGoodsBrand.dismiss();
            });
            tvCancel.setOnClickListener(v12 -> opvGoodsBrand.dismiss());
        }).build();
        opvGoodsBrand.setPicker(sonBean);//添加数据
    }

    private void initUnitDialog(BusinessGoodsShowBean businessGoodsShowBean) {
        opvUnit = new OptionsPickerBuilder(this, (options1, option2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String tx = businessGoodsShowBean.unit.get(options1);
            if (whichUnit == R.id.et_sell_unit) etSellUnit.setText(tx);
            else etWholesaleUnit.setText(tx);
        }).setLayoutRes(R.layout.dialog_business_enter, v -> {
            final TextView tvConfirm = v.findViewById(R.id.tv_confirm);
            TextView tvTitle = v.findViewById(R.id.tv_dialog_title);
            tvTitle.setText("请选择零售单位");
            TextView tvCancel = v.findViewById(R.id.tv_cancel);
            tvConfirm.setOnClickListener(v1 -> {
                opvUnit.returnData();
                opvUnit.dismiss();
            });
            tvCancel.setOnClickListener(v12 -> opvUnit.dismiss());
        }).build();
        opvUnit.setPicker(businessGoodsShowBean.unit);//添加数据
    }

    private @IdRes
    int imageView;

    @OnClick({R.id.et_goods_type, R.id.et_sell_unit, R.id.et_wholesale_unit, R.id.et_goods_brand, R.id.tv_goods_delete, R.id.iv_goods, R.id.iv_goods_detail, R.id.et_Goods_spec})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_goods_type:
                if (opvType != null) {
                    opvType.show();
                }
                break;
            case R.id.et_sell_unit:
                whichUnit = R.id.et_sell_unit;
                if (opvUnit != null) {
                    opvUnit.show();
                }
                break;
            case R.id.et_wholesale_unit:
                whichUnit = R.id.et_wholesale_unit;
                if (opvUnit != null) {
                    opvUnit.show();
                }
                break;
            case R.id.et_goods_brand:
                SoftKeyboardUtils.hideSoftKeyboard(mActivity);
                if (!StringUtils.checkString(StringUtils.getEditText(etGoodsType)) || !StringUtils.checkString(typeId)) {
                    ArmsUtils.snackbarText("请先选择商品类型");
                    return;
                }
                if (opvGoodsBrand != null) opvGoodsBrand.show();
                break;
            case R.id.tv_goods_delete:

                break;
            case R.id.iv_goods:
                imageView = R.id.iv_goods;
                ShowDialog();
                break;
            case R.id.iv_goods_detail:
                imageView = R.id.iv_goods_detail;
                ShowDialog();
                break;
            case R.id.et_Goods_spec:
                if (!StringUtils.checkString(StringUtils.getEditText(etGoodsType)) || !StringUtils.checkString(typeId)) {
                    ArmsUtils.snackbarText("请先选择商品类型");
                    return;
                }
                if (opvGoodsSpec != null) opvGoodsSpec.show();
                break;
        }
    }

    private void ShowDialog() {
        new SetHeaderDialog(this, this);
    }

    @Override
    public void onPhototaken() {//单独拍照
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofAll())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .enableCrop(true)// 是否裁剪
                .compress(true)//是否压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onSelectPic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .enableCrop(true)// 是否裁剪
                .compress(true)//是否压缩
                .maxSelectNum(1)//最大选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .isSingleDirectReturn(true)//单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .enableCrop(true)// 是否裁剪
                .compress(true)//是否压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 原图path
                    // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                    // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform 为false 此字段将返回空
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                    String path = "";
                    if (selectList.size() == 1) {
                        for (LocalMedia media : selectList) {
                            if (media.isCut() && !media.isCompressed()) {// 裁剪过
                                path = media.getCutPath();
                            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {// 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                                path = media.getCompressPath();
                            } else { // 原图
                                path = media.getPath();
                            }
                            GlideUtil.loadImg(path, findViewById(imageView));
                            if (!fileList.contains(media))
                                fileList.add(media);
                            files.put(path, media);
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (SoftKeyboardUtils.isShouldHideInput(view, ev)) {
                InputMethodManager Object = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (Object != null) {
                    Object.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
