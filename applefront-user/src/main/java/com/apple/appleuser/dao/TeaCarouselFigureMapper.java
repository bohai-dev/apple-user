package com.apple.appleuser.dao;



import com.apple.appleuser.domain.TeaCarouselFigure;

public interface TeaCarouselFigureMapper {
    int deleteByPrimaryKey(String figureId);

    int insert(TeaCarouselFigure record);

    int insertSelective(TeaCarouselFigure record);

    TeaCarouselFigure selectByPrimaryKey(String figureId);

    int updateByPrimaryKeySelective(TeaCarouselFigure record);

    int updateByPrimaryKey(TeaCarouselFigure record);
}