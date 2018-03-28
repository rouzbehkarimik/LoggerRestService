#!/bin/bash
rm -rf /root/deleteIndex.sh
touch /root/deleteIndex.sh
chmod +x /root/deleteIndex.sh
echo "#!/bin/bash"  >> /root/deleteIndex.sh
curl http://$1:9200/_cat/indices | awk '{print $3}'| grep kpi| cut -d '-' -f2| sed 's/\./-/g'| date -f - +%s | while read line; do if [ "$line" -lt $(date -d "62 days ago"  +%s) ]; then echo "curl -X DELETE http://$1:9200/kpi-$(date -d @$line '+%Y.%m.%d')" >> /root/deleteIndex.sh; fi; done ;
bash /root/deleteIndex.sh
