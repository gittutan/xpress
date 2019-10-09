package com.wuyuncheng.xpress.controller;

import com.wuyuncheng.xpress.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Map;

@Controller
public class BaseController {

    @Autowired
    private SettingService settingService;

    public String render(String view, Model model) {
        Map<String, String> settings = settingService.listSettings();
        if (null != model) {
            model.addAttribute("title", settings.get("title"));
            model.addAttribute("keywords", settings.get("keywords"));
            model.addAttribute("description", settings.get("description"));
            model.addAttribute("navbar", settings.get("navbar"));
            model.addAttribute("siteURL", settings.get("siteURL"));
            model.addAttribute("logo", settings.get("logo"));
        }
        return view;
    }

}
