package org.test;

import org.junit.Assert;
import org.junit.Test;
import org.test.proto.nano.CycleProto;

import com.google.protobuf.nano.MessageNano;

public class CycleNanoTest {

    @Test
    public void test() throws Exception {
        CycleProto.InnerBean innerBean = new CycleProto.InnerBean();
        innerBean.value1 = 1L;
        innerBean.value2 = Long.MAX_VALUE;
        innerBean.value3 = 300000000000000L;
        innerBean.value5 = "0123456789abcdef";

        CycleProto.OuterBean outerBean = new CycleProto.OuterBean();
        outerBean.bean1 = innerBean; // the same bean
        outerBean.bean2 = innerBean; // the same bean
        // the same bean
        outerBean.beans = new CycleProto.InnerBean[] { innerBean, innerBean };

        byte[] data = MessageNano.toByteArray(outerBean);

        System.out.printf("size = %d\n", data.length);

        CycleProto.OuterBean outerBean2 = CycleProto.OuterBean.parseFrom(data);

        // 1. references are lost (may be important for a complex structure)
        // 2. the final size is x3 times more than actually required
        Assert.assertSame(outerBean2.bean1, outerBean2.bean2);
    }

    @Test
    public void testLoop() throws Exception {
        CycleProto.InnerBean innerBean1 = new CycleProto.InnerBean();
        innerBean1.value1 = 100;

        CycleProto.InnerBean innerBean2 = new CycleProto.InnerBean();
        innerBean2.value1 = 200;

        innerBean1.reference = innerBean2;
        innerBean2.reference = innerBean1;

        CycleProto.OuterBean outerBean = new CycleProto.OuterBean();
        outerBean.bean1 = innerBean1;
        outerBean.bean2 = innerBean2;

        byte[] data = MessageNano.toByteArray(outerBean);

        System.out.printf("size = %d\n", data.length);

        CycleProto.OuterBean outerBean2 = CycleProto.OuterBean.parseFrom(data);

        // Check for cyclic links
        Assert.assertSame(outerBean2.bean1, outerBean2.bean2.reference);
        Assert.assertSame(outerBean2.bean2, outerBean2.bean1.reference);
    }
}
