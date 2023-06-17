package pers.jcl.community.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jcl.community.Dao.DiscussPostMapper;
import pers.jcl.community.entity.DiscussPost;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;
    //查询某一页的数据

    /**
     *
     * @param userId 查询指定用户的讨论帖子，如果传入 0 则不按用户筛选
     * @param offset 查询起始位置
     * @param limit  查询数量上限
     * @return 返回查询结果的讨论帖子列表，如果没有查到则返回空列表
     */
    public List<DiscussPost> findDiscussPosts(int userId,int offset,int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }
    //查询行数
    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscusPostRows(userId);
    }
}
