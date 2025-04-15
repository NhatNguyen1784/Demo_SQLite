package com.hcmute.appnote.ui;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hcmute.appnote.R;
import com.hcmute.appnote.adapter.ListUserAdapter;
import com.hcmute.appnote.databinding.ActivityDataBindingForRecyclerViewBinding;
import com.hcmute.appnote.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataBindingForRecyclerView extends AppCompatActivity {

    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter adapter;
    private ActivityDataBindingForRecyclerViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_for_recycler_view);
        title.set("Vi du ve DataBinding cho Recycler View");
        binding.setHome(this);
        setData();
        binding.rcViewa.setLayoutManager(new LinearLayoutManager(this));
        binding.rcViewa.setAdapter(adapter);
    }

    private void setData() {
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            User u = new User();
            u.setFirstName("Nhat "+i);
            u.setLastName("Nguyen");
            userList.add(u);
        }
        adapter = new ListUserAdapter(userList);
    }
}