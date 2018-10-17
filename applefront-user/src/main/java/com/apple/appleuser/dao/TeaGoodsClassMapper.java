package com.apple.appleuser.dao;



import com.apple.appleuser.domain.TeaGoodsClass;
import com.apple.appleuser.domain.TeaGoodsClassKey;

public interface TeaGoodsClassMapper {
    int deleteByPrimaryKey(TeaGoodsClassKey key);

    int insert(TeaGoodsClass record);

    int insertSelective(TeaGoodsClass record);

    TeaGoodsClass selectByPrimaryKey(TeaGoodsClassKey key);

    int updateByPrimaryKeySelective(TeaGoodsClass record);

    int updateByPrimaryKey(TeaGoodsClass record);
}