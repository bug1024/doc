#!/bin/bash

#reload stop
cmd=$1

if [ "$cmd" = "" ]; then
    nginx -p `pwd` -c conf/nginx.conf
else
    nginx -p `pwd` -c conf/nginx.conf -s $cmd
fi

