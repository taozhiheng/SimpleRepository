package data;

import android.content.Context;
import android.content.SharedPreferences;

import util.Constant;
import util.MD5;

/**
 * Created by taozhiheng on 15-7-23.
 * read and write userInfo
 */
public class UserPref {
    
    private static SharedPreferences mPref;
    
    public static void init(Context context)
    {
        if(mPref == null)
            mPref = context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static boolean getFirstGuide(int index)
    {
        if(index == 0)
            return mPref.getBoolean(Constant.PREF_FIRST_GUIDE0, true);
        else
            return mPref.getBoolean(Constant.PREF_FIRST_GUIDE1, true);
    }

    public static boolean getFirstUse()
    {
        return mPref.getBoolean(Constant.PREF_FIRST_USE, true);
    }
    
    public static String getUserMail()
    {
        return mPref.getString(Constant.PREF_USERNAME, null);
    }
    
    public static String getUserPassword()
    {
        String psw = mPref.getString(Constant.PREF_PASSWORD, null);
        if(psw == null)
            return null;
        else
            return MD5.encryptmd5(psw);
    }

    public static String getUserAuth()
    {
        return mPref.getString(Constant.PREF_AUTH, null);
    }

    public static String getWords(int flag)
    {
        switch (flag)
        {
            case 0:
                return mPref.getString(Constant.PREF_WORDS, null);
            case 1:
                return mPref.getString(Constant.PREF_WORDS2, null);
            case 2:
                return mPref.getString(Constant.PREF_WORDS3, null);
        }
        return null;
    }

    public static long getTime()
    {
        return mPref.getLong(Constant.PREF_TIME, 0);
    }


    public static void clearFirstGuide(int index)
    {
        if(index == 0)
            mPref.edit().putBoolean(Constant.PREF_FIRST_GUIDE0, false).apply();
        else
            mPref.edit().putBoolean(Constant.PREF_FIRST_GUIDE1, false).apply();
    }

    public static void clearFirstUse()
    {
        mPref.edit().putBoolean(Constant.PREF_FIRST_USE, false).apply();
    }

    public static void setWords(int flag, String words)
    {
        if(flag <0 || flag > 3)
            return;
        String key = Constant.PREF_WORDS;
        switch (flag)
        {
            case 1:
                key = Constant.PREF_WORDS2;
                break;
            case 2:
                key = Constant.PREF_WORDS3;
                break;
        }
        mPref.edit().putString(key, words).apply();
    }

    public static void setTime(long time)
    {
        mPref.edit().putLong(Constant.PREF_TIME, time).apply();
    }


    public static void setUserMail(String mail)
    {
        mPref.edit().putString(Constant.PREF_USERNAME, mail).apply();
    }
    
    public static void setUserPassword(String password)
    {
        mPref.edit().putString(Constant.PREF_PASSWORD, MD5.encryptmd5(password)).apply();
    }

    public static void setUserAuth(String auth)
    {
        mPref.edit().putString(Constant.PREF_AUTH, auth).apply();
    }
}
