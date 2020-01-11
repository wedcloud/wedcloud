package club.wedcloud.www.feture;

/**
 * 手写FutureTask
 */
public class Mian {

  public static void main(String[] args) throws InterruptedException {
    FutureTaskTest futureTaskTest = new FutureTaskTest();
    FutureData data = futureTaskTest.request("test");
    System.out.println("main--数据发送成功");
    Thread.sleep(1000);
    System.out.println("其他业务逻辑");
    Long time = System.currentTimeMillis();
    String s = data.getRequest();
    System.out.println("主线程获得异步线程结果"+s+",时间是："+(System.currentTimeMillis()-time));
  }
}
