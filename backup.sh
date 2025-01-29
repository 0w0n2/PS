#!/bin/bash
# 폴더 전체를 이전에 지정해둔 remote 주소(브랜치 = master)로 전체 백업하는 스크립트
# Usage : ./backoff.sh <commit message> (Ex/ .backoff.sh 250116)

# git의 줄바꿈 설정을 변경(환경설정)
git config --global core.autocrlf false

# remote 주소 표시(걍 확인용)
git remote -v

# commit message = today's date
# commit message는 오늘의 날짜로 설정해둠. 직접 설정하고 싶다면 18줄 지우고 19줄 쓰세염
message=$(date +"%y%m%d")
echo "commit message : $message"

# backoff (push all files in this directory)
git add .
# git commit -m "$1"
git commit -m "$message"
git push origin +master
