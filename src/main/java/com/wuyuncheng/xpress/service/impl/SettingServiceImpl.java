package com.wuyuncheng.xpress.service.impl;

import com.wuyuncheng.xpress.model.dao.OptionDAO;
import com.wuyuncheng.xpress.model.param.SettingParam;
import com.wuyuncheng.xpress.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private OptionDAO optionDAO;

    @Override
    public Map<String, String> listSettings() {
        List<Map<String, String>> maps = optionDAO.selectOptions();
        Map<String, String> map = new HashMap<>();
        maps.stream()
                .forEach(item -> {
                    map.put(item.get("key"), item.get("value"));
                });
        return map;
    }

    @Transactional
    @Override
    public void updateSettings(SettingParam settingParam) {
        optionDAO.updateOptions("title", settingParam.getTitle());
        optionDAO.updateOptions("description", settingParam.getDescription());
        optionDAO.updateOptions("keywords", settingParam.getKeywords());
    }

}
