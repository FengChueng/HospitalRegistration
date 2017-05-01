package com.cjb.hospital.ui.mvp.user.userinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjb.hospital.bean.ResponseEntity;
import com.cjb.hospital.utils.RouterUtils;
import com.zyl.hospital.hospital.R;
import com.cjb.hospital.base.MvpBaseFragment;
import com.cjb.hospital.ui.mvp.user.login.LoginRegisterActivity;
import com.cjb.hospital.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 个人中心
 */
public class UserInfoFragment extends MvpBaseFragment<UserInfoContract.UserPresenter> implements UserInfoContract.UserView {
    private static final String TAG = "PersonalFragmentPatient";
    @BindView(R.id.user_portrait)
    CircleImageView userPortrait;
    @BindView(R.id.account_tv)
    TextView accountTv;
    @BindView(R.id.name_text)
    TextView nameText;
    @BindView(R.id.sex_tv)
    TextView sexTv;
    @BindView(R.id.birthday_text)
    TextView birthdayText;
    @BindView(R.id.mobilephone_text)
    TextView mobilephoneText;
    private Integer type = 1;
    private LinearLayout exitLogin;

    public static UserInfoFragment newInstance(Integer type) {
        UserInfoFragment appointmentFragmentDoctor = new UserInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        appointmentFragmentDoctor.setArguments(bundle);
        return appointmentFragmentDoctor;
    }

    @Override
    protected void initParam() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_info;
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getInt("type");
        }
//        mPresenter.get
    }


    @Override
    protected UserInfoContract.UserPresenter createPresenter() {
        return new UserPresenterImpl(this);
    }

    @Override
    public void loadUserInfo(ResponseEntity<?> userEntity) {

    }

    @Override
    public void loadError() {

    }

    @OnClick(R.id.logout)
    public void onClick() {
        SPUtils.clearAllSP(getActivity());
        RouterUtils.gotoNextNoData(getActivity(), LoginRegisterActivity.class);
    }
}