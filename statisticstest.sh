
#!/bin/bash

i="0"
nrsimul="50"
betval="2"
ntry="1000000"
inicialmoney="1000000"

rm tempfile.txt
while [ $i -lt $nrsimul ]
do
java -jar mygame.jar -s $inicialmoney $betval $ntry | grep "(*)" >> tempfile.txt
i=$[$i+1]
done
rm testresult.txt
awk -F "[()]" '{ for (i=2; i<NF; i+=2) print $i }' tempfile.txt > testresult.txt
rm tempfile.txt
count=0; total=0; for i in $( awk '{ print $1; }' testresult.txt );\
do total=$(echo $total+$i | bc ); \
((count++)); done; echo "scale=2; $total / $count" | bc
