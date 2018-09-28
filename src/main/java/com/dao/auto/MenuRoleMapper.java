package com.dao.auto;

import com.mybatis.auto.auto.MenuRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    long countByExample(MenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    int deleteByExample(MenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    int insert(MenuRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    int insertSelective(MenuRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    List<MenuRole> selectByExample(MenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    MenuRole selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    int updateByExampleSelective(@Param("record") MenuRole record, @Param("example") MenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    int updateByExample(@Param("record") MenuRole record, @Param("example") MenuRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    int updateByPrimaryKeySelective(MenuRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_role
     *
     * @mbg.generated Fri Sep 28 11:07:04 GMT+08:00 2018
     */
    int updateByPrimaryKey(MenuRole record);
}