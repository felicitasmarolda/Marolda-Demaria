package queue;

import java.util.ArrayList;

public class Queue {
	
  public ArrayList<QueueStateSlots> objetos = new ArrayList();
  
  public Queue() {
	  objetos.add(new EmptySlot());
  }
  
  public boolean isEmpty() {
	  return objetos.get(0).isEmptyPolymorfic();
  }
  
  public Queue add( Object  cargo ) {
	  objetos.add(objetos.size() - 1, new ContainerSlot(cargo));
	  return this;
  }

  public Object take() {
	  Object taken = objetos.remove(0).takePolymorfic();
	  return taken;
  }

  public Object head() {
	  return objetos.get(0).headPolymorfic();
  }
	
  public int size() {
	  return objetos.size() - 1;
  }
  
}
