package pers.jcl.community.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.jcl.community.entity.DiscussPost;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //查询评论
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);
    //查询帖子的行数

    //给参数取别名，如果只有一个参数，在<if>中使用必须加别名。
    int selectDiscusPostRows(@Param("userId") int userId);




}
