import boto3
s3 = boto3.client("s3")

params = {
    "Bucket": "tonyfraser-aws",
    "Key": "z2.jpg"
}

url = s3.generate_presigned_url("get_object", 
    Params=params, 
    #in seconds
    ExpiresIn=3600,
    HttpMethod="Get")

print(url)

