# ~/.bashrc에 환경변수 추가
```bash
echo 'export SERVER_PORT=8080'         >> ~/.bashrc
echo 'export DB_HOST=192.168.56.1'     >> ~/.bashrc
echo 'export DB_PORT=3306'            >> ~/.bashrc
echo 'export DB_USER=springapp'       >> ~/.bashrc
echo 'export DB_PASS=1234'            >> ~/.bashrc
```

# 변경사항 즉시 반영 및 한 줄로 확인
```bash
source ~/.bashrc && \
echo "SERVER_PORT=$SERVER_PORT  DB_HOST=$DB_HOST  DB_PORT=$DB_PORT  DB_USER=$DB_USER  DB_PASS=$DB_PASS"
```
