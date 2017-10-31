package hr.foi.air.optimix.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public class SessionManager {

    public static final String SHARED_PREFS_NAME = "hr.foi.air.optimix.session";
    public static final String PERSON_INFO_KEY = "person";
    public static final String COOKIE_KEY="cookie";

    static SessionManager instance;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    private SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static SessionManager getInstance(Context context) {
        if(instance == null) instance = new SessionManager(context);
        return instance;
    }

    public boolean createSession(Serializable object, String field) {
        return editor.putString(field, new Gson().toJson(object)).commit();
    }

    public <T extends Serializable> T retrieveSession(String field, Class<T> type) {
        return type.cast(new Gson().fromJson(sharedPreferences.getString(field, null), type));
    }


    public boolean destroyAll() {
        return editor.clear().commit();
    }


    public boolean destroySession(String field) {
        return editor.remove(field).commit();
    }
}
