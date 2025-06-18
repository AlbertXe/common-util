package com.ruoyi.system.domain;

/**
 * 【请填写功能名称】对象 tsp_mutex
 * 
 * @author ruoyi
 * @date 2025-06-15
 */
public class TspMutex
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String systemCode;

    /** $column.columnComment */
    private String subSystemCode;

    /** $column.columnComment */
    private String name;

    public void setSystemCode(String systemCode) 
    {
        this.systemCode = systemCode;
    }

    public String getSystemCode() 
    {
        return systemCode;
    }
    public void setSubSystemCode(String subSystemCode) 
    {
        this.subSystemCode = subSystemCode;
    }

    public String getSubSystemCode() 
    {
        return subSystemCode;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

}
