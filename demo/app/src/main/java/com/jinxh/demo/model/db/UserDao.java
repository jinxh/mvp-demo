package com.jinxh.demo.model.db;

import android.content.Context;

import com.jinxh.demo.model.bean.UserInfo;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by jinxh on 15/12/31.
 */
public class UserDao {

    private Dao<UserInfo, Integer> mUserDaoOpe;
    private DatabaseHelper helper;

    public UserDao(Context context) {
        try {
            helper = DatabaseHelper.getHelper(context);
            mUserDaoOpe = helper.getDao(UserInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrUpdate(final UserInfo user) {
        try {
            mUserDaoOpe.createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserInfo getUser(int id) {
        UserInfo user = null;
        try {
            user = mUserDaoOpe.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
