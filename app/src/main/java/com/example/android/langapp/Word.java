package com.example.android.langapp;

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mMiwokAudio;
    private int mImageResourceId=NO_IMAGE;
    private int icon;
private static final int NO_IMAGE=-1;
    public Word(String defaultTranslation,String miwokTranslation,int miwokAudio,int playicon){
        mMiwokTranslation=miwokTranslation;
        mDefaultTranslation=defaultTranslation;
        mMiwokAudio=miwokAudio;
        icon=playicon;
    }

    public Word(String defaultTrans,String miwokTrans,int imgResId,int miwokAudio,int playicon){
        mMiwokTranslation=miwokTrans;
        mDefaultTranslation=defaultTrans;
        mImageResourceId=imgResId;
        mMiwokAudio=miwokAudio;
        icon=playicon;
    }
    public int getImageResourceId(){
        return mImageResourceId;
    }
    public String getMiwokTranslation() {

        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {

        return mDefaultTranslation;
    }

    public int getIcon() {
        return icon;
    }

    public int getmMiwokAudio() {
        return mMiwokAudio;
    }

    boolean hasImage(){
        if (mImageResourceId!=-1)
            return true;
        else
            return false;
    }

}
