package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	QueueStateSlots elementoVacio = objetos.get(objetos.size() - 1);
	objetos.remove(objetos.size() - 1);
	objetos.add(new ContainerSlot(cargo));
	objetos.add(elementoVacio);
	return this;
  }

  public Object take() {
	// TODO Auto-generated method stub
	Object taken = objetos.get(0).takePolymorfic();
	objetos.remove(0);
	return taken;
  }

  public Object head() {
	// TODO Auto-generated method stub
	return objetos.get(0).headPolymorfic();
  }
	
  public int size() {
	// TODO Auto-generated method stub
	return objetos.size() - 1;
  }
}
