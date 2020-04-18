package com.business.cd1236.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.business.cd1236.mvp.contract.BusinessNoticeContract;
import com.business.cd1236.mvp.model.BusinessNoticeModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 14:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class BusinessNoticeModule {

    @Binds
    abstract BusinessNoticeContract.Model bindBusinessNoticeModel(BusinessNoticeModel model);
}