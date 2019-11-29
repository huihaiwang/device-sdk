#ifndef __LIB_MINIRPC_H__
#define __LIB_MINIRPC_H__
#include <stdbool.h>
#include "miniRPC_ioctl.h"

#define MINIRPC_STATECHANGED_CONNECTED      1
#define MINIRPC_STATECHANGED_DISCONNECTED   2
#define MINIRPC_STATECHANGED_ERROR          3
typedef int (*pFunc_state_cb)(void* para, int events, int arg);
typedef int (*pFunc_cb)(void* para, const char* buf, int sz);
typedef int (*pFunc_svr_cb)(int id, void* para, const char* buf, int sz);

struct miniRpc_config_sock {
	char host[32];    // ip
	char service[8];  // port
	//int type;  // unix,inet,...

	pFunc_state_cb state_cb;
	pFunc_svr_cb svr_cb;
	pFunc_cb cli_cb;
	void *para;
};

struct miniRpc_config_protSpi {
	char spi_dev[30];
	bool isSlave;

	pFunc_cb cb;
	void *para;
};

struct miniRpc_config {
#define MINIRPC_CONFIG_MODE_DIRECT      0 // for debug
#define MINIRPC_CONFIG_MODE_NET         1
#define MINIRPC_CONFIG_MODE_NET_SERVER  2
#define MINIRPC_CONFIG_MODE_PROT_SPI    3
#define _MINIRPC_CONFIG_MODE_MAX        4
	int mode;
	void *priv; // 私有结构, 例: struct miniRpc_config_sock
};

int miniRpc_open(struct miniRpc_config *cfg);
void miniRpc_close(int fd);
int miniRpc_read(int fd, void* buf, int len);
int miniRpc_write(int fd, const void* buf, int len);
int miniRpc_write_id(int fd, int id, const void* buf, int len); // sock服务器端
long miniRpc_ioctl(int fd, unsigned int cmd, unsigned long arg);

#endif

