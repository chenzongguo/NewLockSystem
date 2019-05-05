package com.thl.newlocksystem.ui.view;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public interface ILockedAtView {
    List<EditText> GetEtList();
    List<TextView> GetTvList();
}
