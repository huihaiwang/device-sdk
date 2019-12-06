package net.similarsu.device.sdk.hik;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.NativeLongByReference;
import net.similarsu.device.sdk.hik.callback.MSGCallBack_V31;
import net.similarsu.device.sdk.hik.callback.RemoteConfigCallback;
import net.similarsu.device.sdk.hik.library.HikLibrary;
import net.similarsu.device.sdk.hik.structure.*;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.io.*;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Profile("hik")
@ShellComponent
public class HikCommands {
    private static final Logger LOGGER = LoggerFactory.getLogger(HikCommands.class);

    private HikLibrary hikLibrary = Native.load("hcnetsdk", HikLibrary.class);
    private boolean init = false;
    private int login = -1;
    private int alarmChan = -1;
    private int config = -1;
    private NET_DVR_DEVICEINFO_V40 netDvrDeviceinfoV40 = null;

    private MSGCallBack_V31 msgCallBack_v31 = new MSGCallBack_V31() {
        @Override
        public boolean invoke(int lCommand, NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
            LOGGER.info("lc:[{}]", lCommand);
            String sAlarmType = new String();
            String[] newRow = new String[3];
            //报警时间
            Date today = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] sIP = new String[2];
            switch (lCommand) {
                case HikLibrary.COMM_ID_INFO_ALARM: //身份证信息
                {
                    NET_DVR_ID_CARD_INFO_ALARM strIDCardInfo = new NET_DVR_ID_CARD_INFO_ALARM();
                    strIDCardInfo.write();
                    Pointer pIDCardInfo = strIDCardInfo.getPointer();
                    pIDCardInfo.write(0, pAlarmInfo.getByteArray(0, strIDCardInfo.size()), 0, strIDCardInfo.size());
                    strIDCardInfo.read();

                    sAlarmType = sAlarmType + "：门禁身份证刷卡信息"
                            + ",身份证号码：" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim()
                            + ",姓名：" + new String(strIDCardInfo.struIDCardCfg.byName).trim()
                            + ",住址:" + new String(strIDCardInfo.struIDCardCfg.byAddr).trim()
                            + ",性别：" + strIDCardInfo.struIDCardCfg.bySex
                            + ",出生日期:" + strIDCardInfo.struIDCardCfg.struBirth.wYear + "-" + strIDCardInfo.struIDCardCfg.struBirth.byMonth + "-" + strIDCardInfo.struIDCardCfg.struBirth.byDay
                            + ",民族:" + strIDCardInfo.struIDCardCfg.byNation
                            + ",签发机关:" + new String(strIDCardInfo.struIDCardCfg.byIssuingAuthority).trim()
                            + ",有效期" + strIDCardInfo.struIDCardCfg.struStartDate.wYear + "-" + strIDCardInfo.struIDCardCfg.struStartDate.byMonth + "-" + strIDCardInfo.struIDCardCfg.struStartDate.byDay + "-"
                            + "," + strIDCardInfo.struIDCardCfg.struEndDate.wYear + "-" + strIDCardInfo.struIDCardCfg.struEndDate.byMonth + "-" + strIDCardInfo.struIDCardCfg.struEndDate.byDay;

                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    newRow[2] = sIP[0];
                    LOGGER.info("IP[{}],报警信息[{}]", new String(pAlarmer.sDeviceIP).trim(), sAlarmType);
                    //身份证图片
                    if (strIDCardInfo.dwPicDataLen > 0) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = new String(pAlarmer.sDeviceIP).trim() +
                                    "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
                                    "_" + newName + "_IDInfoPic.jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strIDCardInfo.pPicData.getByteBuffer(offset, strIDCardInfo.dwPicDataLen);
                            byte[] bytes = new byte[strIDCardInfo.dwPicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //抓拍图片
                    if (strIDCardInfo.dwCapturePicDataLen > 0) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = new String(pAlarmer.sDeviceIP).trim() +
                                    "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
                                    "_" + newName + "_IDInfoCapturePic.jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strIDCardInfo.pCapturePicData.getByteBuffer(offset, strIDCardInfo.dwCapturePicDataLen);
                            byte[] bytes = new byte[strIDCardInfo.dwCapturePicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
            return true;
        }
    };

    // 获取人脸成功
    private RemoteConfigCallback remoteConfigCallback = new RemoteConfigCallback() {

        @Override
        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
            LOGGER.info("RCCB:[{}]", dwType);
            // dwType == 2代表有数据
            if (dwType == 2) {

                NET_DVR_CAPTURE_FACE_CFG net_dvr_capture_face_cfg = new NET_DVR_CAPTURE_FACE_CFG();
                net_dvr_capture_face_cfg.write();
                Pointer pointer = net_dvr_capture_face_cfg.getPointer();
                pointer.write(0,lpBuffer.getByteArray(0,net_dvr_capture_face_cfg.size()),0,net_dvr_capture_face_cfg.size());
                net_dvr_capture_face_cfg.read();
                if (net_dvr_capture_face_cfg.byCaptureProgress == 100 && net_dvr_capture_face_cfg.dwFacePicSize > 0) {
                    ByteBuffer byteBuffer = net_dvr_capture_face_cfg.pFacePicBuffer.getByteBuffer(0, net_dvr_capture_face_cfg.dwFacePicSize);
                    byte[] bytes = new byte[net_dvr_capture_face_cfg.dwFacePicSize];
                    byteBuffer.rewind();
                    byteBuffer.get(bytes);
                    String base64 = Base64.getEncoder().encodeToString(bytes);
                    LOGGER.info("解析人脸数据");
                    stopremoteconfig();
                }
            }else {
                LOGGER.info("获取人脸失败");
                stopremoteconfig();
            }
        }
    };
//
//    private RemoteConfigCallback remoteConfigCallback = new RemoteConfigCallback() {
//
//        @Override
//        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
//            LOGGER.info("RCCB:[{}]", dwType);
//        }
//    };

    @ShellMethod(key = "init", value = "init sdk")
    public void init(@ShellOption boolean log) {
        init = hikLibrary.NET_DVR_Init();
        errorMsg("INIT");
        if (log) {
            hikLibrary.NET_DVR_SetLogToFile(3, null, true);
            errorMsg("LOG");
        }
    }

    @ShellMethod(key = "cleanup", value = "clean up resource")
    public void cleanup() {
        hikLibrary.NET_DVR_Cleanup();
        init = false;
        errorMsg("CLEANUP");
    }

    @ShellMethod(key = "login", value = "login")
    public void login(@ShellOption(defaultValue = "192.168.70.122") String ip, @ShellOption(defaultValue = "8000") short port, @ShellOption(defaultValue = "admin") String user
            , @ShellOption(defaultValue = "hik12345") String pass) {
        NET_DVR_USER_LOGIN_INFO netDvrUserLoginInfo = new NET_DVR_USER_LOGIN_INFO();
        netDvrDeviceinfoV40 = new NET_DVR_DEVICEINFO_V40();
        Pointer netDvrUserLoginInfoPointer = netDvrUserLoginInfo.getPointer();
        Pointer netDvrDeviceinfoV40Pointer = netDvrDeviceinfoV40.getPointer();
        netDvrUserLoginInfo.bUseAsynLogin = 0;
        netDvrUserLoginInfo.sUserName = user.getBytes();
        netDvrUserLoginInfo.sPassword = pass.getBytes();
        netDvrUserLoginInfo.sDeviceAddress = ip.getBytes();
        netDvrUserLoginInfo.wPort = port;
        netDvrUserLoginInfo.write();
        login = hikLibrary.NET_DVR_Login_V40(netDvrUserLoginInfoPointer, netDvrDeviceinfoV40Pointer);
        LOGGER.info("LOGIN:[{}]", login);
        errorMsg("LOGIN");
        netDvrDeviceinfoV40.read();
        hikLibrary.NET_DVR_SetDVRMessageCallBack_V31(msgCallBack_v31, null);
        errorMsg("SET_CALLBACK");
    }

    @ShellMethod(key = "info", value = "the device info")
    public void info() {
        LOGGER.info("DEVICEINFO:{}", netDvrDeviceinfoV40.struDeviceV30);
    }

    @ShellMethod(key = "logout", value = "logout")
    public void logout() {
        hikLibrary.NET_DVR_Logout(login);
        login = -1;
        errorMsg("LOGOUT");
    }

    @ShellMethod(key = "restart", value = "restart")
    public void restart() {
        hikLibrary.NET_DVR_RebootDVR(login);
        errorMsg("RESTART");
    }

    @ShellMethod(key = "setupalarmchan", value = "setup alarm channel")
    public void setupalarmchan() {
        NET_DVR_SETUPALARM_PARAM netDvrSetupalarmParam = new NET_DVR_SETUPALARM_PARAM();
        netDvrSetupalarmParam.dwSize = netDvrSetupalarmParam.size();
        Pointer lpNetDvrSetupalarmParam = netDvrSetupalarmParam.getPointer();
        netDvrSetupalarmParam.byLevel = 1;
        netDvrSetupalarmParam.byAlarmInfoType = 1;
        netDvrSetupalarmParam.byDeployType = 0;
        netDvrSetupalarmParam.write();
        alarmChan = hikLibrary.NET_DVR_SetupAlarmChan_V41(login, lpNetDvrSetupalarmParam);
        errorMsg("SETUP_ALARM_CHAN");
    }

    @ShellMethod(key = "closealarmchan", value = "close alarm channel")
    public void closealarmchan() {
        hikLibrary.NET_DVR_CloseAlarmChan_V30(alarmChan);
        alarmChan = -1;
        errorMsg("CLOSE_ALARM_CHAN");
    }

    @ShellMethod(key = "status", value = "the device's status")
    public void status() {
        LOGGER.info("status:[{}]", hikLibrary.NET_DVR_RemoteControl(login, 20005, null, 0));
        errorMsg("STATUS");
    }

    @ShellMethod(key = "setcapparam", value = "set capture param")
    public void setcapparam() {
        NET_DVR_EVENT_CARD_LINKAGE_COND_1 struEventCardLinkageCond_1 = new NET_DVR_EVENT_CARD_LINKAGE_COND_1();
        struEventCardLinkageCond_1.cond_1.dwSize = struEventCardLinkageCond_1.cond_1.size();
        NET_DVR_EVENT_CARD_LINKAGE_CFG_V50_1 struEventCardLinkageCfgV50_1 = new NET_DVR_EVENT_CARD_LINKAGE_CFG_V50_1();
        struEventCardLinkageCfgV50_1.cfg_1.dwSize = struEventCardLinkageCfgV50_1.cfg_1.size();
        STATUS_LIST_1 status_list_1 = new STATUS_LIST_1();
        Pointer lpStruEventCardLinkageCond = struEventCardLinkageCond_1.getPointer();
        Pointer lpStruEventCardLinkageCfgV50 = struEventCardLinkageCfgV50_1.getPointer();
        Pointer lpStatusList = status_list_1.getPointer();

        struEventCardLinkageCond_1.cond_1.dwEventID = 1;
        struEventCardLinkageCond_1.cond_1.wLocalControllerID = 0;
        struEventCardLinkageCfgV50_1.cfg_1.byProMode = 0;//联动方式:0-事件,1-卡号
        struEventCardLinkageCfgV50_1.cfg_1.byCapturePic = 1;//是否联动抓拍:0-不联动抓拍,1-联动抓拍
//事件源 ID,0xffffffff 表示联动全部,其他取值:当主类型为设备事件时无效;当主类型是为门事件时,为门编号;
//当主类型为读卡器事件时,为读卡器 ID;当主类型为报警输入事件时,为防区报警输入 ID 或事件报警输入 ID
        struEventCardLinkageCfgV50_1.cfg_1.dwEventSourceID = 1;
        NET_DVR_EVENT_LINKAGE_INFO netDvrEventLinkageInfo = new NET_DVR_EVENT_LINKAGE_INFO();
        netDvrEventLinkageInfo.wMainEventType = 3;
        netDvrEventLinkageInfo.wSubEventType = 25;
        netDvrEventLinkageInfo.write();
        struEventCardLinkageCfgV50_1.cfg_1.uLinkageInfo.setTypedValue(netDvrEventLinkageInfo);
        struEventCardLinkageCfgV50_1.cfg_1.uLinkageInfo.write();
        struEventCardLinkageCond_1.write();
        struEventCardLinkageCfgV50_1.write();
        status_list_1.write();
        hikLibrary.NET_DVR_SetDeviceConfig(login, HikLibrary.NET_DVR_SET_EVENT_CARD_LINKAGE_CFG_V50, 1, lpStruEventCardLinkageCond, struEventCardLinkageCond_1.size(), lpStatusList, lpStruEventCardLinkageCfgV50, struEventCardLinkageCfgV50_1.size());
        errorMsg("SETCAPPARAM");
    }

    @ShellMethod(key = "delface", value = "del face")
    public void delface(String cardno) {
        NET_DVR_FACE_PARAM_CTRL netDvrFaceParamCtrl = new NET_DVR_FACE_PARAM_CTRL();
        netDvrFaceParamCtrl.dwSize = netDvrFaceParamCtrl.size();
        Pointer lpNetDvrFaceParamCtrl = netDvrFaceParamCtrl.getPointer();
        netDvrFaceParamCtrl.byMode = 0;
//        NET_DVR_FACE_PARAM_BYREADER netDvrFaceParamByreader=new NET_DVR_FACE_PARAM_BYREADER();
//        netDvrFaceParamByreader.dwCardReaderNo = 0;
//        netDvrFaceParamByreader.byClearAllCard = 1;
//        netDvrFaceParamByreader.byCardNo = cardno.getBytes();
//        netDvrFaceParamByreader.write();
//        netDvrFaceParamCtrl.struProcessMode.setTypedValue(netDvrFaceParamByreader);
        NET_DVR_FACE_PARAM_BYCARD netDvrFaceParamBycard = new NET_DVR_FACE_PARAM_BYCARD();
        System.arraycopy(cardno.getBytes(), 0, netDvrFaceParamBycard.byCardNo, 0, cardno.getBytes().length);
        //netDvrFaceParamBycard.byCardNo=cardno.getBytes();
        netDvrFaceParamBycard.byFaceID[0] = 1;
        netDvrFaceParamBycard.byEnableCardReader[0] = 1;
        netDvrFaceParamBycard.write();
        netDvrFaceParamCtrl.struProcessMode.setTypedValue(netDvrFaceParamBycard);
        netDvrFaceParamCtrl.struProcessMode.write();
        netDvrFaceParamCtrl.write();
        hikLibrary.NET_DVR_RemoteControl(login, HikLibrary.NET_DVR_DEL_FACE_PARAM_CFG, lpNetDvrFaceParamCtrl, netDvrFaceParamCtrl.size());
        errorMsg("DELFACE");
    }

    @ShellMethod(key = "startremoteconfig", value = "start remote config")
    public void startremoteconfig() {
        config = hikLibrary.NET_DVR_StartRemoteConfig(login, HikLibrary.NET_DVR_VEHICLE_CONTROL_LIST_START, null, 0, remoteConfigCallback, null);
        errorMsg("STARTREMOTECONFIG");
    }


    @ShellMethod(key = "stopremoteconfig", value = "stop remote config")
    public void stopremoteconfig() {
        hikLibrary.NET_DVR_StopRemoteConfig(config);
        config = -1;
        errorMsg("STOPREMOTECONFIG");
    }


    @ShellMethod(key = "issuecarno", value = "issue car no")
    public void issuecarno(@ShellOption(defaultValue = "京A12345") String carno, @ShellOption(defaultValue = "1") String color) {

        NET_DVR_VEHICLE_CONTROL_LIST_INFO netDvrVehicleControlListInfo = new NET_DVR_VEHICLE_CONTROL_LIST_INFO();
        netDvrVehicleControlListInfo.dwSize = netDvrVehicleControlListInfo.size();
        Pointer lpNetDvrVehicleControlListInfo = netDvrVehicleControlListInfo.getPointer();
        try {
            netDvrVehicleControlListInfo.sLicense = carno.getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        netDvrVehicleControlListInfo.byPlateColor = Byte.valueOf(color);
        NET_DVR_TIME_V30 struStartTime = new NET_DVR_TIME_V30();
        struStartTime.wYear = 2017;
        struStartTime.byMonth = 12;
        struStartTime.byDay = 31;
        struStartTime.byHour = 15;
        struStartTime.byMinute = 20;
        struStartTime.bySecond = 50;
        struStartTime.byISO8601 = 0;
        struStartTime.write();
        netDvrVehicleControlListInfo.struStartTime = struStartTime;
        NET_DVR_TIME_V30 struStopTime = new NET_DVR_TIME_V30();
        struStopTime.wYear = 2019;
        struStopTime.byMonth = 12;
        struStopTime.byDay = 31;
        struStopTime.byHour = 15;
        struStopTime.byMinute = 20;
        struStopTime.bySecond = 50;
        struStopTime.byISO8601 = 0;
        struStopTime.write();
        netDvrVehicleControlListInfo.struStopTime = struStopTime;
        netDvrVehicleControlListInfo.write();
        hikLibrary.NET_DVR_SendRemoteConfig(config, HikLibrary.DVR_VEHICLE_CONTROL_LIST, lpNetDvrVehicleControlListInfo, netDvrVehicleControlListInfo.size());
        errorMsg("SENDREMOTECONFIG");
    }

    @ShellMethod(key = "capture", value = "capture jpeg")
    public void capture(@ShellOption(defaultValue = "/tmp") String filePath) {
        NET_DVR_JPEGPARA netDvrJpegpara = new NET_DVR_JPEGPARA();
        Pointer lpNetDvrJpegpara = netDvrJpegpara.getPointer();
        netDvrJpegpara.wPicSize = 2;
        netDvrJpegpara.wPicQuality = 0;
        netDvrJpegpara.write();
        if(netDvrDeviceinfoV40.struDeviceV30.byIPChanNum!=0){
            for(int i=netDvrDeviceinfoV40.struDeviceV30.byStartDChan;i<=netDvrDeviceinfoV40.struDeviceV30.byStartDChan-1+netDvrDeviceinfoV40.struDeviceV30.byIPChanNum;i++){
                hikLibrary.NET_DVR_CaptureJPEGPicture(login, i, lpNetDvrJpegpara, new File(filePath,System.currentTimeMillis()+"_"+i+".jpg").getAbsolutePath());
                errorMsg("CAPTURE");
            }
        }

    }


    @ShellMethodAvailability({"cleanup", "login"})
    public Availability checkInit() {
        return init ? Availability.available() : Availability.unavailable("exec init first!!!");
    }

    @ShellMethodAvailability({"logout", "restart", "setupalarmchan", "setcapparam", "delface", "info", "capture"})
    public Availability checkLogin() {
        return login != -1 ? Availability.available() : Availability.unavailable("exec login first!!!");
    }

    @ShellMethodAvailability({"closealarmchan"})
    public Availability checkAlarmChan() {
        return alarmChan != -1 ? Availability.available() : Availability.unavailable("exec setupalarmchan first!!!");
    }

    @ShellMethodAvailability({"stopremoteconfig", "issuecarno"})
    public Availability checkRemoteConfig() {
        return config != -1 ? Availability.available() : Availability.unavailable("exec startremoteconfig first!!!");
    }

    private void errorMsg(String opt) {
        int errorCode = hikLibrary.NET_DVR_GetLastError();
        String msg = hikLibrary.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(errorCode)));
        LOGGER.error("OPT:[{}],CODE:[{}],MSG:[{}]", opt, errorCode, msg);
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        HttpClient httpClient = new HttpClient();
//        httpClient.getState().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials( "admin", "hik123456"));
        httpClient.getState().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials( "admin", "admin123"));
//        GetMethod getMethod = new GetMethod("http://192.168.70.121/ISAPI/Security/userCheck");
        GetMethod getMethod = new GetMethod("http://156.0.0.151/ISAPI/Security/userCheck");


        getMethod.setDoAuthentication(true);
        int state = 0;
        try {
            state = httpClient.executeMethod(getMethod);
        } catch (IOException e) {
            System.out.println("网络不通");
            e.printStackTrace();
            return ;
        }
        getMethod.releaseConnection();
        PutMethod putMethod = new PutMethod("http://192.168.70.122/ISAPI/ITC/Entrance/entranceParam");
        StringBuffer sb = new StringBuffer("");
        sb.append("<EntranceParamList version=\"1.0\" xmlns=\"http://www.std-cgi.com/ver10/XMLSchema\">");
        sb.append("<EntranceParam>");
        sb.append("<laneNum>1</laneNum>");
        sb.append("<bEnable>true</bEnable>");
        // 1是平台模式 0是相机模式
        int type = 0;
        sb.append("<ctrlMode>" + type + "</ctrlMode>");
        sb.append("<relateTriggerMode>2</relateTriggerMode>");
        sb.append("<vehControlMeasure>");
        sb.append("<plateNumColorEnable>false</plateNumColorEnable>");
        sb.append("<plateNumOnlyEnable>true</plateNumOnlyEnable>");
        sb.append("</vehControlMeasure>");
        sb.append("<vehInfoManagList>");
        sb.append("<vehInfoManag>");
        sb.append("<vehInfoManagNum>0</vehInfoManagNum>");
        sb.append("<barrierGateOper>0</barrierGateOper>");
        sb.append("<relayOutAlarmEnable>false</relayOutAlarmEnable>");
        sb.append("<upAlarmEnable>true</upAlarmEnable>");
        sb.append("<hostUpAlarmEnable>false</hostUpAlarmEnable>");
        sb.append("</vehInfoManag>");
        sb.append("<vehInfoManag>");
        sb.append("<vehInfoManagNum>1</vehInfoManagNum>");
        sb.append("<barrierGateOper>0</barrierGateOper>");
        sb.append("<relayOutAlarmEnable>false</relayOutAlarmEnable>");
        sb.append("<upAlarmEnable>true</upAlarmEnable>");
        sb.append("<hostUpAlarmEnable>false</hostUpAlarmEnable>");
        sb.append("</vehInfoManag>");
        sb.append("<vehInfoManag>");
        sb.append("<vehInfoManagNum>2</vehInfoManagNum>");
        sb.append("<barrierGateOper>1</barrierGateOper>");
        sb.append("<relayOutAlarmEnable>false</relayOutAlarmEnable>");
        sb.append("<upAlarmEnable>true</upAlarmEnable>");
        sb.append("<hostUpAlarmEnable>false</hostUpAlarmEnable>");
        sb.append("</vehInfoManag>");
        sb.append("</vehInfoManagList>");
        sb.append("<relayList>");
        sb.append("<relay>");
        sb.append("<relayNum>1</relayNum>");
        sb.append("<relayFunction>1</relayFunction>");
        sb.append("</relay>");
        sb.append("<relay>");
        sb.append("<relayNum>2</relayNum>");
        sb.append("<relayFunction>2</relayFunction>");
        sb.append("</relay>");
        sb.append("</relayList>");
        sb.append("<IOAlarmList>");
        sb.append("<IOAlarm>");
        sb.append("<IOAlarmNum>1</IOAlarmNum>");
        sb.append("<IOAlarmType>0</IOAlarmType>");
        sb.append("</IOAlarm>");
        sb.append("</IOAlarmList>");
        sb.append("<notCloseCarFollow>true</notCloseCarFollow>");
        sb.append("<bigCarKeepOpen>");
        sb.append("<enabled>false</enabled>");
        sb.append("<duration>0</duration>");
        sb.append("</bigCarKeepOpen>");
        sb.append("</EntranceParam>");
        sb.append("</EntranceParamList>");
        putMethod.setRequestEntity(new StringRequestEntity(sb.toString(),"text/xml", "utf-8"));
        if (state == 200) {
            try {
                System.out.println("切换平台模式" + (httpClient.executeMethod(putMethod) == 200));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        putMethod.releaseConnection();
    }

    @ShellMethod(key = "getFace1",value = "getFace1")
    public boolean getFace1(){
        NET_DVR_CAPTURE_FACE_COND net_dvr_capture_face_cond = new NET_DVR_CAPTURE_FACE_COND();
        net_dvr_capture_face_cond.write();
        Pointer pointer = net_dvr_capture_face_cond.getPointer();
        config = hikLibrary.NET_DVR_StartRemoteConfig(login, HikLibrary.NET_DVR_CAPTURE_FACE_INFO, pointer, net_dvr_capture_face_cond.dwSize, remoteConfigCallback, null);
        if (config < 0) {
            errorMsg("采集人脸失败");
        }
        return config >= 0;
    }

    @ShellMethod(key = "getFace2",value = "getFace2")
    public boolean getFace2(){
        String json = "POST /ISAPI/AccessControl/OfflineCapture/DataCollections/searchTask?format=json";
        Memory memory = new Memory(json.length() + 1);
        memory.setString(0,json);
        Pointer pointer = memory.getPointer(0);
        config = hikLibrary.NET_DVR_StartRemoteConfig(login, HikLibrary.NET_DVR_CAPTURE_DATA_SEARCH, pointer,
                json.length() + 1, remoteConfigCallback, null);
        NET_DVR_JSON_DATA_CFG net_dvr_json_data_cfg = new NET_DVR_JSON_DATA_CFG();
        net_dvr_json_data_cfg.write();
        Pointer pointer1 = net_dvr_json_data_cfg.getPointer();
        if (config < 0) {
            errorMsg("采集人脸失败");
        } else {
            boolean result = hikLibrary.NET_DVR_SendRemoteConfig(config, 11, pointer1, net_dvr_json_data_cfg.dwSize);
        }


        return config >= 0;
    }



    @ShellMethod(key = "setTime", value = "setTime")
    boolean setDVRTime(){
        NET_DVR_TIME net_dvr_time = new NET_DVR_TIME();
        net_dvr_time.dwYear = 2019;
        net_dvr_time.dwMonth = 11;
        net_dvr_time.dwDay = 5;
        net_dvr_time.dwHour = 16;
        net_dvr_time.dwMinute = 23;
        net_dvr_time.dwSecond = 30;
        boolean result = hikLibrary.NET_DVR_SetDVRConfig(login, 119, 1, net_dvr_time.getPointer(), net_dvr_time.size());
        if (!result) {
            errorMsg("set dvr time failed");
        }
        return result;
    }

}
