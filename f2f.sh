#!/bin/bash
cat test|grep  "public"|awk -F ' ' '{print $3}'|awk -F ';' '{print $1}'|awk -F '=' '{print $1}'> test_tmp
sed '1s/^/"/;2,$s/^/,"/;s/$/"/' test_tmp > test_out
rm test_tmp
