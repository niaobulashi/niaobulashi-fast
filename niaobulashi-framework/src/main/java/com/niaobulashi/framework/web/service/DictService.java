package com.niaobulashi.framework.web.service;

import com.niaobulashi.system.domain.SysDictData;
import com.niaobulashi.system.service.SysDictDataService;
import com.niaobulashi.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: niaobulashi-fast
 * @description: RuoYi首创 html调用 thymeleaf 实现字典读取
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-17 15:17
 */
@Service("dict")
public class DictService {

    @Autowired
    private SysDictDataService dictDataService;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<SysDictData> getType(String dictType) {
        return dictDataService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue) {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
