package org.test.benchmarks;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.protobuf.nano.MessageNano;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.test.beans.MonsterPojo;
import org.test.proto.MonsterProto;
import org.test.proto.MonsterSlowProto;
import org.test.util.NullOutputStream;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Threads(1)
@State(Scope.Thread)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MINUTES)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MINUTES)
@Timeout(time = 3, timeUnit = TimeUnit.MINUTES)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ProtoBenchmark {

    private Schema<MonsterPojo> protoStuffSchema = RuntimeSchema.getSchema(MonsterPojo.class);

    private LinkedBuffer protoStuffBuffer = LinkedBuffer.allocate(4096);

    private ObjectMapper objectMapper = createObjectMapper();

    @Benchmark
    public void serializeFixedFastProto() throws IOException {
        // https://github.com/google/protobuf/pull/2121
        MonsterProto.Monster.newBuilder()
                .setValue001("aaaa")
                .setValue002(1000)
                .build()
                .writeTo(NullOutputStream.INSTANCE);
    }

    @Benchmark
    public void serializeUpstreamSlowProto() throws IOException {
        MonsterSlowProto.Monster.newBuilder()
                .setValue001("aaaa")
                .setValue002(1000)
                .build()
                .writeTo(NullOutputStream.INSTANCE);
    }

    @Benchmark
    public void serializeNanoProto() throws IOException {
        org.test.proto.nano.MonsterProto.Monster monster = new org.test.proto.nano.MonsterProto.Monster();
        monster.value001 = "aaaa";
        monster.value002 = 1000;

        byte[] data = MessageNano.toByteArray(monster);
        NullOutputStream.INSTANCE.write(data);
    }

    @Benchmark
    public void serializeProtoStaff() throws IOException {
        MonsterPojo entity = new MonsterPojo();
        entity.value001 = "aaaa";
        entity.value002 = 1000;

        ProtobufIOUtil.writeTo(NullOutputStream.INSTANCE, entity, protoStuffSchema, protoStuffBuffer);

        protoStuffBuffer.clear();
    }

    @Benchmark
    public void serializeJackson() throws IOException {
        MonsterPojo entity = new MonsterPojo();
        entity.value001 = "aaaa";
        entity.value002 = 1000;

        objectMapper.writeValue(NullOutputStream.INSTANCE, entity);
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);

        // Serialization options
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.disable(SerializationFeature.WRITE_ENUMS_USING_INDEX);

        // Deserialization options
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.disable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        return mapper;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ProtoBenchmark.class.getSimpleName())
                .forks(1)
                .threads(1)
                .mode(Mode.AverageTime)
                .warmupTime(TimeValue.seconds(10))
                .measurementTime(TimeValue.seconds(10))
                .timeUnit(TimeUnit.MICROSECONDS)
                .build();

        new Runner(opt).run();
    }


}
