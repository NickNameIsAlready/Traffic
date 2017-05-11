package traffic.traffic1.bean;

/**
 * Created by feng on 17-5-11.
 */

public class SensorHistoryBean {
    public String sensor;
    public String shijian;
    public String iszhengchang;
    public String shuju;

    public SensorHistoryBean(String sensor, String shijian, String iszhengchang, String shuju) {
        this.sensor = sensor;
        this.shijian = shijian;
        this.iszhengchang = iszhengchang;
        this.shuju = shuju;
    }
}
