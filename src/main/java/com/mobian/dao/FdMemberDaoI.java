package com.mobian.dao;

import com.mobian.model.TfdMember;

import java.util.List;

/**
 * FdMember数据库操作类
 * 
 * @author John
 * 
 */
public interface FdMemberDaoI extends BaseDaoI<TfdMember> {

    List<TfdMember> findBy(String hql, int first);
}
