package iteso.mx.proyectoexamen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import iteso.mx.proyectoexamen.beans.City;
import iteso.mx.proyectoexamen.beans.Store;
import iteso.mx.proyectoexamen.beans.User;
import iteso.mx.proyectoexamen.database.DataBaseHandler;
import iteso.mx.proyectoexamen.database.StoreControl;
import iteso.mx.proyectoexamen.tools.Constants;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DataBaseHandler db = DataBaseHandler.getInstance(this);
        StoreControl storeControl = new StoreControl();
        List<Store> stores = storeControl.getStores(db);
        if(stores.size() == 0) {
            City city = new City();
            Store s1 = new Store();
            s1.setId(1);
            s1.setName("Bestbuy");
            s1.setThumbnail(0); //0 es Bestbuy
            city.setId(1);
            city.setName("Zapopan");
            s1.setCity(city);
            s1.setPhone("123-4567-89");
            s1.setLongitude(456.7891);
            s1.setLatitude(123.456789);
            storeControl.addStore(s1, db);

            city = new City();
            s1 = new Store();
            s1.setId(2);
            s1.setName("Fábricas de Francia");
            s1.setThumbnail(1); //1 es FR
            city.setId(2);
            city.setName("Guadalajara");
            s1.setCity(city);
            s1.setPhone("987-6543-21");
            s1.setLongitude(987.654321);
            s1.setLatitude(123.4567891);
            storeControl.addStore(s1, db);

            city = new City();
            s1 = new Store();
            s1.setId(3);
            s1.setName("RadioShack");
            s1.setThumbnail(2); //2 es RS
            city.setId(1);
            city.setName("Zapopan");
            s1.setCity(city);
            s1.setPhone("123-123456");
            s1.setLongitude(999.888777);
            s1.setLatitude(777.888999);
            storeControl.addStore(s1, db);
        }

        User user = loadPreferences();
        Intent intent;
        if(user.isLogged()){
            intent = new Intent(ActivitySplashScreen.this,ActivityMain.class);
        }else {
            intent = new Intent(ActivitySplashScreen.this,ActivityLogin.class);
        }
        startActivity(intent);
        finish();
    }

    public User loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString(Constants.USER_PREF, null));
        user.setPassword(sharedPreferences.getString(Constants.PASSWORD_PREF, null));
        user.setLogged(sharedPreferences.getBoolean(Constants.LOGGED_PREF, false));
        return user;
    }
}
