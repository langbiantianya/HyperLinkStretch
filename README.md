迁移仓库至[https://github.com/rerubbish/HyperLinkStretch](https://github.com/rerubbish/HyperLinkStretch)  
使用`Spring Reactive Web` 与 `Spring Data Reactive Redis` 写的一个demo

A demo developed using `Spring Reactive Web` and `Spring Data Reactive Redis`

## 如何部署？(how to deploy?)

```shell
wget https://github.com/langbiantianya/HyperLinkStretch/raw/refs/heads/master/docker-compose.yml
```
```shell
docker compose up -d
```
## 如何构建？(how to build?)
### docker
#### use make
```shell
make build-oci-image
```
#### not used make
```shell
docker build -t hyper-link-stretch .
```
### jar 
必须设置`JAVA_HOME`

`JAVA_SOME` must be set
#### linux or unix
```shell
chmod +x ./gradlew
```
```shell
./gradlew build
```
#### windows
```shell
./gradlew.bat build
```
