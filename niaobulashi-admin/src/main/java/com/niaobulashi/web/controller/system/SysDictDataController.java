package com.niaobulashi.web.controller.system;

import com.niaobulashi.common.annotation.Log;
import com.niaobulashi.common.core.controller.BaseController;
import com.niaobulashi.common.core.domain.AjaxResult;
import com.niaobulashi.common.core.page.TableDataInfo;
import com.niaobulashi.common.enums.BusinessType;
import com.niaobulashi.common.utils.poi.ExcelUtil;
import com.niaobulashi.framework.util.ShiroUtils;
import com.niaobulashi.system.domain.SysDictData;
import com.niaobulashi.system.service.SysDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: niaobulashi-fast
 * @description: 数据字典信息 控制层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-19 21:08
 */
@Controller
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    private String prefix = "system/dict/data";

    @Autowired
    private SysDictDataService dictDataService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictData() {
        return prefix + "/data";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictData dictData) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        return util.exportExcel(list, "字典数据");
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, ModelMap mmap) {
        mmap.put("dictType", dictType);
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDictData dict) {
        dict.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, ModelMap mmap) {
        mmap.put("dict", dictDataService.selectDictDataById(dictCode));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictData dict) {
        dict.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(dictDataService.updateDictData(dict));
    }

    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dictDataService.deleteDictDataByIds(ids));
    }
}
