package deu.cse.tos;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Retrofit retrofit;
    RetrofitCalendarService retrofitCalendarService;
    HashMap<String, Object> input = new HashMap<>();
    TextView textView16;
    TextView textView17;
    boolean init_state = false;
    SimpleDateFormat formatTime = new SimpleDateFormat("EEE", Locale.KOREAN);

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarFragment() {
        // Required empty public constructor
    }

    private int returnToothResBig(int count) {

        switch(count) {
            case 0:
                return R.drawable.group_252;
            case 1:
                return R.drawable.group_257;
            default:
                return R.drawable.group_253;
        }

    }

    private int returnToothResSmall(int count) {

        switch(count) {
            case 0:
                return R.drawable.happy;
            case 1:
                return R.drawable.soso;
            default:
                return R.drawable.sad;
        }

    }

    private void initActivity() {
        // MAIN SET UP Navigation Bar & Status Bar
        Window window = getActivity().getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0x00000000);  // transparent
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.addFlags(flags);
        }
        View decorView = getActivity().getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("http://113.198.235.232:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitCalendarService = retrofit.create(RetrofitCalendarService.class);
    }
    private void updateCalendar(@NonNull CalendarDay date) {
        MaterialCalendarView materialCalendarView = getView().findViewById(R.id.calendarView);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.addDecorator(new TodayDecorator());
        ImageView imageView10 = getActivity().findViewById(R.id.imageView10);
        ImageView imageView11 = getActivity().findViewById(R.id.imageView11);
        ImageView imageView12 = getActivity().findViewById(R.id.imageView12);
        ImageView imageView16 = getActivity().findViewById(R.id.imageView16);


        ImageView imageView13 = getActivity().findViewById(R.id.imageView13);
        ImageView imageView14 = getActivity().findViewById(R.id.imageView14);
        ImageView imageView15 = getActivity().findViewById(R.id.imageView15);
        ImageView imageView17 = getActivity().findViewById(R.id.imageView17);

        TextView textViewinit = getActivity().findViewById(R.id.textViewinit);
        TextView textView20 = getActivity().findViewById(R.id.textView20);
        TextView textView21 = getActivity().findViewById(R.id.textView21);
        TextView textView100 = getActivity().findViewById(R.id.textview100);

        textView16 = getActivity().findViewById(R.id.textView16);
        textView17 = getActivity().findViewById(R.id.textView17);
        textView16.setText(date.getDate().format(DateTimeFormatter.ofPattern("EEE", Locale.KOREAN))+"요일");
        textView17.setText(date.getDate().format(DateTimeFormatter.ofPattern("YYYY월 MM월 dd일")).toString());



        input.put("hash_key",UserAccount.getInstance().getHash_key());
        input.put("select_date",date.getDate().toString());

        retrofitCalendarService.postCalendarResult(input).enqueue(new Callback<ToothInfoDTO>() {
            @Override
            public void onResponse(Call<ToothInfoDTO> call, Response<ToothInfoDTO> response) {
                if(response.isSuccessful()) {
                    ToothInfoDTO data = response.body();

                    if(!data.getMorning_time().equals("0")) {

                        imageView10.setImageResource(returnToothResBig(data.getMorning_count()));
                        imageView13.setImageResource(returnToothResSmall(data.getMorning_count()));
                        textViewinit.setText(data.getMorning_time());

                    }
                    else if(data.getMorning_time().equals("0")) {
                        imageView10.setImageResource(R.drawable.group_254);
                        imageView13.setImageResource(R.drawable.basic_off_close);
                        textViewinit.setText("-- : --");

                    }
                    if(!data.getAfternoon_time().equals("0")) {
                        imageView11.setImageResource(returnToothResBig(data.getAfternoon_count()));
                        imageView14.setImageResource(returnToothResSmall(data.getAfternoon_count()));
                        textView20.setText(data.getAfternoon_time());

                    }
                    else if(data.getAfternoon_time().equals("0")) {
                        imageView11.setImageResource(R.drawable.group_254);
                        imageView14.setImageResource(R.drawable.basic_off_close);
                        textView20.setText("-- : --");

                    }

                    if(!data.getDinner_time().equals("0")) {
                        imageView12.setImageResource(returnToothResBig(data.getDinner_count()));
                        imageView15.setImageResource(returnToothResSmall(data.getDinner_count()));
                        textView21.setText(data.getDinner_time());


                    }
                    else if(data.getDinner_time().equals("0")) {
                        imageView12.setImageResource(R.drawable.group_254);
                        imageView15.setImageResource(R.drawable.basic_off_close);
                        textView21.setText("-- : --");

                    }

                    Log.d("Night",data.getNight_time());
                    if(!data.getNight_time().equals("0")) {
                        imageView16.setImageResource(returnToothResBig(data.getNight_count()));
                        imageView17.setImageResource(returnToothResSmall(data.getNight_count()));
                        textView100.setText(data.getNight_time());

                    }
                    else if(data.getNight_time().equals("0")){
                        imageView16.setImageResource(R.drawable.group_254);
                        imageView17.setImageResource(R.drawable.basic_off_close);
                        textView100.setText("-- : --");

                    }
                }
            }
            @Override
            public void onFailure(Call<ToothInfoDTO> call, Throwable t) {

            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialCalendarView materialCalendarView = getView().findViewById(R.id.calendarView);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.addDecorator(new TodayDecorator());
        ImageView imageView10 = getActivity().findViewById(R.id.imageView10);
        ImageView imageView11 = getActivity().findViewById(R.id.imageView11);
        ImageView imageView12 = getActivity().findViewById(R.id.imageView12);
        ImageView imageView16 = getActivity().findViewById(R.id.imageView16);


        ImageView imageView13 = getActivity().findViewById(R.id.imageView13);
        ImageView imageView14 = getActivity().findViewById(R.id.imageView14);
        ImageView imageView15 = getActivity().findViewById(R.id.imageView15);
        ImageView imageView17 = getActivity().findViewById(R.id.imageView17);

        TextView textViewinit = getActivity().findViewById(R.id.textViewinit);
        TextView textView20 = getActivity().findViewById(R.id.textView20);
        TextView textView21 = getActivity().findViewById(R.id.textView21);
        TextView textView100 = getActivity().findViewById(R.id.textview100);

        if(!init_state) {
            init_state = true;
            updateCalendar(CalendarDay.today());
        }

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                textView16 = getActivity().findViewById(R.id.textView16);
                textView17 = getActivity().findViewById(R.id.textView17);
                textView16.setText(date.getDate().format(DateTimeFormatter.ofPattern("EEE", Locale.KOREAN))+"요일");
                textView17.setText(date.getDate().format(DateTimeFormatter.ofPattern("YYYY월 MM월 dd일")).toString());



                input.put("hash_key",UserAccount.getInstance().getHash_key());
                input.put("select_date",date.getDate().toString());

                retrofitCalendarService.postCalendarResult(input).enqueue(new Callback<ToothInfoDTO>() {
                    @Override
                    public void onResponse(Call<ToothInfoDTO> call, Response<ToothInfoDTO> response) {
                        if(response.isSuccessful()) {
                            ToothInfoDTO data = response.body();

                            if(!data.getMorning_time().equals("0")) {

                                imageView10.setImageResource(returnToothResBig(data.getMorning_count()));
                                imageView13.setImageResource(returnToothResSmall(data.getMorning_count()));
                                textViewinit.setText(data.getMorning_time());

                            }
                            else if(data.getMorning_time().equals("0")) {
                                imageView10.setImageResource(R.drawable.group_254);
                                imageView13.setImageResource(R.drawable.basic_off_close);
                                textViewinit.setText("-- : --");

                            }
                            if(!data.getAfternoon_time().equals("0")) {
                                imageView11.setImageResource(returnToothResBig(data.getAfternoon_count()));
                                imageView14.setImageResource(returnToothResSmall(data.getAfternoon_count()));
                                textView20.setText(data.getAfternoon_time());

                            }
                            else if(data.getAfternoon_time().equals("0")) {
                                imageView11.setImageResource(R.drawable.group_254);
                                imageView14.setImageResource(R.drawable.basic_off_close);
                                textView20.setText("-- : --");

                            }

                            if(!data.getDinner_time().equals("0")) {
                                imageView12.setImageResource(returnToothResBig(data.getDinner_count()));
                                imageView15.setImageResource(returnToothResSmall(data.getDinner_count()));
                                textView21.setText(data.getDinner_time());


                            }
                            else if(data.getDinner_time().equals("0")) {
                                imageView12.setImageResource(R.drawable.group_254);
                                imageView15.setImageResource(R.drawable.basic_off_close);
                                textView21.setText("-- : --");

                            }

                            Log.d("Night",data.getNight_time());
                            if(!data.getNight_time().equals("0")) {
                                imageView16.setImageResource(returnToothResBig(data.getNight_count()));
                                imageView17.setImageResource(returnToothResSmall(data.getNight_count()));
                                textView100.setText(data.getNight_time());

                            }
                            else if(data.getNight_time().equals("0")){
                                imageView16.setImageResource(R.drawable.group_254);
                                imageView17.setImageResource(R.drawable.basic_off_close);
                                textView100.setText("-- : --");

                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ToothInfoDTO> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initActivity();


        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    private class TodayDecorator implements DayViewDecorator {

        private final CalendarDay today;

        public TodayDecorator() {
            today = CalendarDay.today();

        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            Log.d("test",day.toString());
            return today.equals(day);
        }

        @Override
        public void decorate(DayViewFacade view) {

        }
    }
}


