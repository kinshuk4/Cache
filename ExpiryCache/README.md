Expiry Cache Problem
------------------

- You have to implement in­memory cache which support time to live (TTL) for cache entries.
- You need to make sure that this Cache can be used without hassle in multi­thread application.

```java
import java.util.concurrent.TimeUnit;
public interface ExpiryCache<K,V>
{
/** put this entry(key,value) in the Cache with provided ttl.
* If cache.get happens within this ttl, value should be returned else cache.get should return
null
* @param key
* @param value
* @param ttl ­ how long this entry should remain in Cache, in units of timUnit
* @param timeUnit ­ a TimeUnit (ref: java.util.concurrent.TimeUint) determining how to interpret the timeout
parameter
*/
public void put(K key,V value,int ttl, TimeUnit timeUnit);
/**
* get entry.value from cache for this key unless that is expired.
* @param key
* @return value associated with the key
*/
public V get(K key);
}
```
­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­­
You need to capture time taken to reach full solution (thinking + writing + testing) and share
the same while submitting code. You are not allowed to use any external library but you
are allowed to use:

1. Any standard data structure which is provided upto Java SE 7.
2. You are allowed to use editor of your choice and internet while implementing this.
You will be evaluated on following basis
  1. Code completeness
  2. Data structure choices
  3. Amortized Complexity on put and get method and expiration of keys
  4. Lock efficiency in multi­thread environment
  5. Memory Optimizations