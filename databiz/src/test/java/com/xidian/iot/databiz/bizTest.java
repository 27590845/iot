package com.xidian.iot.databiz;

import com.xidian.iot.database.entity.NodeAttrStd;
import com.xidian.iot.database.entity.NodeAttrStdExample;
import com.xidian.iot.database.entity.Scene;
import com.xidian.iot.database.entity.SceneExample;
import com.xidian.iot.database.mapper.NodeAttrStdMapper;
import com.xidian.iot.database.mapper.SceneMapper;
import org.apache.kafka.common.errors.InterruptException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: Hansey
 * @date: 2020-09-13 15:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/application-mybatis-dynamic.xml"})
@ContextConfiguration(locations = {"classpath:spring/application-mybatis-def.xml"})
public class bizTest {

    private ScheduledExecutorService mScheduledExecutorService;
    private Future mFuture;

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    private void assertTrue(boolean b) {
    }

    @Resource
    SceneMapper sceneMapper;

    @Test
    public void getScene() {
        List<Scene> scenes1 = sceneMapper.selectByExample(new SceneExample());
        System.out.println(scenes1.size());

        List<Scene> scenes2 = sceneMapper.selectByExample(new SceneExample());
        System.out.println(scenes2.size());
    }

    @Autowired
    private NodeAttrStdMapper nodeAttrStdMapper;

    @Test
    public void addNas() {
        NodeAttrStd nodeAttrStd = new NodeAttrStd();
        nodeAttrStd.setNasId(100000000l);
        nodeAttrStd.setNasKey("test");
        nodeAttrStd.setNasSym("o");
        nodeAttrStd.setNasUnit("o");
        nodeAttrStd.setNasDesc("测试");
        nodeAttrStdMapper.insertSelective(nodeAttrStd);
    }

    @Test
    public void getNas() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            List<NodeAttrStd> nodeAttrStds = null;
            List<Scene> scenes = null;
            try {
                nodeAttrStds = nodeAttrStdMapper.selectByExample(new NodeAttrStdExample());
                scenes = sceneMapper.selectByExample(new SceneExample());
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.print(nodeAttrStds == null ? "null" : nodeAttrStds.size() + "  ");
            System.out.println(scenes == null ? "null" : scenes.size());
            Thread.sleep(10000);
            ExecutorService taskExec = Executors.newCachedThreadPool();
            Future<?> task = taskExec.submit(new Runnable() {
                @Override
                public void run() {

                }
            });
            task.cancel(true);
        }
    }

    @Test
    public void hhhh() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<?> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(i);
                }
                return null;
            }
        });
        executor.execute(futureTask);
        System.out.println("futureTask start");
        try {
            System.out.println("sssssssss--------------------------------------");
            Thread.sleep(10);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            futureTask.cancel(true);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            futureTask.cancel(true);
        }
        System.out.println("futureTask cancel");
    }

    @Test
    public void ss(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<?> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for(int i=0;i<10000&&!Thread.currentThread().isInterrupted();i++){
                    System.out.println(i);
                }
                return null;
            }
        });
        executor.execute(futureTask);
        System.out.println("futureTask start");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        futureTask.cancel(true);
        System.out.println("futureTask cancel");
    }

    @Test
    public void sssss(){
        mScheduledExecutorService = Executors.newScheduledThreadPool(2);
        mFuture = mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("----------------------------");
            }
        }, 1, 3, TimeUnit.SECONDS);

        System.out.println("slepp thread");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("stop slpped thread");
        CancelHeartBeat();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 停止发送心跳
     */
    public void CancelHeartBeat(){
        if (mFuture != null) {
            mFuture.cancel(true);
        }
//        if(mScheduledExecutorService != null){
//            mScheduledExecutorService.shutdownNow();
//        }
        mFuture = null;
        mScheduledExecutorService = null;
        System.out.println("stop suceed");
    }

    @Test
    public void ssssss(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName()+" 依旧在运行");
                }
            }
        });

        thread.start();
        // 执行线程的interrupt()方法
        try {
            thread.interrupt();
        } catch (InterruptException e) {
            e.printStackTrace();
            thread.interrupt();
        }
        System.out.println("已经执行中止");
        try {
            System.out.println("sleep");
            Thread.sleep(100l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");

    }
}
