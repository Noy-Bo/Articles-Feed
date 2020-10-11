package com.example.articlefeed.Utilities;

import android.util.Log;

public class DateParser {
    public static String EngMonthToHebrew(String engMonth)
    {
        switch(engMonth) {
            case "Jan": return "בינואר";
            case "Fab": return "בפברואר";
            case "Mar": return "במרץ";
            case "Apr": return "באפריל";
            case "May": return "במאי";
            case "Jun": return "ביוני";
            case "Jul": return "ביולי";
            case "Aug": return "באוגוסט";
            case "Sep": return "בספטמבר";
            case "Oct": return "באוקטובר";
            case "Nov": return "בנובמבר";
            case "Dec": return "בדצמבר";
        }
        return "";
    }
    public static String NumberMonthToHebrew(String monthNumber)
    {
        switch(monthNumber)
        {
            case "01": return "בינואר";
            case "02": return "בפברואר";
            case "03": return "במרץ";
            case "04": return "באפריל";
            case "05": return "במאי";
            case "06": return "ביוני";
            case "07": return "ביולי";
            case "08": return "באוגוסט";
            case "09": return "בספטמבר";
            case "10": return "באוקטובר";
            case "11": return "בנובמבר";
            case "12": return "בדצמבר";
        }
        return "";
    }
    public static String FullDateObjectStringToHebrewDate(String rawDate) // recieves toString of java date object
    {
        String date;
        date = "";
        if (rawDate.substring(5,6).equals("0"))
        {
            date += rawDate.substring(6,7);
        }
        else
        {
            date += rawDate.substring(5,7);
        }
        date += " ";
        date += DateParser.EngMonthToHebrew(rawDate.substring(8,11));
        //date = date.replace("0","");
        return date;
    }
}
