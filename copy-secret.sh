kubectl get secret groovv-ha-pguser-groovv-ha -n production-database -o yaml | sed 's/namespace: .*/namespace: default/'  | kubectl apply -f -
