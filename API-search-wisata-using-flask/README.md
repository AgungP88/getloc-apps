# Description

In this section there is the source code for the api that is deployed on the google app engine. This API uses the app engine because it reduces the cost of using billing instead of using the Compute Engine. The app engine specifications used are as follows.

```
env: standard
instance_class: F1
automatic_scaling:
  min_idle_instances: automatic
  max_idle_instances: automatic
  min_pending_latency: automatic
  max_pending_latency: automatic
```

Some of the APIs that are deployed on the app engine are as follows:

1. [List Destination](https://github.com/AgungP88/getloc-apps/tree/cloud-computing#list-destination)
2. [List Package](https://github.com/AgungP88/getloc-apps/tree/cloud-computing#list-package)
3. [Convert ID to Place ID](https://github.com/AgungP88/getloc-apps/tree/cloud-computing#convert-id-to-place-id)
