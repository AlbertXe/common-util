#!/bin/bash
echo $0
echo `dirname $0`
logDir=$(cd $(echo `dirname $0`);pwd)
echo $(echo `dirname $0`)
echo $(cd $(echo `dirname $0`))
echo $(cd $(echo `dirname $0`);pwd;ls)
echo $logDir

batchCnt=99

execTask(){
	dbInfo=$1
	echo $1
	echo `seq 0 ${batchCnt}`
	for i in `seq 0 ${batchCnt}`
	do
		countSql="select count(0) from table"
		count=`mysql ${dbInfo} -Ne "${countSql}"`

		execSql=""
		for j in `seq 1 ${count}`
		do
			sql="update table "
			execSql=`echo "${sql};${execSql}"`
		done
		if [ ! -z "${execSql}" ];then
			mysql ${dbInfo} -Ne "${execSql}"
			if [[ $? -gt 0 ]];then
				echo mysql ${dbInfo} -Ne "${execSql}" >> ${logDir}/error.txt
			fi
		fi
	done
	echo "shard:${dbInfo} finished,`date` ...."
}

# shellcheck disable=SC1073
echo "--->start time:`date`..."
while read dbInfo
do
	execTask "${dbInfo}" &
done <${logDir}/dsn.txt
wait
