package com.wkc.java.threadbasic;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
@Slf4j
public class RunAndStart_9 {
    public static void main(String[] args) {
        Thread runing = new Thread("t1") {
            @Override
            public void run() {
                log.debug("runing");
            }
        };
        //runing.run();//直接调run，主线程执行
        log.debug("启动前状态：{}",runing.getState());
        runing.start();
        log.debug("启动后状态：{}",runing.getState());

        log.debug("do something");
    }
}
