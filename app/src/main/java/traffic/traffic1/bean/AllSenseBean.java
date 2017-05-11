package traffic.traffic1.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by feng on 17-5-11.
 */

public class AllSenseBean {
    @SerializedName("pm2.5")
    private int _$Pm25223; // FIXME check this code
    private int co2;
    private int LightIntensity;
    private int humidity;
    private int temperature;

    public int get_$Pm25223() {
        return _$Pm25223;
    }

    @Override
    public String toString() {
        return "AllSenseBean{" +
                "_$Pm25223=" + _$Pm25223 +
                ", co2=" + co2 +
                ", LightIntensity=" + LightIntensity +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                '}';
    }

    public void set_$Pm25223(int _$Pm25223) {
        this._$Pm25223 = _$Pm25223;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(int LightIntensity) {
        this.LightIntensity = LightIntensity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
