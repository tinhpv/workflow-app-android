package com.example.workflow_s.ui.home;

import com.example.workflow_s.model.Checklist;
import com.example.workflow_s.model.Task;
import com.example.workflow_s.ui.base.MvpView;

import java.util.ArrayList;

/**
 * Workflow_S
 * Created by TinhPV on 2019-06-15
 * Copyright © 2019 TinhPV. All rights reserved
 **/


public interface HomeContract {

    interface HomePresenter {
        void onDestroy();
        void loadRunningChecklists(String userId, String orgId);
        void loadDueTasks(String userId, String orgId);
    }

    interface HomeView  {
        void setDataToChecklistRecyclerView(ArrayList<Checklist> datasource);
        void setDataToTasksRecyclerView(ArrayList<Task> datasource);
        void onFailGetChecklist();
        void onFailGetTask();
    }

    interface GetHomeDataInteractor {

        interface OnFinishedGetRunningChecklistsListener {
            void onFinishedGetChecklists(ArrayList<Checklist> checklistArrayList);
            void onFailureGetChecklists(Throwable t);
        }

        interface OnFinishedGetDueTasksListener {
            void onFinishedGetTasks(ArrayList<Task> taskArrayList);
            void onFailureGetTasks(Throwable t);
        }

        void getAllRunningChecklists(String userId, String orgId, OnFinishedGetRunningChecklistsListener onFinishedListener);
        void getAllDueTasks(String userId, String orgId, OnFinishedGetDueTasksListener onFinishedListener);

    }
}
