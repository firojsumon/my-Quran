package com.example.myquran;

public class SurahDetailsModel {
    private String aya,arabic_text,translation,footnotes;

    public SurahDetailsModel(String aya, String arabic_text, String translation, String footnotes) {
        this.aya = aya;
        this.arabic_text = arabic_text;
        this.translation = translation;
        this.footnotes = footnotes;
    }

    public String getAya() {
        return aya;
    }

    public void setAya(String aya) {
        this.aya = aya;
    }

    public String getArabic_text() {
        return arabic_text;
    }

    public void setArabic_text(String arabic_text) {
        this.arabic_text = arabic_text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(String footnotes) {
        this.footnotes = footnotes;
    }
}
