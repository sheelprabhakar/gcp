# gcp

**Build Jar**
```
gradle shadowJar
```
**Run locally using gradle**
```
gradle runFunction -Prun.functionTarget=com.c4c.gcp.cloudfunction.HelloWorld
```
**Deploy on GCP**

Before doing this you need to install GCP cli and configure account and project
```
gcloud functions deploy my-first-function --entry-point com.c4c.gcp.cloudfunction.HelloWorld --runtime java11 --trigger-http --memory 256MB --allow-unauthenticated --source=build/libs
```

Can invoke function as given below
```
https://<region>-<project>.cloudfunctions.net/my-first-function
```