#!/bin/bash
echo "trash" > /tmp/.trash.txt
customerKey=12345678909876543212345678909876


aws s3 cp /tmp/.trash.txt s3://tonyfraser-encrypted/trash.txt \
    --sse-c-key $customerKey \
    --sse-c AES256

# aws s3 cp s3://tonyfraser-encrypted/trash.txt output.txt --sse-c-key $customerKey --sse-c AES256 