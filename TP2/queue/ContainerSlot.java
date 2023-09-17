package queue;

public  class ContainerSlot extends QueueStateSlots{
	
	private Object element;
	
	public ContainerSlot (Object element) {
		this.element = element;
	}
	public boolean isEmptyPolymorfic() {
		return false;
	}
	public Object takePolymorfic() {
		return element;
	}
	public Object headPolymorfic() {
		return element;
	}
	
}


