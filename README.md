# Simple Syslog Server

All-in-one implementation of [RFC-5424 Syslog Protocol](http://tools.ietf.org/html/rfc5424) for testing purposes.

The server is based on [syslog4j](http://www.syslog4j.org/) library.

There are 3 implemented server configurations:

 * UDP
 * TCP
 * TLS (uses self-signed certificate) 

The syslog server listens on **port 9898** in all configurations.

## Usage

    java -jar simple-syslog-server.jar [udp|tcp|tls]

If no argument is provided, then the **"tls"** configuration is started.

### Output

Console then contain output similar to:

```
Simple syslog server (RFC-5424)
Usage:
  java -jar syslog-server.jar [protocol]

Possible protocols: udp, tcp, tls

No protocol provided. Defaulting to tls
Starting Simple Syslog Server
Protocol:     tls
Bind address: 0.0.0.0
Port:         9898
Creating Syslog server socket
Handling Syslog client /10.40.4.198
>>> Syslog message came: Rfc5424SyslogEvent [prioVersion=<12>1, facility=1, level=4, version=1, timestamp=2014-010-22T12:15:48.952+02:00, host=my-nb, appName=Test, procId=11119, msgId=-, structuredData=-, message=Message one]
>>> Syslog message came: Rfc5424SyslogEvent [prioVersion=<12>1, facility=1, level=4, version=1, timestamp=2014-010-22T12:15:52.039+02:00, host=my-nb, appName=Test, procId=11119, msgId=-, structuredData=-, message=Message two]
```

## License

* [GNU Lesser General Public License Version 2.1](http://www.gnu.org/licenses/lgpl-2.1-standalone.html)
