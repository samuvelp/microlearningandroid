package com.samuvel.pandian.microlearning.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    public static void saveSubscribedTopic(String topic, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("topic", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("topic", topic);
        editor.apply();
    }

    public static String getSubscribedTopic(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("topic", Context.MODE_PRIVATE);
        return sharedPreferences.getString("topic", null);
    }

    public static void clearTopic(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("topic", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
