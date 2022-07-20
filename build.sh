gradle clean build -Pvaadin.productionMode=true
docker build -f deployments/docker/groovv.docker . -t us-docker.pkg.dev/groovv-cloud/groovv-containers/groovv.io:1.0

