package com.business.cd1236.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.mvp.IView;

public abstract class MyBaseFragment<P extends IPresenter> extends BaseFragment<P> implements IView {
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }
}
