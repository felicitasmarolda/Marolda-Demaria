package queue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QueueTest {

  private static final String QueueIsEmpty = "Queue is empty";
  private static final String Second = "Second";
  private static final String First = "First";
  private static final String Something = "Something";


  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueWithObjectSomething().isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( Something, queueWithObjectSomething().head() );
  }


  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWithObjectSomething();
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    Queue queue = queueWithObjectSomething();    
    
    assertEquals( Something, queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = new Queue();
    String firstAddedObject = First;
    String secondAddedObject = Second;
    
    queue.add( firstAddedObject );
    queue.add( secondAddedObject );

    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = new Queue();

    queue.add( First );
    queue.add( Second );

    assertEquals( queue.head(), First );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = queueWithObjectSomething();
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, new Queue().add( First ).add( Second ).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();
    Error e = assertThrows(Error.class, () -> queue.take());
	assertTrue( e.getMessage().equals( QueueIsEmpty ) );
  }
  
  @Test public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
	Queue queue = queueWithObjectSomething();
	queue.take();
	Error e = assertThrows(Error.class, () -> queue.take());
	assertTrue( e.getMessage().equals( QueueIsEmpty ) );
  }


  @Test public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();
    Error e = assertThrows(Error.class, () -> queue.head());
    assertTrue( e.getMessage().equals( QueueIsEmpty ) );
  }
  
  public Queue queueWithObjectSomething() {
	  return new Queue().add( Something );
  }
}