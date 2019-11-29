#ifndef __MINIRPC_INTERFACE_H__
#define __MINIRPC_INTERFACE_H__

#define MINIRPC_STATE_CONNECTED      1
#define MINIRPC_STATE_DISCONNECTED   2
#define MINIRPC_STATE_ERROR          3
/* read */
typedef int (*pFunc_cb)(void* para, const char* buf, int sz);
typedef int (*pFunc_svr_cb)(int id, void* para, const char* buf, int sz);

/* state changed */
typedef int (*pFunc_state_cb)(void* para, int events, int arg);


struct miniRPC_config {
#define MINIRPC_CONFIG_MODE_CLIENT 1
#define MINIRPC_CONFIG_MODE_SERVICE 2
	int mode;
	union {
		struct {
			char host[32];    // ip
			char service[8];  // port

			pFunc_state_cb state_cb;
			pFunc_svr_cb svr_cb;
			void *para;
		} service;

		struct {
			char host[32];    // ip
			char service[8];  // port

			pFunc_state_cb state_cb;
			pFunc_cb cli_cb;
			void *para;
		} client;

	};
};

/* 返回文件描述符fd(>= 0), 出错: -1 */
int miniRPC_init(struct miniRPC_config *cfg);
void miniRPC_release(int fd);

/* 服务端和客户端发送数据，其中客户端id为0 */
int miniRPC_send(int fd, unsigned int id, const char* buf, int sz);
#define miniRPC_cli_send(fd, buf, sz) miniRPC_send(fd, 0, buf, sz)

#endif
