package com.ddanwang.tourism.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ddanwang.tourism.R;
import com.ddanwang.tourism.db.CityVo;
import com.ddanwang.tourism.db.DestinationVo;
import com.ddanwang.tourism.db.dao.CityDao;
import com.ddanwang.tourism.db.dao.DestinationDao;
import com.ddanwang.tourism.view.CircleImageView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by WeiWenjun
 * 2016/3/22
 * 17:58
 * UserInfoFragment
 */
public class UserInfoFragment extends BaseFragment {
    @InjectView(R.id.background)
    ImageView background;
    @InjectView(R.id.head_image)
    CircleImageView headImage;
    @InjectView(R.id.userName)
    TextView userName;
    @InjectView(R.id.login_linear)
    LinearLayout loginLinear;
    @InjectView(R.id.login_btn)
    Button loginBtn;
    @InjectView(R.id.unlogin_linear)
    LinearLayout unloginLinear;

    private CityDao citydao;
    private List<CityVo> mCityList;
    private DestinationDao destDao;

    @Override
    protected int initPageLayoutId() {
        return R.layout.fragment_userinfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.head_image, R.id.userName, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_image:
                break;
            case R.id.userName:
                break;
            case R.id.login_btn:
                saveCityInfo();
                saveDestinationInfo();
                break;
        }
    }

    private void saveCityInfo() {
        citydao = new CityDao(mContext);

        CityVo vo1 = new CityVo();
        vo1.setId(1);
        vo1.setCityName("常州");
        vo1.setCityNameEng("changzhou");
        vo1.setShowInMainCity(0);
        vo1.setShowInMainDest(1);
        vo1.setHaveDest(0);
        vo1.setCityDesc("常州恐龙园");
        vo1.setCityBg("http://shanxi.sinaimg.cn/2012/0821/U7514P1196DT20120821150906.jpg");
        vo1.setCityPic("http://img2.imgtn.bdimg.com/it/u=505992161,2794589883&fm=21&gp=0.jpg");
        citydao.saveUserVO(vo1);

        CityVo vo2 = new CityVo();
        vo2.setId(2);
        vo2.setCityName("上海");
        vo2.setCityNameEng("shanghai");
        vo2.setShowInMainCity(1);
        vo2.setShowInMainDest(1);
        vo2.setHaveDest(1);
        vo2.setCityDesc("上海市中国的");
        vo2.setCityBg("http://shanxi.sinaimg.cn/2012/0821/U7514P1196DT20120821150906.jpg");
        vo2.setCityPic("http://img2.imgtn.bdimg.com/it/u=505992161,2794589883&fm=21&gp=0.jpg");
        citydao.saveUserVO(vo2);

        CityVo vo3 = new CityVo();
        vo3.setId(3);
        vo3.setCityName("南京");
        vo3.setCityNameEng("nanjing");
        vo3.setShowInMainCity(1);
        vo3.setShowInMainDest(1);
        vo3.setHaveDest(1);
        vo3.setCityDesc("南京美好的");
        vo3.setCityBg("http://shanxi.sinaimg.cn/2012/0821/U7514P1196DT20120821150906.jpg");
        vo3.setCityPic("http://img2.imgtn.bdimg.com/it/u=505992161,2794589883&fm=21&gp=0.jpg");
        citydao.saveUserVO(vo3);

        CityVo vo4 = new CityVo();
        vo4.setId(4);
        vo4.setCityName("东京");
        vo4.setCityNameEng("Tokyo");
        vo4.setShowInMainCity(1);
        vo4.setShowInMainDest(1);
        vo4.setHaveDest(1);
        vo4.setCityDesc("东京是日本的");
        vo4.setCityBg("http://shanxi.sinaimg.cn/2012/0821/U7514P1196DT20120821150906.jpg");
        vo4.setCityPic("http://img2.imgtn.bdimg.com/it/u=505992161,2794589883&fm=21&gp=0.jpg");
        citydao.saveUserVO(vo4);
    }

    private void saveDestinationInfo() {
        destDao = new DestinationDao(mContext);
        DestinationVo d1 = new DestinationVo();
        d1.setId(1);
        d1.setCityId(1);
        d1.setDestName("常州中华恐龙园");
        d1.setDestAddr("常州市新北区汉江路1号");
        d1.setHighlights("在以恐龙为主题的大型游乐园内，体验各种惊险的游乐项目。");
        d1.setDestPic("http://pic3.40017.cn/scenery/destination/2015/06/10/14/75YDqT_540x304_00.jpg");
        d1.setIsHaveComment(1);
        d1.setIsHavePackage(1);
        d1.setIsHaveTotel(1);
        d1.setIsTicket(1);
        d1.setIsHotInCity(1);
        d1.setLevel("5A");
        d1.setTikect("230");
        d1.setTicketDesc("门市价：230RMB。中华恐龙园成人票230元，夜公园100元。");
        d1.setIsHaveDest(0);
        d1.setOpenTime("9:00 ~ 17:00;夏季夜公园开放时间:16:00 ~ 22:00");
        d1.setTelephone("400-616-6600");
        d1.setWebsite("http://www.konglongcheng.com/");
        destDao.saveVO(d1);
    }
}
