package club.wedcloud.www.feture;

public class RealData {
  private String result;

  public RealData(String requestData){
    System.out.println("正在使用 RealData 进行网络请求，requestData"+requestData+",开始");
    try{
      //模拟业务操作
      Thread.sleep(3000);
    }catch (Exception e){

    }
    System.out.println("操作结束");
    this.result="结果是："+requestData;
  }

  public String getResult() {
    return result;
  }
}
