package club.wedcloud.www.feture;

public class FutureData {
  private Boolean FLAG =false;
  private RealData realData;

  //读取数据
  public synchronized void setRealData(RealData realData){
    //如果已获得结果，直接返回
    if(FLAG){
      return;
    }
    // 如果FLAG位false,没有获取到数据，传递realData 对象
    this.realData=realData;
    FLAG=true;
    notify();
  }

  //取出数据
  public synchronized String getRequest(){
    //监听是否获取到数据
    while (!FLAG) {
      try {
        wait();
      }catch (Exception e){

      }
    }
    return this.realData.getResult();
  }
}
