package org.example.bigevent.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.bigevent.pojo.User;
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM `user` WHERE username = #{username}")
    User findByuserName(String username);
    @Insert("INSERT INTO `user`(username,`password`,create_time,update_time)"+
    " VALUES (#{username},#{md5String},now(),now())")
    void add(String username, String md5String);
}
