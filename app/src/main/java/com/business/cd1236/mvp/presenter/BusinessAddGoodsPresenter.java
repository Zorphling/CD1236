package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.BusinessGoodsShowBean;
import com.business.cd1236.mvp.contract.BusinessAddGoodsContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.business.cd1236.utils.GsonUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


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
@ActivityScope
public class BusinessAddGoodsPresenter extends BasePresenter<BusinessAddGoodsContract.Model, BusinessAddGoodsContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BusinessAddGoodsPresenter(BusinessAddGoodsContract.Model model, BusinessAddGoodsContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getGoodsTypes(Context context) {
        RequestUtils.businessGoodsShow(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                BusinessGoodsShowBean businessGoodsShowBean = GsonUtils.parseJsonWithGson(jsonString, BusinessGoodsShowBean.class);
                mRootView.getGoodsTypesSucc(businessGoodsShowBean);
            }
        });
    }

    public void uploadImg(MultipartBody.Part part, Context context) {
        RequestUtils.uploadImg(part, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
//                mRootView.uploadImgSucc(jsonString);
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
            }
        });
    }

    public void uploadImgs(Map<String, RequestBody> map, Context context) {
        RequestUtils.uploadImgs(map, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {//多图返回是个字符串数组
                //["http:\/\/my.1236sc.com\/Public\/Uploads\/2020-04\/5ea2900177b969.84523706.jpg","http:\/\/my.1236sc.com\/Public\/Uploads\/2020-04\/5ea2900178ea98.80881714.jpg"]
//                List<String> list = GsonUtils.parseJsonArrayWithGson(jsonString, String.class);

//                这种方法不行呀!!!
//                if (jsonString.startsWith("[") || jsonString.startsWith("{")) {
//                    jsonString = jsonString.substring(1);
//                }
//                if (jsonString.endsWith("]") || jsonString.endsWith("}")) {
//                    jsonString = jsonString.substring(0, jsonString.length() - 1);
//                }
//                String[] array = jsonString.split(",");
//                ArrayList<String> list = new ArrayList<String>();
//                for (String temp : array) {
//                    list.add(temp);
//                }
                mRootView.uploadImgSucc(Arrays.asList(jsonString.split(",")));
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
            }
        });
    }

    public void uploadImgs(List<MultipartBody.Part> partList, Context context) {
        RequestUtils.uploadImgs(partList, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
//                mRootView.uploadImgSucc(jsonString);
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
            }
        });
    }

    public void addGoods(String id,String typeId, String brandId, String categoryId, String editText, String editText1, String editText2, String editText3, String editText4, String formatId, String s, String s1, String editText5, String editText6, String editText7, String total,String agent_total, String s2, Context context) {
        RequestUtils.businessAddGoods(id,typeId, brandId, categoryId, editText, editText1, editText2, editText3, editText4, formatId, s, s1, editText5, editText6, editText7, total,agent_total, s2, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.addGoodsSucc(msg);
            }
        });
    }
    public void deleteGoods(String id, Context context) {
        RequestUtils.businessGoodsDelete(id, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.deleteGoodsSucc(msg);
            }
        });
    }
}
