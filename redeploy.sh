echo "Redeploying $1"
kubectl set image deployment/groovv-io groovv-io=us-docker.pkg.dev/groovv-cloud/groovv-containers/groovv.io@sha256:$1
echo "Successfully redeployed $1"
