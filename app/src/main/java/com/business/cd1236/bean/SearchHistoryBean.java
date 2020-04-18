package com.business.cd1236.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchHistoryBean {
    @Id
    private Long id;

    @NotNull
    private String text;

    //    @Generated(hash = 1976838443)
//    public SearchHistoryBean(Long id, @NotNull String text) {
//        this.id = id;
//        this.text = text;
//    }
    @Keep
    public SearchHistoryBean(@NotNull String text) {
        this.text = text;
    }

    @Keep
    public SearchHistoryBean() {
    }

    @Generated(hash = 1976838443)
    public SearchHistoryBean(Long id, @NotNull String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
