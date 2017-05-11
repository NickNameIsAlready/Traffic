package traffic.traffic1.bean;

/**
 * Created by feng on 17-5-11.
 */

public class BillBean {
    public String xuhao;

    public BillBean(String xuhao, String shijian, String caozuoren, String jine, String chehao) {
        this.xuhao = xuhao;
        this.shijian = shijian;
        this.caozuoren = caozuoren;
        this.jine = jine;
        this.chehao = chehao;
    }

    public String shijian;
    public String caozuoren;
    public String jine;
    public String chehao;
}
