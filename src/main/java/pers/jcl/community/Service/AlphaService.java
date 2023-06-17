package pers.jcl.community.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class AlphaService {
    public AlphaService() {
        System.out.println("实例化构造函数");
    }
    @PostConstruct
    public void init(){
        System.out.println("初始化alphaservice");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁alphaservice");
    }
}
