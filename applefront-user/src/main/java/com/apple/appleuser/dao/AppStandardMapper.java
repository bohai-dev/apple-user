package com.apple.appleuser.dao;

import com.apple.appleuser.domain.AppStandard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AppStandardMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppStandard record);

    int insertSelective(AppStandard record);

    AppStandard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppStandard record);

    int updateByPrimaryKey(AppStandard record);

    //查询id
    @Select("select APPLE_STANDARD_ID_SEQ.NEXTVAL from dual")
    String generateAttrId();

    /**
     * 根据规格名称查询规格
     * @param name
     * @return
     */
    @Select("select * from APP_STANDARD where NAME=#{0}")
    List<AppStandard> selectByName(String name);

    /**
     * 根据商品id更新删除状态 0->1
     * @param goodsId 商品id
     * @return
     */
    @Update("update APP_STANDARD set DELETE_FLAG=1 where GOOD_ID=#{0}")
    int deleteByGoodsId(String goodsId);

    /**
     * 根据商品id查询规格列表
     * @param goodsId
     * @return
     */
    @Select("select * from APP_STANDARD where GOOD_ID=#{0}")
    List<AppStandard> selectByGoodsId(String goodsId);
}