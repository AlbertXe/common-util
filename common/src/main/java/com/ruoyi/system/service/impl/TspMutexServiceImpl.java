package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TspMutex;
import com.ruoyi.system.mapper.TspMutexMapper;
import com.ruoyi.system.service.ITspMutexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-15
 */
@Service
public class TspMutexServiceImpl implements ITspMutexService 
{
    @Autowired
    private TspMutexMapper tspMutexMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param systemCode 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TspMutex selectTspMutexBySystemCode(String systemCode)
    {
        return tspMutexMapper.selectTspMutexBySystemCode(systemCode);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tspMutex 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TspMutex> selectTspMutexList(TspMutex tspMutex)
    {
        return tspMutexMapper.selectTspMutexList(tspMutex);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param tspMutex 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTspMutex(TspMutex tspMutex)
    {
        return tspMutexMapper.insertTspMutex(tspMutex);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param tspMutex 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTspMutex(TspMutex tspMutex)
    {
        return tspMutexMapper.updateTspMutex(tspMutex);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param systemCodes 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTspMutexBySystemCodes(String systemCodes)
    {
        String[] arr = new String[1];
        arr[0] = systemCodes;
        return tspMutexMapper.deleteTspMutexBySystemCodes(arr);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param systemCode 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTspMutexBySystemCode(String systemCode)
    {
        return tspMutexMapper.deleteTspMutexBySystemCode(systemCode);
    }
}
