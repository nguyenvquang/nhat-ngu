package com.q.a.hocnhatngumina.utils;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by Nguyen Van Quang on 8/11/2016.
 */
public class TextFormat {

    public static Spanned textColor(String text, String color) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"'>");
        stringBuilder.append(text);
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }

    public static Spanned textColorDefault(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append(text);
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }


    public static Spanned textColorBold(String text, String color) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"'>");
        stringBuilder.append("<b>");
        stringBuilder.append(text);
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }

    public static Spanned textUnderline(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<u>");
        stringBuilder.append(text);
        stringBuilder.append("</u>");
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }

    public static Spanned textColorBold(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<b>");
        stringBuilder.append(text);
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }


    public static Spanned textColorItalic(String text, String color) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"'>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }

    public static Spanned textColorItalicBold(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<b>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }

    public static Spanned textColorItalic(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }

    public static Spanned textColorBoldItalic(String text, String color) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"'>");
        stringBuilder.append("<b>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }

    public static Spanned textColorBoldItalic(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<b>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return Html.fromHtml(stringBuilder.toString());
    }
}
