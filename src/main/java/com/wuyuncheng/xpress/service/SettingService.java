package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.param.SettingParam;

import java.util.Map;

public interface SettingService {

    Map<String, String> listSettings();
    void updateSettings(SettingParam settingParam);

}
