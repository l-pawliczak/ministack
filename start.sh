#!/bin/sh
docker run --name ministack-db -e MYSQL_ROOT_PASSWORD=test1 -d mysql