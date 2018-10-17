package com.apple.appleuser.dao;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.apple.appleuser.domain.TeaGlobalToken;

public interface TeaGlobalTokenMapper {
    int deleteByPrimaryKey(String token);

    int insert(TeaGlobalToken record);

    int insertSelective(TeaGlobalToken record);

    TeaGlobalToken selectByPrimaryKey(String token);

    int updateByPrimaryKeySelective(TeaGlobalToken record);

    int updateByPrimaryKey(TeaGlobalToken record);
    
    @Delete("delete from TEA_GLOBAL_TOKEN")
    int deleteAll();
    
    @Select("select * from TEA_GLOBAL_TOKEN where rownum = 1")
    TeaGlobalToken getGlobalToken();
}