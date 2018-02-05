package com.mobian.dao.impl;

import com.mobian.dao.FdMemberDaoI;
import com.mobian.model.TfdMember;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FdMemberDaoImpl extends BaseDaoImpl<TfdMember> implements FdMemberDaoI {

    @Override
    public List<TfdMember> findBy(String hql, int first) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.setFirstResult(first).list();
    }
}
