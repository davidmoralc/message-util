import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

public class TestMessageUtil {

  String message = "Robert";    
  MessageUtil messageUtil = new MessageUtil(message);
   
  @Test
  public void testPrintMessage() {      
    System.out.println("Inside testPrintMessage()");     
    assertEquals(message,messageUtil.printMessage());
  }

  // @Test
  // public void testPrintMessageBad() {      
  //   System.out.println("Inside testPrintMessage()");     
  //   assertEquals(message,"nope4");
  // }  

  // @Test
  // public void testPrintMessageBad2() {      
  //   System.out.println("Inside testPrintMessage()");     
  //   assertEquals(message,"nope2");
  // }    

  @Test
  public void testSalutationMessage() {
    System.out.println("Inside testSalutationMessage()");
    message = "Hi!" + "Robert";
    assertEquals(message,messageUtil.salutationMessage());
  }
}