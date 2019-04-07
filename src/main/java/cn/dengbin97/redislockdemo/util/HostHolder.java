package cn.dengbin97.redislockdemo.util;

import org.springframework.stereotype.Component;

/**
 * Created by nowcoder on 2016/7/3.
 */
@Component
public class HostHolder {
    private static ThreadLocal<String> ids = new ThreadLocal<String>();

    public void setId(String id){
        ids.set(id);
    }

    public String getId(){
        return ids.get();
    }

    public void clear() {
        ids.remove();
    }
}
