package com.business.cd1236.mvp.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.business.cd1236.R;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    String load = "https://open.czb365.com/redirection/todo/?platformType=98639076&platformCode=13088888888";
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llContent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(load);
    }
}
