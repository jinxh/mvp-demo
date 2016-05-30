#!/bin/bash

find ./ -name .svn > ./svnlist.tmp
echo "files: `cat svnlist.tmp | wc -l`"
declare -i cnt=0
cat svnlist.tmp | while read oneline
do
rm -rf "$oneline"
((cnt++))
echo $cnt > ./cnt.tmp
done
echo "deleted: `cat ./cnt.tmp`"
rm -rf ./svnlist.tmp
rm -rf ./cnt.tmp 

###################################
###################################
# The '|' will create a subshell to deal with the
# while operation, the subshell cannot change the
# variable $cnt in the baseshell. So use a temp
# file to record the count number.
# Another way to do with this:
#
# while read oneline
# do
# rm -rf "$oneline"
# ((cnt++))
# done < svnlist.tmp
#
###################################
###################################
