#!/bin/bash

function logger() {
    logStr=$*
    echo "`date +'%Y-%m-%d %H:%M:%S'` ${logStr}"
}

function exception() {
    errorNo=$1
    # shellcheck disable=SC2068
    errMsg=`echo ${@:2}`
    if [[ ${errMsg}> 0 ]];then
      logger "[ERROR] ${errMsg}"
      exit 1
    fi
}

## 目录 文件名 配置名 表名
mergeDataDir=$1
fileName=$2
dsn=${mergeDataDir}/dsn.txt
tableName=$3

if [ ! -d "${mergeDataDir}" ];then
  mkdir -p ${mergeDataDir}
fi

dataFile=${mergeDataDir}/${fileName}

while read shardId dbConf
do
  if [ "${shardId}" = "" ];then
    continue
  fi
  dbname=`echo "${dbConf}" | awk '{print $5}'`
  echo "dbname===${dbname}"		`
  echo "${shardId}===${dbConf}====${dbname:1-4}"
  ## table lower
  # tableNameLower=`echo "${tableName}" | tr [:upper:] [:lower:]`
  outfile=$mergeDataDir/${tableNameLower}.dat

  echo "outfile=${outfile}"

  ##merge
  cat ${fileName} >> ${outfile}

done < ${dsn}
