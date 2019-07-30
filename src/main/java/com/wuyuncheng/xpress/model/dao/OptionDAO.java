package com.wuyuncheng.xpress.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyuncheng.xpress.model.entity.Option;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OptionDAO extends BaseMapper<Option> {

    List<Map<String, String>> selectOptions();
    int updateOptions(String key, String value);

}
