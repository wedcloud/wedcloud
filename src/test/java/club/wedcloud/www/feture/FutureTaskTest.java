package club.wedcloud.www.feture;

public class FutureTaskTest {
  public FutureData  request(String requestData){
    FutureData futureData = new FutureData();
    new Thread(new Runnable() {
      @Override
      public void run() {
        RealData realData = new RealData(requestData);
        futureData.setRealData(realData);
      }
    }).start();
    return futureData;
  }
}
