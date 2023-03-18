echo Building app
call gradle clean bootJar

echo Building docker
docker build -t sheelprabhakar/cloud-function-manager:0.1 .
docker image push sheelprabhakar/cloud-function-manager:0.1

gcloud run deploy cloud-function-manager --image sheelprabhakar/cloud-function-manager:0.1