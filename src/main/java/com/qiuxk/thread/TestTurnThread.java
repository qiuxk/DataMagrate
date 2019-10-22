package com.qiuxk.thread;

/**
 *
 * @author qiuxk
 * @Description: 测试顺序执行线程
 * @create 2019/10/22
 **/
public class TestTurnThread {

    /*public static void main(String[] args) throws Exception {

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("产品经理正在规划新需求...");
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开发人员开发新需求功能");
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试人员测试新功能");
            }
        });

        System.out.println("早上:");
        *//*System.out.println("产品经理来上班了");
        System.out.println("测试人员来上班了");
        System.out.println("开发人员来上班了");*//*

        //在父进程调用子进程的join()方法后，父进程需要等待子进程运行完再继续运行。
        //System.out.println("开发人员和测试人员休息会...");
        thread1.join();
        thread1.start();
        thread2.start();
        thread3.start();

       // System.out.println("产品经理新需求规划完成!");
       // thread2.start();
        //System.out.println("测试人员休息会...");
        //thread2.join();
      //  thread3.start();
    }*/

    private static Object myLock1 = new Object();
    private static Object myLock2 = new Object();

    private static  Boolean t1Run =false;
    private static  Boolean t2Run =false;
    public static void main(String[] args) {
        final  Thread  t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock1){
                    System.out.println("产品规划需求");
                    t1Run =true;
                    myLock1.notify();
                }
            }
        });


        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock1){
                    try{
                    if(!t1Run){
                        System.out.println("开发人员没事吹水吧！");
                        myLock1.wait();
                    }

                    synchronized (myLock2){
                        System.out.println("开发人员开发需求");
                       // t2Run = true;
                        myLock2.notify();
                    }
                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }
            }
        });

        final  Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock2){

                    try{
                        if(!t2Run){
                            System.out.println("测试人员休息会");
                            myLock2.wait();
                        }
                        //if(t2Run){
                            System.out.println("测试人员测试代码");
                       // }

                    }catch (Exception e){

                    }




                }
            }
        });

        t1.start();
        t2.start();
        t3.start();


    }

}
