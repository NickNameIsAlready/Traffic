package traffic.traffic1.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by feng on 17-5-11.
 */

public class AllSenseBean {
    /**
     * RESULT : S
     * ERRMSG : 成功
     * pm2.5 : 8
     * co2 : 5919
     * LightIntensity : 1711
     * humidity : 44
     * temperature : 28
     */

    private String RESULT;
    private String ERRMSG;
    @SerializedName("pm2.5")
    private int _$Pm25292; // FIXME check this code
    private int co2;
    private int LightIntensity;
    private int humidity;
    private int temperature;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public int get_$Pm25292() {
        return _$Pm25292;
    }

    public void set_$Pm25292(int _$Pm25292) {
        this._$Pm25292 = _$Pm25292;
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
