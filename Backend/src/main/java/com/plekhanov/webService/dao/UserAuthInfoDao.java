package com.plekhanov.webService.dao;

import com.plekhanov.webService.entities.UserAuthInfo;


public interface UserAuthInfoDao extends BaseDao<UserAuthInfo, Long>{

    UserAuthInfo findByName(String userName);
}
