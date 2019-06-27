package com.example.workflow_s.ui.login;

import com.example.workflow_s.model.Organization;
import com.example.workflow_s.model.User;

import java.util.ArrayList;

/**
 * Workflow_S
 * Created by TinhPV on 2019-06-21
 * Copyright © 2019 TinhPV. All rights reserved
 **/


public interface LoginContract {

    interface LoginPresenter {
        void addUserToDB(User user);
        void getCurrentOrganization(String userId);
        void onDestroy();
    }

    interface LoginView {
        void saveCurrentUserToPreference(User user);
        void saveCurrentOrganizationToPreference(Organization organization);
        void navigateToCodeVerifyActivity();
        void navigateToHomeActivity();
        void onFinishedAddUser();
    }

    interface GetLoginDataInteractor {

        interface OnFinishedGetOrganizationListener {
            void onFinished(Organization currentOrganization);
            void onFailure(Throwable t);
        }

        interface OnFinishedSaveUserListener {
            void onFinished(User user);
            void onFailure(Throwable t);
        }

        void getCurrentOrganization(String userId,
                                    OnFinishedGetOrganizationListener onFinishedListener);

        void saveUserToDB(User user,
                          LoginContract.GetLoginDataInteractor.OnFinishedSaveUserListener onFinishedListener);

    }
}