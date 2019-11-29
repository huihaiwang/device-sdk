#ifndef __MINILOG_H__
#define __MINILOG_H__
#include <syslog.h>
// #define LOG_EMERG   0   /* system is unusable */
// #define LOG_ALERT   1   /* action must be taken immediately */
// #define LOG_CRIT    2   /* critical conditions */
// #define LOG_ERR     3   /* error conditions */
// #define LOG_WARNING 4   /* warning conditions */
// #define LOG_NOTICE  5   /* normal but significant condition */
// #define LOG_INFO    6   /* informational */
// #define LOG_DEBUG   7   /* debug-level messages */

void miniLog_threshold(int threshold);

void miniLog(int priority, const char *fmt, ...)
	__attribute__ ((format (printf, 2, 3)));

#define miniLog_err(fmt, ...)   miniLog(LOG_ERR, fmt, ## __VA_ARGS__)
#define miniLog_warn(fmt, ...)  miniLog(LOG_WARNING, fmt, ## __VA_ARGS__)
#define miniLog_notice(fmt, ...)  miniLog(LOG_NOTICE, fmt, ## __VA_ARGS__)
#define miniLog_info(fmt, ...)  miniLog(LOG_INFO, fmt, ## __VA_ARGS__)
#define miniLog_debug(fmt, ...) miniLog(LOG_DEBUG, fmt, ## __VA_ARGS__)

#endif
