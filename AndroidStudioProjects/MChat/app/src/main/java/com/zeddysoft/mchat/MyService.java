package com.zeddysoft.mchat;

/**
 * Created by hayzed on 10/31/15.
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;

import org.jivesoftware.smack.chat.Chat;

public class MyService extends Service {
    private static final String DOMAIN = "192.168.43.107";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "zeddy1993";
    public static ConnectivityManager cm;
    public static MyXMPP xmpp;
    public static boolean ServerchatCreated = false;
    String text = "";

    @Override
    public IBinder onBind(final Intent intent) {
        return new LocalBinder<MyService>(this);
    }

    public Chat chat;

    @Override
    public void onCreate() {
        super.onCreate();
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        xmpp = MyXMPP.getInstance(MyService.this, DOMAIN, USERNAME, PASSWORD);
        xmpp.connect("onCreate");
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags,
                              final int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(final Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        xmpp.connection.disconnect();
    }

    public static boolean isNetworkConnected() {
        return cm.getActiveNetworkInfo() != null;
    }
}
