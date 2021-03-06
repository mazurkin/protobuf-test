// Generated by the protocol buffer compiler.  DO NOT EDIT!

package org.test.proto.nano;

@SuppressWarnings("hiding")
public interface CycleProto {

  public static final class InnerBean extends
      com.google.protobuf.nano.MessageNano {

    private static volatile InnerBean[] _emptyArray;
    public static InnerBean[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new InnerBean[0];
          }
        }
      }
      return _emptyArray;
    }

    // optional int64 value1 = 1;
    public long value1;

    // optional int64 value2 = 2;
    public long value2;

    // optional int64 value3 = 3 [default = 1000];
    public long value3;

    // optional int64 value4 = 4 [default = 1200];
    public long value4;

    // optional string value5 = 5;
    public java.lang.String value5;

    // optional bytes value6 = 6;
    public byte[] value6;

    // optional .InnerBean reference = 7;
    public org.test.proto.nano.CycleProto.InnerBean reference;

    public InnerBean() {
      clear();
    }

    public InnerBean clear() {
      value1 = 0L;
      value2 = 0L;
      value3 = 1000L;
      value4 = 1200L;
      value5 = "";
      value6 = com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES;
      reference = null;
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (this.value1 != 0L) {
        output.writeInt64(1, this.value1);
      }
      if (this.value2 != 0L) {
        output.writeInt64(2, this.value2);
      }
      if (this.value3 != 1000L) {
        output.writeInt64(3, this.value3);
      }
      if (this.value4 != 1200L) {
        output.writeInt64(4, this.value4);
      }
      if (!this.value5.equals("")) {
        output.writeString(5, this.value5);
      }
      if (!java.util.Arrays.equals(this.value6, com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES)) {
        output.writeBytes(6, this.value6);
      }
      if (this.reference != null) {
        output.writeMessage(7, this.reference);
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (this.value1 != 0L) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt64Size(1, this.value1);
      }
      if (this.value2 != 0L) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt64Size(2, this.value2);
      }
      if (this.value3 != 1000L) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt64Size(3, this.value3);
      }
      if (this.value4 != 1200L) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt64Size(4, this.value4);
      }
      if (!this.value5.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(5, this.value5);
      }
      if (!java.util.Arrays.equals(this.value6, com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES)) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeBytesSize(6, this.value6);
      }
      if (this.reference != null) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeMessageSize(7, this.reference);
      }
      return size;
    }

    @Override
    public InnerBean mergeFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      while (true) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            return this;
          default: {
            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
              return this;
            }
            break;
          }
          case 8: {
            this.value1 = input.readInt64();
            break;
          }
          case 16: {
            this.value2 = input.readInt64();
            break;
          }
          case 24: {
            this.value3 = input.readInt64();
            break;
          }
          case 32: {
            this.value4 = input.readInt64();
            break;
          }
          case 42: {
            this.value5 = input.readString();
            break;
          }
          case 50: {
            this.value6 = input.readBytes();
            break;
          }
          case 58: {
            if (this.reference == null) {
              this.reference = new org.test.proto.nano.CycleProto.InnerBean();
            }
            input.readMessage(this.reference);
            break;
          }
        }
      }
    }

    public static InnerBean parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new InnerBean(), data);
    }

    public static InnerBean parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new InnerBean().mergeFrom(input);
    }
  }

  public static final class OuterBean extends
      com.google.protobuf.nano.MessageNano {

    private static volatile OuterBean[] _emptyArray;
    public static OuterBean[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new OuterBean[0];
          }
        }
      }
      return _emptyArray;
    }

    // optional .InnerBean bean1 = 1;
    public org.test.proto.nano.CycleProto.InnerBean bean1;

    // optional .InnerBean bean2 = 2;
    public org.test.proto.nano.CycleProto.InnerBean bean2;

    // repeated .InnerBean beans = 3;
    public org.test.proto.nano.CycleProto.InnerBean[] beans;

    public OuterBean() {
      clear();
    }

    public OuterBean clear() {
      bean1 = null;
      bean2 = null;
      beans = org.test.proto.nano.CycleProto.InnerBean.emptyArray();
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (this.bean1 != null) {
        output.writeMessage(1, this.bean1);
      }
      if (this.bean2 != null) {
        output.writeMessage(2, this.bean2);
      }
      if (this.beans != null && this.beans.length > 0) {
        for (int i = 0; i < this.beans.length; i++) {
          org.test.proto.nano.CycleProto.InnerBean element = this.beans[i];
          if (element != null) {
            output.writeMessage(3, element);
          }
        }
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (this.bean1 != null) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeMessageSize(1, this.bean1);
      }
      if (this.bean2 != null) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeMessageSize(2, this.bean2);
      }
      if (this.beans != null && this.beans.length > 0) {
        for (int i = 0; i < this.beans.length; i++) {
          org.test.proto.nano.CycleProto.InnerBean element = this.beans[i];
          if (element != null) {
            size += com.google.protobuf.nano.CodedOutputByteBufferNano
              .computeMessageSize(3, element);
          }
        }
      }
      return size;
    }

    @Override
    public OuterBean mergeFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      while (true) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            return this;
          default: {
            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
              return this;
            }
            break;
          }
          case 10: {
            if (this.bean1 == null) {
              this.bean1 = new org.test.proto.nano.CycleProto.InnerBean();
            }
            input.readMessage(this.bean1);
            break;
          }
          case 18: {
            if (this.bean2 == null) {
              this.bean2 = new org.test.proto.nano.CycleProto.InnerBean();
            }
            input.readMessage(this.bean2);
            break;
          }
          case 26: {
            int arrayLength = com.google.protobuf.nano.WireFormatNano
                .getRepeatedFieldArrayLength(input, 26);
            int i = this.beans == null ? 0 : this.beans.length;
            org.test.proto.nano.CycleProto.InnerBean[] newArray =
                new org.test.proto.nano.CycleProto.InnerBean[i + arrayLength];
            if (i != 0) {
              java.lang.System.arraycopy(this.beans, 0, newArray, 0, i);
            }
            for (; i < newArray.length - 1; i++) {
              newArray[i] = new org.test.proto.nano.CycleProto.InnerBean();
              input.readMessage(newArray[i]);
              input.readTag();
            }
            // Last one without readTag.
            newArray[i] = new org.test.proto.nano.CycleProto.InnerBean();
            input.readMessage(newArray[i]);
            this.beans = newArray;
            break;
          }
        }
      }
    }

    public static OuterBean parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new OuterBean(), data);
    }

    public static OuterBean parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new OuterBean().mergeFrom(input);
    }
  }
}
