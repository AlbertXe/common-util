package com.ruoyi.system.service;

import com.ruoyi.system.domain.TspMutex;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2025-06-15
 */
public interface ITspMutexService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param systemCode 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TspMutex selectTspMutexBySystemCode(String systemCode);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tspMutex 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TspMutex> selectTspMutexList(TspMutex tspMutex);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tspMutex 【请填写功能名称】
     * @return 结果
     */
    public int insertTspMutex(TspMutex tspMutex);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tspMutex 【请填写功能名称】
     * @return 结果
     */
    public int updateTspMutex(TspMutex tspMutex);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param systemCodes 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTspMutexBySystemCodes(String systemCodes);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param systemCode 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTspMutexBySystemCode(String systemCode);
}
