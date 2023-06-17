package pers.jcl.community.controller;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.jcl.community.Service.DiscussPostService;
import pers.jcl.community.Service.UserService;
import pers.jcl.community.entity.DiscussPost;
import pers.jcl.community.entity.Page;
import pers.jcl.community.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;
//访问首页返回对象
    @RequestMapping(path="/index",method = RequestMethod.GET)
    public String getindexPage(Model model, Page page){
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        //直接取出前10条数据放在list里面
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());

        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if(list!=null){
            for (DiscussPost post : list) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "index";
    }
}
