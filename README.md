## EUID

Implementation of EUID ([Draft-5](https://github.com/ardikars/EUID/tree/draft-5))

### Usage

```xml
<dependency>
    <groupId>com.ardikars.euid</groupId>
    <artifactId>euid</artifactId>
    <version>${euid.version}</version>
</dependency>
```

```java
final EUID _euid = EUID.create().orElseThrow();

final long now = System.currentTimeMillis();
final EUID euid = EUID.createWithExtension(0x7fff).orElseThrow();
final String encodedWithCheckMod = euid.encode(true);
final String encodedWithoutCheckMod = euid.encode(false);
assert !encodedWithCheckMod.equals(encodedWithoutCheckMod);
assert encodedWithCheckMod.equals(euid.toString());
assert euid.equals(EUID.fromString(encodedWithCheckMod));
assert euid.compareTo(EUID.fromString(encodedWithCheckMod)) == 0;
assert euid.equals(EUID.fromString(encodedWithoutCheckMod));
assert euid.compareTo(EUID.fromString(encodedWithoutCheckMod)) == 0;
assert euid.extension().isPresent();
assert 0x7fff == euid.extension().orElseThrow();
assert now <= euid.timestamp();
assert 16 == euid.toBytes().length;
final EUID nextEuid = euid.next().orElseThrow();
assert euid.compareTo(nextEuid) < 0;
```
