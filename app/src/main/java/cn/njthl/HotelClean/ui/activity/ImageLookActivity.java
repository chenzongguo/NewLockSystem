package cn.njthl.HotelClean.ui.activity;

import com.jaeger.library.StatusBarUtil;
import com.previewlibrary.GPreviewActivity;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.util.UIUtils;

public class ImageLookActivity extends GPreviewActivity {
    /***
     * 重写该方法
     * 使用你的自定义布局
     **/
    @Override
    public int setContentLayout() {
        return R.layout.activity_image_look;
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.assist_green1), 30);
    }
}
