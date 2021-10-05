package com.example.tugas14_anisahasna.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tugas14_anisahasna.MainActivity;
import com.example.tugas14_anisahasna.R;
import com.example.tugas14_anisahasna.adapter.SliderAdapter;
import com.example.tugas14_anisahasna.databinding.FragmentHomeBinding;
import com.example.tugas14_anisahasna.helper.SharedPref;
import com.example.tugas14_anisahasna.model.SliderItem;
import com.example.tugas14_anisahasna.ui.LoginActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TextView username;
    Button btnLogout;
    SliderView sliderView;
    private SliderAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        username = root.findViewById(R.id.username);
        btnLogout = root.findViewById(R.id.btn_logout);

        //check if user is logged in
        if (!SharedPref.getInstance(getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }

        //getting logged in user name
        String loggedUsename = SharedPref.getInstance(getContext()).LoggedInUser();
        username.setText(loggedUsename + "!");

        //logging out
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Berhasil logout", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                SharedPref.getInstance(getContext()).logout();
            }
        });

        sliderView = root.findViewById(R.id.imageSlider);

        List<SliderItem> mSliderItems = new ArrayList<>();

        SliderItem sliderItem1 = new SliderItem();
        sliderItem1.setDescription("Pengumuman Penting!\nPerubahan Aturan Perusaan, Cek Disini.");
        sliderItem1.setImageUrl("https://i.imgur.com/OCMV0kl.jpeg");
        mSliderItems.add(sliderItem1);

        SliderItem sliderItem2 = new SliderItem();
        sliderItem2.setDescription("Bagaimana cara berkomunikasi yang baik?\nCek 7 Tips Berkomunikasi dengan Rekan Kerja Disini.");
        sliderItem2.setImageUrl("https://i.imgur.com/pjjzgZO.jpeg");
        mSliderItems.add(sliderItem2);

        SliderItem sliderItem3 = new SliderItem();
        sliderItem3.setDescription("Bingung cara menggunakan Emply?\nCek FAQ Terkait Penggunaan Emply Disini.");
        sliderItem3.setImageUrl("https://i.imgur.com/kpvg0UE.jpeg");
        mSliderItems.add(sliderItem3);

        adapter = new SliderAdapter(getContext(), mSliderItems);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });

        return root;
    }

//    public void renewItems(View view) {
//        List<SliderItem> sliderItemList = new ArrayList<>();
//        //dummy data
//        for (int i = 0; i < 5; i++) {
//            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Slider Item " + i);
//            if (i % 2 == 0) {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//            } else {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
//            }
//            sliderItemList.add(sliderItem);
//        }
//        adapter.renewItems(sliderItemList);
//    }
//
//    public void removeLastItem(View view) {
//        if (adapter.getCount() - 1 >= 0)
//            adapter.deleteItem(adapter.getCount() - 1);
//    }
//
//    public void addNewItem(View view) {
//        SliderItem sliderItem = new SliderItem();
//        sliderItem.setDescription("Slider Item Added Manually");
//        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//        adapter.addItem(sliderItem);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}