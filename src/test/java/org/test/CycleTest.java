package org.test;

import org.junit.Assert;
import org.junit.Test;
import org.test.proto.CycleProto;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class CycleTest {

    @Test
    public void test() throws Exception {
        CycleProto.InnerBean.Builder innerBean = CycleProto.InnerBean.newBuilder()
                .setValue1(1L)
                .setValue2(Long.MAX_VALUE)
                .setValue3(300000000000000L)
                .setValue5("0123456789abcdef");

        CycleProto.OuterBean outerBean = CycleProto.OuterBean.newBuilder()
                .setBean1(innerBean) // the same bean
                .setBean2(innerBean) // the same bean
                .addBeans(innerBean) // the same bean
                .addBeans(innerBean) // the same bean
                .build();

        ByteOutputStream os = new ByteOutputStream();
        outerBean.writeTo(os);

        System.out.printf("size = %d\n", os.size());

        ByteInputStream is = new ByteInputStream(os.getBytes(), os.size());
        CycleProto.OuterBean outerBean2 = CycleProto.OuterBean.parseFrom(is);

        // 1. references are lost (may be important for a complex structure)
        // 2. the final size is x3 times more than actually required
        Assert.assertSame(outerBean2.getBean1(), outerBean2.getBean2());
    }

    @Test
    public void testLoop() throws Exception {
        CycleProto.InnerBean.Builder innerBean1 = CycleProto.InnerBean.newBuilder()
                .setValue1(100);

        CycleProto.InnerBean.Builder innerBean2 = CycleProto.InnerBean.newBuilder()
                .setValue1(200);

        innerBean1.setReference(innerBean2);
        innerBean2.setReference(innerBean1);

        CycleProto.OuterBean outerBean = CycleProto.OuterBean.newBuilder()
                .setBean1(innerBean1)
                .setBean2(innerBean2)
                .build();

        ByteOutputStream os = new ByteOutputStream();
        outerBean.writeTo(os);

        ByteInputStream is = new ByteInputStream(os.getBytes(), os.size());
        CycleProto.OuterBean outerBean2 = CycleProto.OuterBean.parseFrom(is);

        // Check for cyclic links
        Assert.assertSame(outerBean2.getBean1(), outerBean2.getBean2().getReference());
        Assert.assertSame(outerBean2.getBean2(), outerBean2.getBean1().getReference());
    }
}
