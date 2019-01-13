## nginx日志文件正则表达式

###日志格式

```n
'$http_host $server_addr $remote_addr "$http_x_forwarded_for" [$time_local] "$request" $status $body_bytes_sent "$http_referer" "$http_user_agent" $request_time $upstream_response_time';
```

### 日志文件

```nginx
www.linuxhub.cn 192.168.60.74 192.168.60.59 "113.105.222.200, 192.168.62.184" [14/Mar/2017:10:27:00 +0800] "GET /hello.php HTTP/1.0" 200 2146 "http://www.ddd.cn/test9.php" "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0" 0.001 0.000
```

### 正则字段介绍

```js
([^\s]*)              #匹配 $http_host
(\d+\.\d+\.\d+\.\d+)  #匹配 $server_addr,$remote_addr
(\"\d+\.\d+\.\d+\.\d+\,\s\d+\.\d+\.\d+\.\d+\"|\"\d+\.\d+\.\d+\.\d+\") #匹配 "$http_x_forwarded_for"
(\[[^\[\]]+\])     #匹配[$time_local]
(\"(?:[^"]|\")+|-\")   #匹配"$request","$http_referer"，"$http_user_agent"
(\d{3})            #匹配$status 
(\d+|-)            #匹配$body_bytes_sent
(\d*\.\d*|\-)      #匹配$request_time,$upstream_response_time'
^                  #匹配每行数据的开头
$                  #匹配每行数据的结局
```

### 完整正则表达式

```pro
^([^\s]*)\s(\d+\.\d+\.\d+\.\d+)\s(\d+\.\d+\.\d+\.\d+)\s(\"\d+\.\d+\.\d+\.\d+\,\s\d+\.\d+\.\d+\.\d+\"|\"\d+\.\d+\.\d+\.\d+\")\s(\[[^\[\]]+\])\s(\"(?:[^"]|\")+|-\")\s(\d{3})\s(\d+|-)\s(\"(?:[^"]|\")+|-\")\s(\"(?:[^"]|\")+|-\")\s(\d*\.\d*|\-)\s(\d*\.\d*|\-)$
```

### 日志匹配效果

<http://www.rubular.com/r/WxbGSkXWRi>

