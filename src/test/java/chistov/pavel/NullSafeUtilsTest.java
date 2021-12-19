package chistov.pavel;

import chistov.pavel.model.TestDtoA;
import chistov.pavel.model.TestDtoB;
import chistov.pavel.model.TestDtoC;
import org.junit.Assert;
import org.junit.Test;

public class NullSafeUtilsTest {

  @Test
  public void utilsTest() {
    TestDtoA testDtoA = new TestDtoA();
    TestDtoB testDtoB = new TestDtoB();
    TestDtoC testDtoC = new TestDtoC();

    testDtoA.setTestDtoB(testDtoB);
    testDtoB.setTestDtoC(testDtoC);

    Assert.assertTrue(NullSafeUtils.isValuePresent(() -> testDtoA.getTestDtoB().getTestDtoC()));
    TestDtoC otherTestDtoC = NullSafeUtils.getValueSafety(() -> testDtoA.getTestDtoB().getTestDtoC());
    Assert.assertEquals(testDtoC, otherTestDtoC);

    Assert.assertTrue(NullSafeUtils.isDeepValuePresent(testDtoA,
                                                       TestDtoA::getTestDtoB,
                                                       TestDtoB::getTestDtoC));
    otherTestDtoC = NullSafeUtils.getDeepValueSafety(testDtoA,
                                                     TestDtoA::getTestDtoB,
                                                     TestDtoB::getTestDtoC);
    Assert.assertEquals(testDtoC, otherTestDtoC);
  }

  @Test
  public void defaultValueTest() {
    TestDtoA testDtoA = new TestDtoA();
    TestDtoC defaultValue = new TestDtoC();

    Assert.assertFalse(NullSafeUtils.isValuePresent(() -> testDtoA.getTestDtoB().getTestDtoC()));
    TestDtoC otherTestDtoC = NullSafeUtils.getValueSafetyOrDefault(() -> testDtoA.getTestDtoB().getTestDtoC(), defaultValue);
    Assert.assertEquals(defaultValue, otherTestDtoC);

    Assert.assertFalse(NullSafeUtils.isDeepValuePresent(testDtoA,
                                                        TestDtoA::getTestDtoB,
                                                        TestDtoB::getTestDtoC));
    otherTestDtoC = NullSafeUtils.getDeepValueSafetyOrDefault(testDtoA,
                                                              defaultValue,
                                                              TestDtoA::getTestDtoB,
                                                              TestDtoB::getTestDtoC);
    Assert.assertEquals(defaultValue, otherTestDtoC);
  }

  @Test
  public void nullValueTest() {
    TestDtoA testDtoA = new TestDtoA();

    Assert.assertFalse(NullSafeUtils.isValuePresent(() -> testDtoA.getTestDtoB().getTestDtoC()));
    TestDtoC testDtoC = NullSafeUtils.getValueSafety(() -> testDtoA.getTestDtoB().getTestDtoC());
    Assert.assertNull(testDtoC);

    Assert.assertFalse(NullSafeUtils.isDeepValuePresent(testDtoA,
                                                        TestDtoA::getTestDtoB,
                                                        TestDtoB::getTestDtoC));
    testDtoC = NullSafeUtils.getDeepValueSafety(testDtoA,
                                                TestDtoA::getTestDtoB,
                                                TestDtoB::getTestDtoC);

    Assert.assertNull(testDtoC);
  }
}
