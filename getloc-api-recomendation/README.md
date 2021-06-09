# Description

In this section there is the source code for the api that is deployed on the google compute engine. This API uses a compute engine because to read machine learning models it requires tensorflow which cannot be done using the app engine. The compute specifications used are as follows:

```
Region: asia-southeast2(Jakarta)
Zona: asia-southeast2-a
Machine type: n1-standard-1 (1 vCPU,3.73 GB memory)
```

Some of the APIs that are deployed on the compute engine are as follows:

1. [Destination Recommendation](https://github.com/AgungP88/getloc-apps/tree/cloud-computing#destination-recommendation)
