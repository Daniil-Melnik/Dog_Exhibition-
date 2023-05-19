import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;


public class GUITest1 {
@Test
public void testcheckName() {
Assert.assertFalse(GUI.checkName(""));
Assert.assertTrue(GUI.checkName("danya"));
Assert.assertTrue(GUI.checkName("Aleg Tkach"));
Assert.assertTrue(GUI.checkName("Niiodh"));
Assert.assertFalse(GUI.checkName("654fdfdgfdg"));
Assert.assertFalse(GUI.checkName(" "));
Assert.assertTrue(GUI.checkName("hand"));
Assert.assertFalse(GUI.checkName("@"));
}
@Test
public void testcheckFileName() {
Assert.assertFalse(GUI.checkName(""));
Assert.assertTrue(GUI.checkName("danya"));
Assert.assertTrue(GUI.checkName("Aleg Tkach"));
Assert.assertTrue(GUI.checkName("Niiodh"));
Assert.assertFalse(GUI.checkName("654fdfdgfdg"));
Assert.assertFalse(GUI.checkName(" "));
Assert.assertTrue(GUI.checkName("hand"));
Assert.assertFalse(GUI.checkName("@"));
}

@BeforeClass
 public static void allTestsStarted() {
 System.out.println("Начало тестирования");
 }
 @AfterClass
 public static void allTestsFinished() {
 System.out.println("Конец тестирования");
 }
 @Before
 public void testStarted() {
 System.out.println("Запуск теста");
 }
 @After
 public void testFinished() {
 System.out.println("Завершение теста");
 }
}
