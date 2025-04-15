package com.hcmute.appnote.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmute.appnote.R;
import com.hcmute.appnote.databinding.ItemUserBinding;
import com.hcmute.appnote.model.User;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyViewHolder> {
    private List<User> userList;

    public ListUserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding itemListUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        return new MyViewHolder(itemListUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(userList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return userList.isEmpty() ? 0 : userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ObservableField<String> stt = new ObservableField<>();
        public ObservableField<String> firstName = new ObservableField<>();
        public ObservableField<String> lastName = new ObservableField<>();
        private ItemUserBinding itemListUserBinding;

        public MyViewHolder(ItemUserBinding itemListUserBinding) {
            super(itemListUserBinding.getRoot());
            this.itemListUserBinding = itemListUserBinding;
        }


        public void setBinding(User u, int position){
            if(itemListUserBinding.getViewHolder() == null){
                itemListUserBinding.setViewHolder(this);
            }
            stt.set(String.valueOf(position));
            firstName.set(u.getFirstName());
            lastName.set(u.getLastName());
        }
    }
}
