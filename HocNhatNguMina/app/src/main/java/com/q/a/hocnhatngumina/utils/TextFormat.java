package com.q.a.hocnhatngumina.utils;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by Nguyen Van Quang on 8/11/2016.
 */
public class TextFormat {

    public static String textSColor(String text, String color) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"'>");
        stringBuilder.append(text);
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }

    public static String textSColorDefault(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append(text);
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }


    public static String textSColorBold(String text, String color) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"'>");
        stringBuilder.append("<b>");
        stringBuilder.append(text);
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }

    public static String textSColorBold(String text, String color, String fontSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"' size='"+ fontSize +"'>");
        stringBuilder.append("<b>");
        stringBuilder.append(text);
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }

    public static String textSUnderline(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<u>");
        stringBuilder.append(text);
        stringBuilder.append("</u>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }

    public static String textSColorBold(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<b>");
        stringBuilder.append(text);
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }


    public static String textSColorItalic(String text, String color) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"'>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }

    public static String textSColorItalicBold(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<b>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }

    public static String textSColorItalic(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }

    public static String textSColorBoldItalic(String text, String color) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='"+ color +"'>");
        stringBuilder.append("<b>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }

    public static String textSColorBoldItalic(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='#f4282727'>");
        stringBuilder.append("<b>");
        stringBuilder.append("<i>");
        stringBuilder.append(text);
        stringBuilder.append("</i>");
        stringBuilder.append("</b>");
        stringBuilder.append("</font>");
        return stringBuilder.toString();
    }



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
