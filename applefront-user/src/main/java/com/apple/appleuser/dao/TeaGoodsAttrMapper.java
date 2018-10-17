package com.apple.appleuser.dao;



import com.apple.appleuser.domain.TeaGoodsAttr;
import com.apple.appleuser.domain.TeaGoodsAttrKey;

public interface TeaGoodsAttrMapper {
    int deleteByPrimaryKey(TeaGoodsAttrKey key);

    int insert(TeaGoodsAttr record);

    int insertSelective(TeaGoodsAttr record);

    TeaGoodsAttr selectByPrimaryKey(TeaGoodsAttrKey key);

    int updateByPrimaryKeySelective(TeaGoodsAttr record);

    int updateByPrimaryKey(TeaGoodsAttr record);
}