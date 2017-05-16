package traffic.traffic1.bean;

/**
 * Created by asdf on 2017/5/15.
 */

public class hTrafficLightBean extends hRequestBean{
    //"RedTime":"25","GreenTime":"55","YellowTime":"5"
    public int RedTime;
    public int GreenTime;
    public int YellowTime;

    @Override
    public String toString() {
        return "HKBTrafficLightBean{" +
                "RedTime=" + RedTime +
                ", GreenTime=" + GreenTime +
                ", YellowTime=" + YellowTime +
                '}';
    }
}
