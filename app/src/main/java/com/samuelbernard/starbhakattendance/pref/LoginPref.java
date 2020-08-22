package com.samuelbernard.starbhakattendance.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.samuelbernard.starbhakattendance.model.ResponseLogin;

public class LoginPref {
    public static final String ROLE_STUDENT = "siswa";
    public static final String ROLE_TEACHER = "guru";
    public static final String STATUS_ACTIVE = "active";

    private static final String PREF_NAME = "loginpref";
    private static final String ID_USER = "iduser";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";

    private static final String CREATED_AT = "created_at";
    private static final String IS_FIRST_TIME_LAUNCH = "isfirsttimelaunch"; // First time app open
    private static final String IS_ABSENT = "isabsent"; // Checkin checkout condition

    private static final String USER_TOKEN = "user_token"; // Put For fcm token
    private static final String LONGITUDE = "longitude"; // Put only
    private static final String LATITUDE = "latitude"; // Put only
    private final SharedPreferences preferences;

    /**
     * Instantiates a new Login pref.
     *
     * @param context the context
     */
    public LoginPref(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public int getIdUser() {
        return preferences.getInt(ID_USER, 0);
    }

    public void setIdUser(int idUser) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ID_USER, idUser);
        editor.apply();
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, name);
        editor.apply();
    }

    public String getName() {
        return preferences.getString(NAME, "");
    }

    public void setRole(String role) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ROLE, role);
        editor.apply();
    }

    public String getRole() {
        return preferences.getString(ROLE, "");
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public String getEmail() {
        return preferences.getString(EMAIL, "");
    }

    public void setCreatedAt(String createdAt) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CREATED_AT, createdAt);
        editor.apply();
    }

    public String getCreatedAt() {
        return preferences.getString(CREATED_AT, "0");
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return preferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setIsAbsent(String isAbsent) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(IS_ABSENT, isAbsent);
        editor.apply();
    }

    public String getIsAbsent() {
        return preferences.getString(IS_ABSENT, "null");
    }

    public Double getLatitude() {
        String latt = preferences.getString(LATITUDE, "0");
        return Double.valueOf(latt);
    }

    public void setLatitude(String latitude) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LATITUDE, latitude);
        editor.apply();
    }

    public Double getLongitude() {
        String longi = preferences.getString(LONGITUDE, "0");
        return Double.valueOf(longi);
    }

    public void setLongitude(String longitude) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LONGITUDE, longitude);
        editor.apply();
    }

    public String getUserToken() {
        return preferences.getString(USER_TOKEN, "0");
    }

    public void setUserToken(String userToken) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_TOKEN, userToken);
        editor.apply();
    }

    // Set user data
    public void setUserData(ResponseLogin.Data user) {
        SharedPreferences.Editor editor = preferences.edit();
        setIdUser(user.getId());
        setName(user.getName());
        setRole(user.getRole());
        setEmail(user.getEmail());
        setCreatedAt(user.getCreatedAt());

        editor.apply();
    }

    // Clear session data
    public void clearData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}