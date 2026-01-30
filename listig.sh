#!/bin/sh

OUTPUT="project_dump_backend.txt"

echo "==============================" > $OUTPUT
echo "PROJECT COMMAND DUMP" >> $OUTPUT
echo "==============================" >> $OUTPUT
echo "" >> $OUTPUT

echo "$ ls -a" >> $OUTPUT
ls -a >> $OUTPUT
echo "" >> $OUTPUT

echo "$ cat .env docker-compose.yml" >> $OUTPUT
cat .env docker-compose.yml >> $OUTPUT
echo "" >> $OUTPUT

echo "$ cd backend" >> $OUTPUT
cd backend || exit 1



echo "==============================" >> ../$OUTPUT
echo "END OF DUMP" >> ../$OUTPUT