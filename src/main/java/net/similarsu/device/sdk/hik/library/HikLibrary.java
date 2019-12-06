package net.similarsu.device.sdk.hik.library;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.NativeLongByReference;
import net.similarsu.device.sdk.hik.callback.MSGCallBack_V31;
import net.similarsu.device.sdk.hik.callback.RemoteConfigCallback;
import net.similarsu.device.sdk.hik.structure.NET_DVR_SNAPCFG;

public interface HikLibrary extends Library {

    int NET_DVR_DEV_ADDRESS_MAX_LEN = 129;
    int NET_DVR_LOGIN_USERNAME_MAX_LEN = 64;
    int NET_DVR_LOGIN_PASSWD_MAX_LEN = 64;
    int SERIALNO_LEN = 48;
    int MAX_INTERVAL_NUM = 4;
    int MAX_ALARMHOST_ALARMOUT_NUM = 512;
    int MAX_DOOR_NUM_256 = 256;
    int MAX_CARD_READER_NUM_512 = 512;
    int MAX_ALARMHOST_ALARMIN_NUM =512;
    int ACS_CARD_NO_LEN = 32;
    int MACADDR_LEN = 6;
    int NET_SDK_EMPLOYEE_NO_LEN = 32;
    int NET_DVR_SET_EVENT_CARD_LINKAGE_CFG_V50 = 2182;
    int NET_DVR_DEL_FACE_PARAM_CFG = 2509;
    int MAX_FACE_NUM = 2;
    int COMM_ID_INFO_ALARM = 0x5200;
    int MAX_NAMELEN =16;
    int MAX_ID_NAME_LEN = 128;
    int MAX_ID_ADDR_LEN = 280;
    int MAX_ID_NUM_LEN = 32;
    int MAX_ID_ISSUING_AUTHORITY_LEN = 128;
    int NAME_LEN =32 ;
    int NET_DVR_VEHICLE_CONTROL_LIST_START = 3123;
    int NET_DVR_CAPTURE_FACE_INFO = 2510;
    int NET_DVR_CAPTURE_DATA_SEARCH = 2554;
    int DVR_VEHICLE_CONTROL_LIST = 0x1;
    int MAX_LICENSE_LEN= 16;
    int MAX_CARDNO_LEN= 48;
    int MAX_OPERATE_INDEX_LEN= 32;

    /**
     * init
     * @return
     */
    boolean NET_DVR_Init();

    /**
     * cleanup
     * @return
     */
    boolean NET_DVR_Cleanup();

    /**
     * enable log
     * @param nLogLevel 0
     * @param strLogDir
     * @param bAutoDel true
     * @return
     */
    boolean NET_DVR_SetLogToFile(int nLogLevel, String strLogDir , boolean bAutoDel);

    /**
     * get last error
     * @return
     */
    int NET_DVR_GetLastError();

    /**
     * get error msg
     * @param pErrorNo
     * @return
     */
    String NET_DVR_GetErrorMsg(NativeLongByReference pErrorNo);


    /**
     * login
     * @param pLoginInfo
     * @param lpDeviceInfo
     * @return
     */
    int  NET_DVR_Login_V40(Pointer pLoginInfo, Pointer lpDeviceInfo);

    /**
     * logout
     * @param lUserID
     * @return
     */
    boolean NET_DVR_Logout(int lUserID);

    /**
     * get device ability
     * @param lUserID
     * @param dwAbilityType
     * @param pInBuf
     * @param dwInLength
     * @param pOutBuf
     * @param dwOutLength
     * @return
     */
    boolean NET_DVR_GetDeviceAbility(int lUserID, int dwAbilityType, String pInBuf, int dwInLength, String pOutBuf, int dwOutLength);

    /**
     * restart device
     * @param lUserID
     * @return
     */
    boolean NET_DVR_RebootDVR(int lUserID);

    /**
     * set msg call back
     * @param fMessageCallBack
     * @param pointer
     * @return
     */
    boolean NET_DVR_SetDVRMessageCallBack_V31(MSGCallBack_V31 fMessageCallBack, Pointer pointer);

    /**
     * set up alarm chan
     * @param lUserID
     * @param lpSetupParam
     * @return
     */
    int NET_DVR_SetupAlarmChan_V41(int lUserID, Pointer lpSetupParam);

    /**
     * close alarm chan
     * @param lAlarmHandle
     * @return
     */
    boolean NET_DVR_CloseAlarmChan_V30(int lAlarmHandle);

    /**
     * set device config
     * @param lUserID
     * @param dwCommand
     * @param dwCount
     * @param lpInBuffer
     * @param dwInBufferSize
     * @param lpStatusList
     * @param lpInParamBuffer
     * @param dwInParamBufferSize
     * @return
     */
    boolean NET_DVR_SetDeviceConfig(int lUserID, int dwCommand, int dwCount, Pointer lpInBuffer, int dwInBufferSize, Pointer lpStatusList, Pointer lpInParamBuffer, int dwInParamBufferSize);

    boolean NET_DVR_ContinuousShoot(int lUserID, NET_DVR_SNAPCFG lpInter);

    /**
     * remote control
     * @param lUserID
     * @param dwCommand
     * @param lpInBuffer
     * @param dwInBufferSize
     * @return
     */
    boolean NET_DVR_RemoteControl(int lUserID, int dwCommand, Pointer lpInBuffer, int dwInBufferSize);

    /**
     * start remote config
     * @param lUserID
     * @param dwCommand
     * @param lpInBuffer
     * @param dwInBufferLen
     * @param cbStateCallback
     * @param pUserData
     * @return
     */
    int NET_DVR_StartRemoteConfig(int lUserID, int dwCommand, Pointer lpInBuffer, int dwInBufferLen, RemoteConfigCallback cbStateCallback, Pointer pUserData);

    /**
     * send remote config
     * @param lHandle
     * @param dwDataType
     * @param pSendBuf
     * @param dwBufSize
     * @return
     */
    boolean NET_DVR_SendRemoteConfig(int lHandle, int dwDataType, Pointer pSendBuf, int dwBufSize);

    /**
     * stop remote config
     * @param lHandle
     * @return
     */
    boolean NET_DVR_StopRemoteConfig(int lHandle);

    /**
     * capture jpeg
     * @param lUserID
     * @param lChannel
     * @param lpJpegPara
     * @param sPicFileName
     * @return
     */
    int NET_DVR_CaptureJPEGPicture(int lUserID, int lChannel, Pointer lpJpegPara, String sPicFileName);


    /**
     * NET_DVR_SET_TIMECFG            119        //设置DVR时间
     * @param lUserID
     * @param dwCommand
     * @param lChannel
     * @param lpInBuffer
     * @param dwInBufferSize
     * @return
     */
    boolean NET_DVR_SetDVRConfig(int lUserID, int dwCommand,int lChannel, Pointer lpInBuffer, int dwInBufferSize);

}
