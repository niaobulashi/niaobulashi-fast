package com.niaobulashi.system.service;

import com.niaobulashi.common.core.domain.Ztree;
import com.niaobulashi.system.domain.SysMenu;
import com.niaobulashi.system.domain.SysRole;
import com.niaobulashi.system.domain.SysUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: niaobulashi-system
 * @description: 菜单 业务层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-03 12:59
 */
public interface SysMenuService {

    /**
     * 根据用户ID查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<SysMenu> selectMenusByUser(SysUser user);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    /**
     * 查询菜单集合
     *
     * @param userId 用户ID
     * @return 所有菜单信息
     */
    public List<SysMenu> selectMenuAll(Long userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Ztree> roleMenuTreeData(SysRole role, Long userId);

    /**
     * 查询所有菜单信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Ztree> menuTreeData(Long userId);

    /**
     * 查询系统所有权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Map<String, String> selectPermsAll(Long userId);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public SysMenu selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     *
     * @param parentId 菜单父ID
     * @return 结果
     */
    public int selectCountMenuByParentId(Long parentId);

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysMenu menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenu menu);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public String checkMenuNameUnique(SysMenu menu);
}
