package chistov.pavel;

import chistov.pavel.model.TestDtoA;
import chistov.pavel.model.TestDtoB;
import chistov.pavel.model.TestDtoC;
import chistov.pavel.model.TestDtoD;
import chistov.pavel.model.TestDtoE;
import chistov.pavel.model.TestDtoF;
import chistov.pavel.model.TestDtoG;
import chistov.pavel.model.TestDtoH;
import chistov.pavel.model.TestDtoI;
import chistov.pavel.model.TestDtoJ;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Supplier;

public class NullSafeUtilsTest {

  @Test
  public void utilsTest() {
    TestDtoA testDtoA = new TestDtoA();
    TestDtoB testDtoB = new TestDtoB();
    TestDtoC testDtoC = new TestDtoC();
    TestDtoD testDtoD = new TestDtoD();
    TestDtoE testDtoE = new TestDtoE();
    TestDtoF testDtoF = new TestDtoF();
    TestDtoG testDtoG = new TestDtoG();
    TestDtoH testDtoH = new TestDtoH();
    TestDtoI testDtoI = new TestDtoI();
    TestDtoJ testDtoJ = new TestDtoJ();

    testDtoA.setTestDtoB(testDtoB);
    testDtoB.setTestDtoC(testDtoC);
    testDtoC.setTestDtoD(testDtoD);
    testDtoD.setTestDtoE(testDtoE);
    testDtoE.setTestDtoF(testDtoF);
    testDtoF.setTestDtoG(testDtoG);
    testDtoG.setTestDtoH(testDtoH);
    testDtoH.setTestDtoI(testDtoI);
    testDtoI.setTestDtoJ(testDtoJ);

    Supplier<TestDtoJ> testDtoKGetter = () -> testDtoA.getTestDtoB()
                                                      .getTestDtoC()
                                                      .getTestDtoD()
                                                      .getTestDtoE()
                                                      .getTestDtoF()
                                                      .getTestDtoG()
                                                      .getTestDtoH()
                                                      .getTestDtoI()
                                                      .getTestDtoJ();

    boolean isTestDtoJValuePresent = NullSafeUtils.isValuePresent(testDtoKGetter);
    Assert.assertTrue(isTestDtoJValuePresent);

    TestDtoJ otherTestDtoJ = NullSafeUtils.getValueSafety(testDtoKGetter);
    Assert.assertEquals(testDtoJ, otherTestDtoJ);

    isTestDtoJValuePresent = NullSafeUtils.isDeepValuePresent(testDtoA,
                                                              TestDtoA::getTestDtoB,
                                                              TestDtoB::getTestDtoC,
                                                              TestDtoC::getTestDtoD,
                                                              TestDtoD::getTestDtoE,
                                                              TestDtoE::getTestDtoF,
                                                              TestDtoF::getTestDtoG,
                                                              TestDtoG::getTestDtoH,
                                                              TestDtoH::getTestDtoI,
                                                              TestDtoI::getTestDtoJ);
    Assert.assertTrue(isTestDtoJValuePresent);

    otherTestDtoJ = NullSafeUtils.getDeepValueSafety(testDtoA,
                                                     TestDtoA::getTestDtoB,
                                                     TestDtoB::getTestDtoC,
                                                     TestDtoC::getTestDtoD,
                                                     TestDtoD::getTestDtoE,
                                                     TestDtoE::getTestDtoF,
                                                     TestDtoF::getTestDtoG,
                                                     TestDtoG::getTestDtoH,
                                                     TestDtoH::getTestDtoI,
                                                     TestDtoI::getTestDtoJ);
    Assert.assertEquals(testDtoJ, otherTestDtoJ);
  }

  @Test
  public void defaultValueTest() {
    TestDtoA testDtoA = new TestDtoA();
    TestDtoC defaultValue = new TestDtoC();

    Supplier<TestDtoC> testDtoCGetter = () -> testDtoA.getTestDtoB().getTestDtoC();

    boolean isTestDtoCValuePresent = NullSafeUtils.isValuePresent(testDtoCGetter);
    Assert.assertFalse(isTestDtoCValuePresent);

    TestDtoC otherTestDtoC = NullSafeUtils.getValueSafetyOrDefault(testDtoCGetter, defaultValue);
    Assert.assertEquals(defaultValue, otherTestDtoC);

    isTestDtoCValuePresent = NullSafeUtils.isDeepValuePresent(testDtoA,
                                                              TestDtoA::getTestDtoB,
                                                              TestDtoB::getTestDtoC);
    Assert.assertFalse(isTestDtoCValuePresent);

    otherTestDtoC = NullSafeUtils.getDeepValueSafetyOrDefault(testDtoA,
                                                              defaultValue,
                                                              TestDtoA::getTestDtoB,
                                                              TestDtoB::getTestDtoC);
    Assert.assertEquals(defaultValue, otherTestDtoC);
  }

  @Test
  public void nullValueTest() {
    TestDtoA testDtoA = new TestDtoA();
    Supplier<TestDtoC> testDtoCGetter = () -> testDtoA.getTestDtoB().getTestDtoC();

    boolean isTestDtoCValuePresent = NullSafeUtils.isValuePresent(testDtoCGetter);
    Assert.assertFalse(isTestDtoCValuePresent);

    TestDtoC testDtoC = NullSafeUtils.getValueSafety(testDtoCGetter);
    Assert.assertNull(testDtoC);

    isTestDtoCValuePresent = NullSafeUtils.isDeepValuePresent(testDtoA,
                                                              TestDtoA::getTestDtoB,
                                                              TestDtoB::getTestDtoC);
    Assert.assertFalse(isTestDtoCValuePresent);

    testDtoC = NullSafeUtils.getDeepValueSafety(testDtoA,
                                                TestDtoA::getTestDtoB,
                                                TestDtoB::getTestDtoC);
    Assert.assertNull(testDtoC);
  }
}
