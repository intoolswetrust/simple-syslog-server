# Simple Syslog Server

All-in-one implementation of [RFC-5424 Syslog Protocol](http://tools.ietf.org/html/rfc5424) for testing purposes.

There are 3 implemented server configurations:

 * UDP
 * TCP
 * TLS (uses self-signed certificate) 

The syslog server listens on **port 9898** in all configurations.

## Usage

    java -jar syslog-server.jar [udp|tcp|tls]

If no argument is provided, then the **"tls"** configuration is started.

### Output

Console then contain output similar to:

```
Simple syslog server (RFC-5424)
Usage:
  java -jar syslog-server.jar [protocol]

Possible protocols: udp, tcp, tls
Starting Syslog server
Protocol: tcp
Host:     127.0.0.1
Port:     9898
Creating Syslog server socket
Handling Syslog client /127.0.0.1
>>> Syslog message came: Rfc5424SyslogEvent [prioVersion=<12>1, facility=1, level=4, version=1, timestamp=2014-010-22T12:15:48.952+02:00, host=my-nb, appName=Test, procId=11119, msgId=-, structuredData=-, message=Message one]
>>> Syslog message came: Rfc5424SyslogEvent [prioVersion=<12>1, facility=1, level=4, version=1, timestamp=2014-010-22T12:15:52.039+02:00, host=my-nb, appName=Test, procId=11119, msgId=-, structuredData=-, message=Message two]
```