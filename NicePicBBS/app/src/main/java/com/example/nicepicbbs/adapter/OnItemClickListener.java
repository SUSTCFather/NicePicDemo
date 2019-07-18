/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2008-2019. All rights reserved.
 */

package com.example.nicepicbbs.adapter;

import android.view.View;

import java.util.List;

/******************************************************************<br>
 * @author 尹宝华 yxw565607 <br>
 * @since 2019/04/10 <br>
 * description : ColumView整行点击事件<br>
 * Copyright (C) Huawei Technologies Co., Ltd. 2008-2020. All rights reserved.
 ******************************************************************/
public interface OnItemClickListener {
    /**
     * onItemClick
     *
     * @param view View
     * @param position position
     */
    void onItemClick(View view, List<String> urlList, int position);
}
