package queue;

import java.util.ArrayList;

public  class ContainerSlot extends QueueStateSlots{
	
	public ArrayList<Object> elemento = new ArrayList();

	public ContainerSlot (Object element) {
		elemento.add(element);
	}
	
	public boolean isEmptyPolymorfic() {
		return false;
	}
	
	public Object headPolymorfic() {
		return elemento.get(0);
	}
	
	public Object takePolymorfic() {
		return elemento.remove(0);
	}
}


