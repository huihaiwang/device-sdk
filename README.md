## 概述
```
该项目主要基于spring-shell进行开发，对linux版本的海大宇的sdk进行二次封装，方便java研发人员使用海大宇sdk。
工程启动后，类似shell界面。
```
## 环境搭建
### 海康
*以/opt/hc目录为例*
- HCNetSDKCom
    - libanalyzedata.so
    - libHCAlarm.so
    - libHCCoreDevCfg.so
    - libHCDisplay.so
    - libHCGeneralCfgMgr.so
    - libHCIndustry.so
    - libHCPlayBack.so
    - libHCPreview.so
    - libHCVoiceTalk.so
    - libiconv2.so
    - libStreamTransClient.so
    - libSystemTransform.so
- libAudioRender.so
- libcrypto.so
- libcrypto.so.1.0.0
- libHCCore.so
- libhcnetsdk.so
- libhpr.so
- libNPQos.so
- libPlayCtrl.so
- libssl.so
- libSuperRender.so

**在/etc/profile中配置环境变量**
```
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/opt/hc:/opt/hc/HCNetSDKCom
```

*注：在ubuntu中在profile配置LD_LIBRARY_PATH是无效的，需相关/etc/X11/Xsession.options相关参数*
```
use-ssh-agent --> no-use-ssh-agent
```

## 运行
```
./gradlew clean assemble
java -jar build/libs/hik-sdk-0.0.1-SNAPSHOT.jar
```
## 命令
- init
```
初始化sdk

SYNOPSYS
	init [--log]  

OPTIONS
	--log	
		[Optional, default = false]
```
- login
```
登录

SYNOPSYS
	login [--ip] string  [[--port] short]  [[--user] string]  [[--pass] string]  

OPTIONS
	--ip  string
		
		[Mandatory]

	--port  short
		
		[Optional, default = 8000]

	--user  string
		
		[Optional, default = admin]

	--pass  string
		
		[Optional, default = admin12345]
```
- info
```
设备信息

NAME
	info - the device info

SYNOPSYS
	info
```

- capture
```
抓取图片

SYNOPSYS
	capture [[--file-path] string]

OPTIONS
	--file-path  string

		[Optional, default = /tmp]
```

- setcapparam
```
设置抓拍联动

SYNOPSYS
	setcapparam 
```
- setupalarmchan
```
启用布控

SYNOPSYS
	setupalarmchan 
```
- closealarmchan
```
关闭布控

SYNOPSYS
	closealarmchan 
``` 
- delface
```
删除人脸

SYNOPSYS
	delface [--cardno] string  

OPTIONS
	--cardno  string
		
		[Mandatory]
```
- restart
```
重启

SYNOPSYS
	restart 
```
- logout
```
登出

SYNOPSYS
	logout 
```
- cleanup
```
释放资源

SYNOPSYS
	cleanup 
```
- startremoteconfig
```
开启长连接

SYNOPSYS
	startremoteconfig
```
- stopremoteconfig
```
关闭长连接

SYNOPSYS
	stopremoteconfig
```
- issuecarno
```
下发车牌

SYNOPSYS
	issuecarno [[--carno] string]  [[--color] string]  

OPTIONS
	--carno  string
		
		[Optional, default = 京A12345]

	--color  string
		
		[Optional, default = 1]
```
- help
```
帮助
SYNOPSYS
	help [[-C] string]  

OPTIONS
	-C or --command  string
		The command to obtain help for.
		[Optional, default = <none>]
```

- clear
```
清空屏幕

SYNOPSYS
	clear
```
- exit,quit
```
清空屏幕

SYNOPSYS
	exit 

ALSO KNOWN AS
	quit
```

## 工具集
### f2f.sh
```
将类中的字段转化为按逗号隔开的字符串
```
**使用**

- 在同级目录下，新建test文件，并将类中字段写入*
- 运行sh f2f.sh
- 在同级目录下生成结果文件test_out

**示例**

- test文件
```
public byte byUserIDValid;                 /* userid是否有效 0-无效，1-有效 */
public byte bySerialValid;                 /* 序列号是否有效 0-无效，1-有效 */
public byte byVersionValid;                /* 版本号是否有效 0-无效，1-有效 */
public byte byDeviceNameValid;             /* 设备名字是否有效 0-无效，1-有效 */
public byte byMacAddrValid;                /* MAC地址是否有效 0-无效，1-有效 */
public byte byLinkPortValid;               /* login端口是否有效 0-无效，1-有效 */
public byte byDeviceIPValid;               /* 设备IP是否有效 0-无效，1-有效 */
public byte bySocketIPValid;               /* socket ip是否有效 0-无效，1-有效 */
public int lUserID;                       /* NET_DVR_Login()返回值, 布防时有效 */
public byte[] sSerialNumber= new byte[SERIALNO_LEN];    /* 序列号 */
public int dwDeviceVersion;                /* 版本信息 高16位表示主版本，低16位表示次版本*/
public byte[] sDeviceName=new byte[NAME_LEN];            /* 设备名字 */
public byte[] byMacAddr=new byte[MACADDR_LEN];        /* MAC地址 */
public short wLinkPort;                     /* link port */
public byte[] sDeviceIP=new byte[128];                /* IP地址 */
public byte[] sSocketIP=new byte[128];                /* 报警主动上传时的socket IP地址 */
public byte byIpProtocol;                  /* Ip协议 0-IPV4, 1-IPV6 */
public byte[] byRes1=new byte[2];
public byte bJSONBroken;                   //JSON断网续传标志。0：不续传；1：续传
public short wSocketPort;
public byte[] byRes2=new byte[6];
```
- test_out文件
```
"byUserIDValid"
,"bySerialValid"
,"byVersionValid"
,"byDeviceNameValid"
,"byMacAddrValid"
,"byLinkPortValid"
,"byDeviceIPValid"
,"bySocketIPValid"
,"lUserID"
,"sSerialNumber"
,"dwDeviceVersion"
,"sDeviceName"
,"byMacAddr"
,"wLinkPort"
,"sDeviceIP"
,"sSocketIP"
,"byIpProtocol"
,"byRes1"
,"bJSONBroken"
,"wSocketPort"
,"byRes2"
```

