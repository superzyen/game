package com.superzyen.zyengame.page;

public class AbstractPage implements IPageIntroduce{
    protected int hoemTag = 0;

    public void setHoemTag(int tag) {
        hoemTag = tag;
    }

    public int getHoemTag() {
        return hoemTag;
    }

    @Override
    public String introduce() {
        return null;
    }
}
