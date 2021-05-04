package deu.cse.tos;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationHelper {

    public static void enableNavigation(final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.callHome:
                    Intent intent1 = new Intent(context, MainActivity.class);  // 0
                    context.startActivity(intent1);
                    break;

                case R.id.callQnA:
                    Intent intent2 = new Intent(context, QnAFragment.class);  // 0
                    context.startActivity(intent2);
                    break;
                case R.id.callBrush:
                    Intent intent3 = new Intent(context, ModeFragment.class);  // 0
                    context.startActivity(intent3);
                    break;
                case R.id.callCalendar:
                    Intent intent4 = new Intent(context, CalendarFragment.class);  // 0
                    context.startActivity(intent4);
                    break;
                case R.id.callInformation:
                    Intent intent5 = new Intent(context, InformationFragment.class);  // 0
                    context.startActivity(intent5);

                    break;
            }
            return false;

        });
    }
}

