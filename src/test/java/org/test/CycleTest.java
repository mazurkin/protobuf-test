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
                .setValue1(100000000000000L)
                .setValue2(200000000000000L)
                .setValue3(300000000000000L);

        CycleProto.OuterBean outerBean = CycleProto.OuterBean.newBuilder()
                .setValue1(innerBean) // the same bean
                .setValue2(innerBean) // the same bean
                .setValue3(innerBean) // the same bean
                .build();

        ByteOutputStream os = new ByteOutputStream();
        outerBean.writeTo(os);

        ByteInputStream is = new ByteInputStream(os.getBytes(), os.size());

        CycleProto.OuterBean outerBean2 = CycleProto.OuterBean.parseFrom(is);

        // 1. references are lost (may be important for a complex structure)
        // 2. the final size is x3 times more than actually required
        Assert.assertSame(outerBean2.getValue1(), outerBean2.getValue2());
        Assert.assertSame(outerBean2.getValue1(), outerBean2.getValue2());
    }

}
