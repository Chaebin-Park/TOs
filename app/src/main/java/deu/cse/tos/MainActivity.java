package deu.cse.tos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class MainActivity extends AppCompatActivity {
    private Context mContext = MainActivity.this;
    private static final int ACTIVITY_NUM = 0;
    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    BottomNavigationView bottomNavigationView;
    QnAFragment qnaFragement = new QnAFragment();
    MainFragment mainFragement = new MainFragment();
    InformationFragment informationFragement = new InformationFragment();
    CalendarFragment calendarFragement = new CalendarFragment();
    ModeFragment modeFragement = new ModeFragment();
//    private void initActivity() {
//        Window window = getWindow();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(0x00000000);  // transparent
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            window.addFlags(flags);
//        }
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout, fragment).commit();      // Fragment??? ????????? MainActivity?????? layout????????? ???????????????.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        initActivity();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout,mainFragement).commitAllowingStateLoss();
        bottomNavigationView = findViewById(R.id.navigation);
        Intent i = new Intent(this, ModeActivity.class);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragmentTransaction = fragmentManager.beginTransaction();
                switch(item.getItemId()) {
                    case R.id.callHome:
                        fragmentTransaction.replace(R.id.main_layout,mainFragement).commitAllowingStateLoss();
                        break;
                    case R.id.callQnA:
                        fragmentTransaction.replace(R.id.main_layout,qnaFragement).commitAllowingStateLoss();
                        break;
                    case R.id.callCalendar:
                        fragmentTransaction.replace(R.id.main_layout,calendarFragement).commitAllowingStateLoss();
                        break;
                    case R.id.callInformation:
                        fragmentTransaction.replace(R.id.main_layout,informationFragement).commitAllowingStateLoss();
                        break;
                    case R.id.callBrush:
                        fragmentTransaction.replace(R.id.main_layout,modeFragement).commitAllowingStateLoss();
                        break;
                }
                return false;
            }
        });


//        ImageButton button = (ImageButton) findViewById(R.id.brushimageButton);
//        Intent i = new Intent(this, VideoActivity.class);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(i);
//
//            }
//
//        });
    }

    private void setupBottomNavigationView(){
        BottomNavigationHelper.enableNavigation(mContext, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    // ???????????? ?????? ??????????????? ?????? long ??????
    private long pressedTime = 0;

    // ????????? ??????
    public interface OnBackPressedListener {
        public void onBack();
    }

    // ????????? ?????? ??????
    private OnBackPressedListener mBackListener;

    // ????????? ?????? ?????????
    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }

    // ???????????? ????????? ????????? ?????? ??????????????? ?????????
    @Override
    public void onBackPressed() {

        // ?????? Fragment ?????? ???????????? ???????????? ??? ???????????????.
        if(mBackListener != null) {
            mBackListener.onBack();
            Log.e("!!!", "Listener is not null");
            // ???????????? ???????????? ?????? ??????(???????????? ??????Fragment)??????
            // ???????????? ????????? ??????????????? ?????? ????????? ??? ?????? ???????????????.
        } else {
            Log.e("!!!", "Listener is null");
            if ( pressedTime == 0 ) {
                Snackbar.make(findViewById(R.id.main_layout),
                        " ??? ??? ??? ????????? ???????????????." , Snackbar.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
            }
            else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);

                if ( seconds > 2000 ) {
                    Snackbar.make(findViewById(R.id.main_layout),
                            " ??? ??? ??? ????????? ???????????????." , Snackbar.LENGTH_LONG).show();
                    pressedTime = 0 ;
                }
                else {
                    super.onBackPressed();
                    Log.e("!!!", "onBackPressed : finish, killProcess");
                    System.exit(1);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }
    }


}