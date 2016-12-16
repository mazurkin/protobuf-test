# protobuf serialization problem

Serialization takes too much time due to excessive copying from builder to immutable bean.

```
# org.test.benchmarks.ProtoBenchmark
# bean has 300 fields: 2 fields are set with data, 298 are not set

# Run complete. Total time: 00:10:03

Benchmark                                  Mode  Cnt   Score   Error  Units
ProtoBenchmark.serializeFixedFastProto     avgt       11.343          us/op
ProtoBenchmark.serializeJackson            avgt        6.747          us/op
ProtoBenchmark.serializeNanoProto          avgt        1.464          us/op
ProtoBenchmark.serializeProtoStaff         avgt        4.223          us/op
ProtoBenchmark.serializeUpstreamSlowProto  avgt       26.588          us/op

```

Issue: https://github.com/google/protobuf/issues/2108

Pull request: https://github.com/google/protobuf/pull/2121
