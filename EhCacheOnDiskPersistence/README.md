PRE-REQUISTIES
==============
Maven for build

HOW TO RUN THE SAMPLE
======================
First time when you run the sample, there is no persisted data on the disk.
So we / cache will get NULL (for a given key hardcoded within the sample). 
But at the end of the sample, we will write to the cache.
``` 
$> mvn compile exec:exec
Reading from cache null
Writing to cache [ key = a, value=b, version=1, hitCount=1, CreationTime = 1439480214501, LastAccessTime = 1439480214505 ]
```

Now when we run the same program one more time, since we have cache data persisted on disk, 
we shall be able to read the value from the cache. 
```
$>mvn exec:exec
Reading from cache [ key = a, value=b, version=1, hitCount=2, CreationTime = 1439480215000, LastAccessTime = 1439480303001 ]
Writing to cache [ key = a, value=b, version=1, hitCount=1, CreationTime = 1439480303001, LastAccessTime = 1439480303006 ]
```




HOW TO RUN THE HIBERNATE SAMPLE
===============================
The demo uses a HSQLDB with local file persistence.
Save the hibernate entity using the following
```
$> mvn clean compile exec:exec -PsaveEntity -DaccountNumber=a1 -DcustomerName=c1 -Dbalance=10
```

Now load the entity using the following
```
$> mvn clean compile exec:exec -PloadEntity -DaccountNumber=a1 
```

This should now populate the 2nd level hibernate ehcache region.
Loading the same entity should not now lead to the SQL select query being fired.
Confirm 
```
$> mvn clean compile exec:exec -PloadEntity -DaccountNumber=a1
```
