package com.example.workflow_s.ui.taskdetail.dialog.assignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.workflow_s.R;
import com.example.workflow_s.model.ChecklistMember;
import com.example.workflow_s.model.TaskMember;
import com.example.workflow_s.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Workflow_S
 * Created by TinhPV on 2019-07-06
 * Copyright © 2019 TinhPV. All rights reserved
 **/


public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {


    EventListener listener;

    public interface EventListener {
        void onEvent(String userId, boolean doAssign);
    }

    private List<User> mUserList;
    private ArrayList<TaskMember> mTaskMembers;
    private ArrayList<ChecklistMember> mChecklistMembers;
    private RecyclerView mRecyclerView;

    public void setUserList(List<User> userList) {
        mUserList = userList;
    }

    public void setTaskMembers(ArrayList<TaskMember> taskMembers) {
        mTaskMembers = taskMembers;
        notifyDataSetChanged();
    }

    public void setChecklistMembers(ArrayList<ChecklistMember> checklistMembers) {
        mChecklistMembers = checklistMembers;
    }

    public MemberAdapter(EventListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        int layoutId = R.layout.recycler_item_user_assigning_detail;
        View view = layoutInflater.inflate(layoutId, viewGroup, false);
        MemberViewHolder viewHolder = new MemberViewHolder(view);
        return viewHolder;
    }

    private boolean isChecklistMember(String userId) {
        for (ChecklistMember member : mChecklistMembers) {
            if (member.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    private User getUser(String userId) {
        for (User user : mUserList) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    private boolean isTaskMember(String userId) {
        for (TaskMember member : mTaskMembers) {
            if (member.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder memberViewHolder, final int i) {
        User user = getUser(mChecklistMembers.get(i).getUserId());

        memberViewHolder.mMemberName.setText(user.getName());
        memberViewHolder.mEmail.setText(user.getEmail());
        String profileUrlString = user.getAvatar();
        if (profileUrlString ==  null) {
            memberViewHolder.mAvatar.setImageDrawable(ContextCompat.getDrawable(memberViewHolder.view.getContext(), R.drawable.default_avatar));
        } else {
            Glide.with(memberViewHolder.view.getContext()).load(profileUrlString).into(memberViewHolder.mAvatar);
        }

        if (isTaskMember(user.getId())) {
            memberViewHolder.btAssign.setVisibility(View.INVISIBLE);
            memberViewHolder.btUnassign.setVisibility(View.VISIBLE);
            memberViewHolder.btUnassign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // DONE - HANDLE UNASSIGN USER HERE
                    //int index = mRecyclerView.getChildLayoutPosition(v);
                    listener.onEvent(mChecklistMembers.get(i).getUserId(), false);
                }
            });
        } else {
            memberViewHolder.btAssign.setVisibility(View.VISIBLE);
            memberViewHolder.btUnassign.setVisibility(View.INVISIBLE);
            memberViewHolder.btAssign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // DONE - HANDLE UNASSIGN USER HERE
                    //int index = mRecyclerView.getChildLayoutPosition(v);
                    listener.onEvent(mChecklistMembers.get(i).getUserId(), true);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        if (null == mChecklistMembers) {
            return 0;
        }
        return mChecklistMembers.size();
    }



    public class MemberViewHolder extends RecyclerView.ViewHolder {

        public TextView mMemberName;
        public TextView mEmail;
        public ImageView mAvatar;
        public ImageButton btAssign, btUnassign;
        public View view;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            mMemberName = itemView.findViewById(R.id.tv_user_display_name);
            mEmail = itemView.findViewById(R.id.tv_user_email);
            mAvatar = itemView.findViewById(R.id.img_user_avatar);
            btUnassign = itemView.findViewById(R.id.bt_unassign_user);
            btAssign = itemView.findViewById(R.id.bt_assign_user);
        }
    }
}
