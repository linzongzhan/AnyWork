package com.qgstudio.anywork.main;

import com.qgstudio.anywork.common.PreLoading;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.mvp.BasePresenter;
import com.qgstudio.anywork.mvp.BaseView;

public class HomeContract {
    interface HomeView extends BaseView, PreLoading {
        public void onMyClassGot(Organization organization);


    }

    interface HomePresenter extends BasePresenter<HomeView> {
        public void getJoinOrganization();
    }
}
