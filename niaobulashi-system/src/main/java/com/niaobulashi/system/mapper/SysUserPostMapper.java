package com.niaobulashi.system.mapper;

import com.niaobulashi.system.domain.SysUserPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: niaobulashi-system
 * @description: 在线用户 数据层
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-01 23:37
 */
public interface SysUserPostMapper {

    /**
     * 通过用户ID删除用户和岗位关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserPostByUserId(Long userId);

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    public int countUserPostById(Long postId);

    /**
     * 批量删除用户和岗位关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserPost(Long[] ids);

    /**
     * 批量新增用户岗位信息
     *
     * @param userPostList 用户角色列表
     * @return 结果
     */
    public int batchUserPost(List<SysUserPost> userPostList);
}
