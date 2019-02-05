package www.nugamedical.com;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondFragment extends Fragment implements View.OnClickListener {

    private CardView btn_thermo, btn_humidity, btn_brightness, btn_dust, btn_co2;
    private TextView text_setTemperature, text_setDust, text_setHumidity, text_setCO2, text_setbrightness;
    private SwipeRefreshLayout swipe_frag2;

    public static SecondFragment newInstance(){
        SecondFragment f = new SecondFragment();
        return f;
    }

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag2, container, false);

        btn_co2 = view.findViewById(R.id.btn_co2);
        btn_brightness = view.findViewById(R.id.btn_brightness);
        btn_dust = view.findViewById(R.id.btn_dust);
        btn_thermo = view.findViewById(R.id.btn_thermo);
        btn_humidity = view.findViewById(R.id.btn_humidity);
        text_setTemperature = view.findViewById(R.id.text_setTemperature);
        text_setCO2 = view.findViewById(R.id.text_setCO2);
        text_setDust= view.findViewById(R.id.text_setDust);
        text_setHumidity = view.findViewById(R.id.text_setHumidity);
        text_setbrightness = view.findViewById(R.id.text_setBrightness);
        swipe_frag2 = view.findViewById(R.id.swipe_frag2);

        btn_thermo.setOnClickListener(this);
        btn_humidity.setOnClickListener(this);
        btn_dust.setOnClickListener(this);
        btn_brightness.setOnClickListener(this);
        btn_co2.setOnClickListener(this);

        //swipe refresh할 때 동작
        swipe_frag2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                showFragment(SecondFragment.newInstance());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_frag2.setRefreshing(false);
                    }
                },3000);
            }
        });

        //설정 값이 자동으로 Activity에 반영되도록 불러오는 코드
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        StringBuilder info1 = new StringBuilder();
        StringBuilder info2 = new StringBuilder();
        StringBuilder info3 = new StringBuilder();
        StringBuilder info4 = new StringBuilder();
        StringBuilder info5 = new StringBuilder();


        info1.append(sharedPreferences.getString("list1","18"));      //온도 불러오기(에어컨)
        info2.append(sharedPreferences.getString("list4","50"));      //습도 불러오기(가습기)
        info3.append(sharedPreferences.getString("list3","30"));      //미세먼지 불러오기 (공기청정기)
        info4.append(sharedPreferences.getString("list2","500"));      //CO2 불러오기(환풍기)
        info5.append(sharedPreferences.getString("list5","500"));      //밝기 불러오기(조명)



        text_setTemperature.setText(info1);
        text_setHumidity.setText(info2);
        text_setDust.setText(info3);
        text_setCO2.setText(info4);
        text_setbrightness.setText(info5);
//////////////////////////////////////////////////////////////

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_thermo:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentThermo()).commit();
                break;
            case R.id.btn_humidity:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentHumidity()).commit();
                break;
            case R.id.btn_dust:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentDust()).commit();
                break;
            case R.id.btn_brightness:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentBrightness()).commit();
                break;
            case R.id.btn_co2:
                getFragmentManager().beginTransaction().replace(R.id.frame_frag2, new SecondFragmentCO2()).commit();
                break;
        }
    }


    public void showFragment(Fragment f){
        getFragmentManager().beginTransaction().replace(R.id.frame_main, f).commit();
    }
}
