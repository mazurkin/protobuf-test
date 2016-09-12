# protobuf serialization problem

Serialization takes too much time due to excessive copying from builder to immutable bean.

```org.test.ProtoTest
Creating: 26445.886382 ns/op
Writing: 582.140266 ns/op
```

