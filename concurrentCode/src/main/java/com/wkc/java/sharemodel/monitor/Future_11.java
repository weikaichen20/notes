package com.wkc.java.sharemodel.monitor;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
@Slf4j(topic = "Future")
public class Future_11 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new People().start();
        }
        TimeUnit.SECONDS.sleep(2);
        Set<Integer> keys = MailBoxes.getKeys();
        for (Integer key : keys) {
            new Postman(key, "hello==>"+key).start();
        }

    }
}

@Slf4j(topic = "people")
class People extends Thread {
    @Override
    public void run() {
        GrardObject go = MailBoxes.generateGO();
        log.debug("开始收信 id【{}】",go.getId());
        String o = (String) go.get(5000);
        log.debug("收信 id【{}】 内容【{}】",go.getId(),o);
    }
}

@Slf4j(topic = "postman")
class Postman extends Thread {
    private int id;
    private String res;

    public Postman(int id,String res){
        this.res=res;
        this.id=id;
    }

    @Override
    public void run() {
        GrardObject go = MailBoxes.getGO(id);
        log.debug("准备信件 id【{}】",go.getId());
        go.complete(res);
        log.debug("送件完毕 id【{}】",go.getId());
    }
}

class MailBoxes {
    private static Map<Integer, GrardObject> box = new Hashtable<>();

    public static int id = 1;

    public static GrardObject getGO(Integer id) {
        return box.remove(id);
    }

    private synchronized static Integer generateId() {
        return id++;
    }

    public static GrardObject generateGO() {
        GrardObject grardObject = new GrardObject(generateId());
        box.put(grardObject.getId(), grardObject);
        return grardObject;
    }

    public static Set<Integer> getKeys(){
        return box.keySet();
    }

}

class GrardObject {
    private Integer id;
    private Object res;

    public GrardObject(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public synchronized Object get() {
        while (res == null) {

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return res;
    }

    public synchronized Object get(long time) {
        long begin = System.currentTimeMillis();

        long pass = 0;
        while (res == null) {
            long wait = time - pass;
            if (wait <= 0)
                break;
            try {
                this.wait(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pass = System.currentTimeMillis() - begin;
        }
        return res;
    }


    public synchronized void complete(Object res) {
        this.res = res;
        this.notifyAll();
    }

}
