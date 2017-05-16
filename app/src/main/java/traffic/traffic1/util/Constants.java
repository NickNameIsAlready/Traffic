package traffic.traffic1.util;

/**
 * Created by asdf on 2017/5/15.
 */

public class Constants {
    private static final String BaseUrl = "http://192.168.0.110:8080/transportservice/action/";

    /*
    * 查询所有传感器的值
    *
    * */
    public static String GetAllSense = BaseUrl + "GetAllSense.do";


    /*
    * 设置路灯模式
    *
    * */
    public static String SetRoadLightControlMode = BaseUrl + "SetRoadLightControlMode.do";


    /*
    * 设置路灯开关
    *
    * */
    public static String SetRoadLightStatusAction = BaseUrl + "SetRoadLightStatusAction.do";


    /*
* 设置路灯开关
*
* */
    public static String GetRoadLightStatus = BaseUrl + "GetRoadLightStatus.do";

    /*
* 查询所有传感器的值GetRoadStatus
*
* */
    public static String GetBusCapacity = BaseUrl + "GetBusCapacity.do";
    /*
    *
    * 查询道路状态的值GetRoadStatus
    *
    * */
    public static String GetRoadStatus = BaseUrl + "GetRoadStatus.do";

    /*
    *
    * 查询小车的余额
    *
    * */
    public static String GetCarAccountBalance = BaseUrl + "GetCarAccountBalance.do";


    /*
    *
    * 小车充值
    *
    * */
    public static String SetCarAccountRecharge = BaseUrl + "SetCarAccountRecharge.do";

    /*
    * 设置小车动作
    * */
    public static String SetCarMove = BaseUrl + "SetCarMove.do";
}
