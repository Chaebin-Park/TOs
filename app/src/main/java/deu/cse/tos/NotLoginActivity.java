package deu.cse.tos;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class NotLoginActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView image,image2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KakaoSdk.init(this, "1c11ae1b9e8f2cfbeb1676908dfcd2da");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0x00000000);  // transparent
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.addFlags(flags);
        }
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButton button = (ImageButton)findViewById(R.id.imageButton2);
        Intent i = new Intent(this,MainActivity.class);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        //image = findViewById(R.id.imageView2);
        image2 = findViewById(R.id.imageView);


        //image.setAnimation(bottomAnim);
        image2.setAnimation(topAnim);

        final LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.tooth_turn);
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


//        getHashKey();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(i);

//                if (LoginClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
//                    LoginClient.getInstance().loginWithKakaoTalk(LoginActivity.this, new Function2<OAuthToken, Throwable, Unit>() {
//                        @Override
//                        public Unit invoke(OAuthToken token, Throwable error) {
//                            if (error != null) {
//                                Log.e("TAG", "로그인 실패", error);
//                            } else {
//                                Log.d("TAG", "로그인 성공");
//                                startActivity(i);
//
//                                // 사용자 정보 요청
//                                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
//                                    @Override
//                                    public Unit invoke(User user, Throwable error) {
//                                        if (error != error) {
//                                            Log.e("TAG", "사용자 정보 요청 실패", error);
//                                        } else {
//                                            Log.i("TAG", user.toString());
//                                        }
//                                        return null;
//                                    }
//                                });
//                            }
//                            return null;
//                        }
//                    });
//                } else {
//                    LoginClient.getInstance().loginWithKakaoAccount(LoginActivity.this,  new Function2<OAuthToken, Throwable, Unit>() {
//                        @Override
//                        public Unit invoke(OAuthToken token, Throwable error) {
//                            if (error != null) {
//                                Log.e("TAG", "로그인 실패", error);
//                            } else {
//                                Log.d("TAG", "로그인 성공");
//                                startActivity(i);
//
//                                // 사용자 정보 요청
//                                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
//                                    @Override
//                                    public Unit invoke(User user, Throwable error) {
//                                        if (error != error) {
//                                            Log.e("TAG", "사용자 정보 요청 실패", error);
//                                        } else {
//                                            Log.i("TAG", user.toString());
//                                        }
//                                        return null;
//                                    }
//                                });
//                            }
//                            return null;
//                        }
//                    });
//                }
//
            }
        });

    }

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }





}